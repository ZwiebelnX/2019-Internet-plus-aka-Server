package com.aka.server.akaminiprogramserver.service;

import com.aka.server.akaminiprogramserver.DTO.result.ResponseDataDTO;
import com.aka.server.akaminiprogramserver.repo.docker.SongRepo;
import com.aka.server.akaminiprogramserver.repo.entity.SongEntity;
import com.aka.server.akaminiprogramserver.util.GlobalComponent;
import com.aka.server.akaminiprogramserver.util.GlobalData;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * <p>Title: SongService</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019版权</p>
 * <p>Company: </p>
 *
 * @author Zwiebeln_Chan
 * @version V1.0
 * @date 2019/5/24 20:22
 */

@Service
public class SongService {
    private final SongRepo songRepo;

    @Autowired
    public SongService(SongRepo songRepo) {
        this.songRepo = songRepo;
    }

    @Transactional
    public ResponseDataDTO createSong(MultipartFile file, Map<String, String> paramMap) {
        ResponseDataDTO responseDTO = new ResponseDataDTO();

        OkHttpClient okHttpClient = GlobalComponent.getOkHttpClient();

        SongEntity songEntity = new SongEntity();
        songEntity.setLeaderId(paramMap.get("openid"));
        songEntity.setLyric(paramMap.get("lyric"));
        songEntity.setName(paramMap.get("songName"));
        songEntity.setPeopleCounting((byte)0);
        songEntity.setPart("");

        songRepo.save(songEntity);

        String uploadFileName = songEntity.getId() + "_0.mp3";
        File uploadFile = new File(".", uploadFileName);
        try{

            if(!uploadFile.createNewFile()) throw new IOException("缓存文件创建失败");
            file.transferTo(uploadFile);

            MediaType multiPartFormData = MediaType.parse("multipart/form-data; charset=utf-8");
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("key", "aka/${filename}")
                    .addFormDataPart("file", uploadFileName, RequestBody.create(multiPartFormData, uploadFile))
                    .build();

            Request request = new Request.Builder()
                    .url(GlobalData.COS_BUCKET_URL)
                    .post(requestBody)
                    .addHeader("Content-Length", Long.toString(requestBody.contentLength()))
                    .build();
            final Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            if(response.isSuccessful()){
                songEntity.setFilesUrl(response.header("location") + ";");
                songRepo.save(songEntity);
                responseDTO.setSuccess(true);
                responseDTO.setResult(songEntity.getId());
            }
        } catch (Exception e){
            e.printStackTrace();
            responseDTO.setResult("创建缓存文件失败，请重试");
        } finally {
            uploadFile.delete();
        }
        return responseDTO;
    }
}

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

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

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
    public ResponseDataDTO createSong(MultipartFile file, Map<String, String> paramMap, HttpServletRequest httpRequest) {
        ResponseDataDTO responseDTO = new ResponseDataDTO();


        SongEntity songEntity = new SongEntity();
        songEntity.setLeaderId(paramMap.get("openid"));
        songEntity.setLyric(paramMap.get("lyric"));
        songEntity.setName(paramMap.get("songName"));
        songEntity.setPeopleCounting((byte)0);
        songEntity.setPart(paramMap.get("part") + ":" + paramMap.get("nickname") + ";");

        songRepo.save(songEntity);

        String uploadFileName = songEntity.getId() + "_0.mp3";
        Response response = uploadSongFile(file, uploadFileName, httpRequest);
        if(response != null && response.isSuccessful()){
            songEntity.setFilesUrl(response.header("location") + ";");
            songRepo.save(songEntity);
            responseDTO.setSuccess(true);
            responseDTO.setResult(songEntity.getId());
        }
        else{
            responseDTO.setReason("文件上传失败！");
        }
        return responseDTO;
    }

    public ResponseDataDTO participateSong(long songId, MultipartFile file, Map<String, String> params, HttpServletRequest request){
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Optional<SongEntity> songEntity = songRepo.findById(songId);

        if(!songEntity.isPresent()) return new ResponseDataDTO("获取歌曲失败！请检查ID号");

        String uploadFilename = songEntity.get().getId() + "_" + songEntity.get().getPeopleCounting() + ".mp3";
        Response response = uploadSongFile(file, uploadFilename, request);
        if(response != null && response.isSuccessful()){
            songEntity.get().setFilesUrl(songEntity.get().getFilesUrl() + response.header("location") + ";");
            songEntity.get().setPart(songEntity.get().getPart() + params.get("part") + ":" + params.get("nickname") + ";");
            songRepo.save(songEntity.get());
            responseDataDTO.setSuccess(true);
        }
        else{
            responseDataDTO.setReason("上传文件失败！");
        }
        return responseDataDTO;
    }

    private Response uploadSongFile(MultipartFile file, String uploadFileName, HttpServletRequest httpRequest){
        OkHttpClient okHttpClient = GlobalComponent.getOkHttpClient();
        File upPath = new File(httpRequest.getSession().getServletContext().getRealPath("/uploadFile/up"));
        if(!upPath.exists()) upPath.mkdirs();

        File uploadFile = new File(upPath, uploadFileName);
        try{
            if(!uploadFile.createNewFile()){

                throw new IOException("缓存文件创建失败");
            }
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
            return call.execute();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            uploadFile.delete();
        }
    }
}

package com.aka.server.akaminiprogramserver.service;

import com.aka.server.akaminiprogramserver.DTO.result.ResponseDataDTO;
import com.aka.server.akaminiprogramserver.DTO.user.OpenidDTO;
import com.aka.server.akaminiprogramserver.DTO.user.UserDTO;
import com.aka.server.akaminiprogramserver.DTO.user.UserDataDTO;
import com.aka.server.akaminiprogramserver.DTO.user.WxOpenidDTO;
import com.aka.server.akaminiprogramserver.repo.docker.UserRepo;
import com.aka.server.akaminiprogramserver.repo.entity.UserEntity;
import com.aka.server.akaminiprogramserver.util.GlobalData;
import com.aka.server.akaminiprogramserver.util.JsonMapper;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * <p>Title: UserService</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019版权</p>
 * <p>Company: </p>
 *
 * @author Zwiebeln_Chan
 * @version V1.0
 * @date 2019/5/24 22:37
 */

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    // 获取openid
    public ResponseDataDTO getOpenid(String code){
        ResponseDataDTO responseData  = new ResponseDataDTO();
        try {
            String url = "https://api.weixin.qq.com/sns/jscode2session"; //微信服务器API端口
            //构造请求参数
            String params = "appid=" + GlobalData.APP_ID
                    + "&secret=" + GlobalData.APP_SECRET
                    + "&grant_type=" + GlobalData.GRANT_TYPE
                    + "&js_code=" + code;
            url = url + "?" + params;
            //向微信服务器发送请求
            OkHttpClient httpClient = new OkHttpClient();
            final Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            final Call call = httpClient.newCall(request);
            Response response = call.execute();
            if(response.isSuccessful() && response.body() != null){
                WxOpenidDTO openidDTO = JsonMapper.getMapper().readValue(response.body().string(), WxOpenidDTO.class);

                if(openidDTO.getErrcode() != 0) throw new IOException("获取openID失败！");

                responseData.setSuccess(true);
                OpenidDTO responseOpenid = new OpenidDTO();
                responseOpenid.setOpenid(openidDTO.getOpenid());
                responseData.setResult(responseOpenid);

                UserEntity userEntity = new UserEntity();
                userEntity.setOpenid(((OpenidDTO)responseData.getResult()).getOpenid());
                userRepo.save(userEntity);
            }
        } catch (Exception e){
            e.printStackTrace();
            responseData.setReason(e.getMessage());
        }

        return responseData;
    }


    //更新用户信息
    public boolean updateUserInfo(UserDTO userDTO){
        UserEntity userEntity = userRepo.findByOpenid(userDTO.getOpenid());
        try{
            UserDataDTO userData = userDTO.getData();
            userEntity.setRealName(userData.getRealName());
            userEntity.setAge(Byte.valueOf(userData.getAge()));
            userEntity.setCollege(userData.getCollege());
            userEntity.setGrade(userData.getGrade());
            userEntity.setPhone(userData.getPhone());
            userEntity.setGender(userData.getGender());

            userRepo.save(userEntity);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }


    //获取openid指定的用户信息
    public ResponseDataDTO getUserInfo(String openid){
        UserEntity user = userRepo.findByOpenid(openid);
        ResponseDataDTO response = new ResponseDataDTO();
        response.setSuccess(true);
        response.setResult(user);
        return response;
    }
}

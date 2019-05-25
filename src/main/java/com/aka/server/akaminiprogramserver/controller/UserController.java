package com.aka.server.akaminiprogramserver.controller;

import com.aka.server.akaminiprogramserver.DTO.result.ResponseDataDTO;
import com.aka.server.akaminiprogramserver.DTO.user.UserDTO;
import com.aka.server.akaminiprogramserver.service.UserService;
import com.aka.server.akaminiprogramserver.util.GlobalComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;

    }

    @GetMapping("global/openid")
    public ResponseDataDTO getOpenid(@RequestParam String code){
        return userService.getOpenid(code);
    }

    @PostMapping("/user/info")
    public ResponseDataDTO updateUserInfo(@RequestBody String jsonString){
        UserDTO user;
        try{
            user = GlobalComponent.getJsonMapper().readValue(jsonString, UserDTO.class);
        } catch (Exception e){
            return new ResponseDataDTO("json格式错误！");
        }

        ResponseDataDTO responseData = new ResponseDataDTO();
        if(userService.updateUserInfo(user)){
            responseData.setSuccess(true);
        }
        else{
            responseData.setReason("数据存储失败！请稍后再试");
        }
        return responseData;
    }

    @GetMapping("/user/info")
    public ResponseDataDTO getUserInfo(@RequestParam String openid){
        return userService.getUserInfo(openid);
    }
}

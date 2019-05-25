package com.aka.server.akaminiprogramserver.controller;

import com.aka.server.akaminiprogramserver.DTO.user.User;
import com.aka.server.akaminiprogramserver.service.SongService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.aka.server.akaminiprogramserver.DTO.result.ResponseData;

import java.io.IOException;


@RestController
public class UserController {
    private final SongService songService;

    @Autowired
    public UserController(SongService songService){
        this.songService = songService;

    }

    @PostMapping("/user/info")
    public ResponseData updateUserInfo(@RequestBody String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(jsonString, User.class);
        ResponseData responseData = new ResponseData();
        responseData.setSuccess(true);
        return responseData;
    }
}

package com.aka.server.akaminiprogramserver.controller;

import com.aka.server.akaminiprogramserver.DTO.result.ResponseDataDTO;
import com.aka.server.akaminiprogramserver.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SongController {
    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping("/song")
    public ResponseDataDTO createSong(MultipartHttpServletRequest request){
        MultipartFile file = request.getFile("songFile");
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("songName", request.getParameter("songName"));
        paramMap.put("lyric", request.getParameter("lyric"));
        paramMap.put("openid", request.getParameter("openid"));
        return songService.createSong(file, paramMap, request);
    }
}

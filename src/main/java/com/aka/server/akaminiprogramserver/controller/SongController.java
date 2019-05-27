package com.aka.server.akaminiprogramserver.controller;

import com.aka.server.akaminiprogramserver.DTO.result.ResponseDataDTO;
import com.aka.server.akaminiprogramserver.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
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
        paramMap.put("nickname", request.getParameter("nickname"));
        paramMap.put("part", request.getParameter("part"));
        return songService.createSong(file, paramMap, request);
    }

    @PostMapping("song/{songId}")
    public ResponseDataDTO participateSong(@PathVariable long songId, MultipartHttpServletRequest request){
        MultipartFile file = request.getFile("songFile");
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("openid", request.getParameter("openid"));
        paramMap.put("nickname", request.getParameter("nickname"));
        paramMap.put("part", request.getParameter("part"));
        return songService.participateSong(songId, file, paramMap, request);
    }

    @GetMapping("song/mysong")
    public ResponseDataDTO getMySong(@RequestParam String openid){
        return songService.getMySong(openid);
    }

    @GetMapping("song/songlist")
    public ResponseDataDTO getSongList(){
        return songService.getSongList();
    }

    @DeleteMapping("song/{songId}")
    public ResponseDataDTO deleteSong(@PathVariable long songId){
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if(songService.deleteSong(songId)){
            responseDataDTO.setSuccess(true);
        }
        else{
            responseDataDTO.setResult("删除合唱失败！请检查ID或稍后重试");
        }
        return responseDataDTO;
    }
}

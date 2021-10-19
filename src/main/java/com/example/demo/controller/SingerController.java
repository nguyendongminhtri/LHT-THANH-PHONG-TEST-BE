package com.example.demo.controller;

import com.example.demo.dto.response.ResponMessage;
import com.example.demo.dto.response.SingerResponse;
import com.example.demo.model.Singer;
import com.example.demo.model.Song;
import com.example.demo.service.impl.SingerServiceImpl;
import com.example.demo.service.impl.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("singer")
public class SingerController {
    @Autowired
    SingerServiceImpl singerService;
    @Autowired
    SongServiceImpl songService;
    @GetMapping
    public ResponseEntity<?> pageSinger(@PageableDefault(sort = "nameSinger", direction = Sort.Direction.ASC)Pageable pageable){
        Page<Singer> singerPage = singerService.findAll(pageable);
        if(singerPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(singerPage,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createSinger(@RequestBody Singer singer){
//        if(singer.getAvatarSinger()==null){
//            return new ResponseEntity<>(new ResponMessage("no_avatar_singer"), HttpStatus.OK);
//        }
        singerService.save(singer);
        return new ResponseEntity<>(new ResponMessage("yes"), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> pageSongBySingerId(@PageableDefault(sort = "nameSong", direction = Sort.Direction.ASC)Pageable pageable, @PathVariable Long id){
        Optional<Singer> singer = singerService.findById(id);
        if(!singer.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Page<Song> songPage = songService.pageSongBySingerId(id,pageable);
        return new ResponseEntity<>(new SingerResponse(singer.get(), songPage),HttpStatus.OK);
    }
}

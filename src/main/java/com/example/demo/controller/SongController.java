package com.example.demo.controller;

import com.example.demo.dto.response.ResponMessage;
import com.example.demo.model.Band;
import com.example.demo.model.Category;
import com.example.demo.model.Song;
import com.example.demo.service.impl.CategoryServiceImpl;
import com.example.demo.service.impl.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RequestMapping("song")
@RestController
public class SongController {
    @Autowired
    SongServiceImpl songService;
    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping
    public ResponseEntity<?> pageSong(@PageableDefault(sort = "nameSong", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Song> page = songService.findAll(pageable);
        List<Band> bandList = new ArrayList<>();
        if (page.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createSong(@RequestBody Song song) {
//        song.setCategoryList(song.getCategoryList());
        if (song.getAvatarSong() == null) {
            return new ResponseEntity<>(new ResponMessage("no_avatar"), HttpStatus.OK);
        }
        if (song.getMp3Url() == null) {
            return new ResponseEntity<>(new ResponMessage("no_mp3Url"), HttpStatus.OK);
        }
        if (song.getCategory() == null) {
            return new ResponseEntity<>(new ResponMessage("no_category"), HttpStatus.OK);
        }
        if (song.getSingerList().size() == 0) {
            if (song.getBandList().size() == 0) {
                return new ResponseEntity<>(new ResponMessage("no_singer_band"), HttpStatus.OK);
            } else {
                songService.save(song);
                return new ResponseEntity<>(new ResponMessage("yes"), HttpStatus.OK);
            }
        } else {
            songService.save(song);
            return new ResponseEntity<>(new ResponMessage("yes"), HttpStatus.OK);
        }
    }

    @GetMapping("category/{id}")
    public ResponseEntity<?> findCategoryBySong(@PathVariable Long id, Pageable pageable) {
        Optional<Category> category = categoryService.findById(id);
        if (!category.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Page<Song> songPage = songService.findAllByCategoryId(category.get().getId(), pageable);
        return new ResponseEntity<>(songPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailSong(@PathVariable Long id) {
        Optional<Song> song = songService.findById(id);
        if (!song.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(song, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSong(@PathVariable Long id) {
        Optional<Song> song = songService.findById(id);
        if (!song.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        songService.deleteById(id);
        return new ResponseEntity<>(new ResponMessage("delete_success"), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listSong() {
        List<Song> songList = songService.findAll();
        if (songList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(songList, HttpStatus.OK);
    }
}

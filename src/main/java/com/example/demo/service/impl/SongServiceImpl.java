package com.example.demo.service.impl;

import com.example.demo.model.Category;
import com.example.demo.model.Singer;
import com.example.demo.model.Song;
import com.example.demo.model.User;
import com.example.demo.repository.ISongRepository;
import com.example.demo.security.userprincal.UserDetailService;
import com.example.demo.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SongServiceImpl implements ISongService {
    @Autowired
    ISongRepository songRepository;
    @Autowired
    UserDetailService userDetailService;
    @Override
    public Page<Song> findAll(Pageable pageable) {
        return songRepository.findAll(pageable);
    }

    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public Song save(Song song) {
        User user = userDetailService.getCurrentUser();
        song.setUser(user);
        return songRepository.save(song);
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public Page<Song> findAllByCategoryId(Long id, Pageable pageable) {
        return songRepository.findAllByCategoryId(id, pageable);
    }

    @Override
    public Page<Song> pageSongBySingerId(Long id, Pageable pageable) {
        return songRepository.pageSongBySingerId(id,pageable);
    }

    @Override
    public List<Singer> listSingerBySongId(Long id) {
        return songRepository.listSingerBySongId(id);
    }



}

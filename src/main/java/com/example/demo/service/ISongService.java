package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ISongService {
    Page<Song> findAll(Pageable pageable);
    List<Song> findAll();
    void deleteById(Long id);
    Song save(Song song);
    Optional<Song> findById(Long id);
    Page<Song> findAllByCategoryId(Long id, Pageable pageable);
    Page<Song> pageSongBySingerId(Long id,Pageable pageable);
}

package com.example.demo.service;

import com.example.demo.model.Singer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ISingerService {
    Page<Singer> findAll(Pageable pageable);
    void deleteById(Long id);
    Singer save(Singer singer);
    Optional<Singer> findById(Long id);
}

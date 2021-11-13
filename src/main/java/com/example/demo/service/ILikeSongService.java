package com.example.demo.service;

import com.example.demo.model.LikeSong;

import java.util.Optional;

public interface ILikeSongService extends IGeneratorService<LikeSong>{
    Optional<LikeSong> findBySongIdAndUserId(Long song_id, Long user_id);
}

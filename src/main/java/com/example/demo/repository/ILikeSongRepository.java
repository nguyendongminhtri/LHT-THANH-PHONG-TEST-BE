package com.example.demo.repository;

import com.example.demo.model.LikeSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILikeSongRepository extends JpaRepository<LikeSong, Long> {
    Optional<LikeSong> findBySongIdAndUserId(Long song_id, Long user_id);
}

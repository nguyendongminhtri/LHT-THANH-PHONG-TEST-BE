package com.example.demo.repository;

import com.example.demo.model.Category;
import com.example.demo.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISongRepository extends JpaRepository<Song, Long> {
   Page<Song> findAllByCategoryId(Long id, Pageable pageable);
   @Query("SELECT s.id FROM Song s INNER JOIN s.singerList t WHERE t.id = :id")
   Page<Song> pageSongBySingerId(Long id,Pageable pageable);
}

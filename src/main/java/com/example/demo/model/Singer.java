package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "singers")
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameSinger;
    @Lob
    private String avatarSinger;
    private LocalDate birthDay;
    private String description;
    @ManyToOne
    User user;
//    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "singerList")
//    @JsonBackReference
//    List<Song> songList = new ArrayList<>();
    public Singer() {
    }

    public Singer(Long id, String nameSinger, String avatarSinger, LocalDate birthDay, String description, User user) {
        this.id = id;
        this.nameSinger = nameSinger;
        this.avatarSinger = avatarSinger;
        this.birthDay = birthDay;
        this.description = description;
        this.user = user;
//        this.songList = songList;
    }

//    public void setSongList(List<Song> songList) {
//        this.songList = songList;
//    }
//
//    public List<Song> getSongList() {
//        return songList;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

    public String getAvatarSinger() {
        return avatarSinger;
    }

    public void setAvatarSinger(String avatarSinger) {
        this.avatarSinger = avatarSinger;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

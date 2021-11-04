package com.example.demo.model;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameSong;
    @Lob
    private String lyrics;
    private String avatarSong;
    private String mp3Url;
    @ManyToOne
    User user;
    @ManyToOne
    Category category;
//            @JsonIgnore
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "song_singer",joinColumns = @JoinColumn(name = "song_id"), inverseJoinColumns = @JoinColumn(name = "singer_id"))
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "song_singer",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "singer_id"))
            List<Singer> singerList = null;
//            @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "song_band", joinColumns = @JoinColumn(name = "song_id"), inverseJoinColumns = @JoinColumn(name = "band_id"))
    List<Band> bandList = null;

    public Song() {
    }

    public Song(Long id, String nameSong, String lyrics, String avatarSong, String mp3Url, User user, Category category, List<Singer> singerList, List<Band> bandList) {
        this.id = id;
        this.nameSong = nameSong;
        this.lyrics = lyrics;
        this.avatarSong = avatarSong;
        this.mp3Url = mp3Url;
        this.user = user;
        this.category = category;
        this.singerList = singerList;
        this.bandList = bandList;
    }

    public List<Band> getBandList() {
        if(bandList==null){
            bandList = new ArrayList<>();
        }
        return bandList;
    }

    public void setBandList(List<Band> bandList) {
        this.bandList = bandList;
    }

    public List<Singer> getSingerList() {
        if(singerList==null){
            singerList = new ArrayList<>();
        }
        return singerList;
    }

    public void setSingerList(List<Singer> singerList) {
        this.singerList = singerList;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getAvatarSong() {
        return avatarSong;
    }

    public void setAvatarSong(String avatarSong) {
        this.avatarSong = avatarSong;
    }

    public String getMp3Url() {
        return mp3Url;
    }

    public void setMp3Url(String mp3Url) {
        this.mp3Url = mp3Url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bands")
public class Band {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameBand;
    private String avatarBand;
    private String description;
    @ManyToOne
    User user;
    //    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "bandList")
//    @JsonBackReference
//    List<Song> songList = new ArrayList<>();
    @ManyToMany(mappedBy = "bandList", targetEntity = Song.class)
    @JsonIgnoreProperties("bandList")
    List<Song> songList = new ArrayList<>();

    public Band() {
    }

    public Band(Long id, String nameBand, String avatarBand, String description, User user, List<Song> songList) {
        this.id = id;
        this.nameBand = nameBand;
        this.avatarBand = avatarBand;
        this.description = description;
        this.user = user;
        this.songList = songList;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameBand() {
        return nameBand;
    }

    public void setNameBand(String nameBand) {
        this.nameBand = nameBand;
    }

    public String getAvatarBand() {
        return avatarBand;
    }

    public void setAvatarBand(String avatarBand) {
        this.avatarBand = avatarBand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

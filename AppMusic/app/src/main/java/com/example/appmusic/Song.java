package com.example.appmusic;

public class Song {
    private String nameSong;
    private int mp3;

    public Song(String nameSong, int mp3) {
        this.nameSong = nameSong;
        this.mp3 = mp3;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public int getMp3() {
        return mp3;
    }

    public void setMp3(int mp3) {
        this.mp3 = mp3;
    }
}

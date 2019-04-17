package com.javarank.magicapp.MediaPlayer.Model;

public class SongsInfo {
    public  String songName;
    public  String artistName;
    public String songUrl;

    public SongsInfo(String songName, String artistName, String songUrl) {
        this.songName = songName;
        this.artistName = artistName;
        this.songUrl = songUrl;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getSongUrl() {
        return songUrl;
    }
}

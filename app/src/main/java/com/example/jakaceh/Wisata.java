package com.example.jakaceh;

public class Wisata {
    public String judul;
    public String detail;
    public String detailSpec;
    public int gambar;
    public float rating;

    public Wisata(String judul, String detail, int gambar, float rating, String detailSpec) {
        this.judul = judul;
        this.detail = detail;
        this.gambar = gambar;
        this.rating = rating;
        this.detailSpec = detailSpec;
    }
}

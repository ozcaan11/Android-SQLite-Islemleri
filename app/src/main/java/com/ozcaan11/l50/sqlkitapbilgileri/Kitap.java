package com.ozcaan11.l50.sqlkitapbilgileri;

/**
 * Author : l50 - Özcan YARIMDÜNYA (@ozcaan11)
 * Date   : 11.05.2016 - 16:04
 */

public class Kitap {
    int kitap_id;
    String kitap_adi;
    String kitap_yazar;
    int kitap_sayfa_sayisi;
    int kitap_fiyat;

    public Kitap() {
    }

    public Kitap(String kitap_adi, String kitap_yazar, int kitap_sayfa_sayisi, int kitap_fiyat) {
        this.kitap_adi = kitap_adi;
        this.kitap_yazar = kitap_yazar;
        this.kitap_sayfa_sayisi = kitap_sayfa_sayisi;
        this.kitap_fiyat = kitap_fiyat;
    }

    public Kitap(int kitap_id, String kitap_adi, String kitap_yazar, int kitap_sayfa_sayisi, int kitap_fiyat) {
        this.kitap_id = kitap_id;
        this.kitap_adi = kitap_adi;
        this.kitap_yazar = kitap_yazar;
        this.kitap_sayfa_sayisi = kitap_sayfa_sayisi;
        this.kitap_fiyat = kitap_fiyat;
    }

    public int getKitap_id() {
        return kitap_id;
    }

    public void setKitap_id(int kitap_id) {
        this.kitap_id = kitap_id;
    }

    public String getKitap_adi() {
        return kitap_adi;
    }

    public void setKitap_adi(String kitap_adi) {
        this.kitap_adi = kitap_adi;
    }

    public String getKitap_yazar() {
        return kitap_yazar;
    }

    public void setKitap_yazar(String kitap_yazar) {
        this.kitap_yazar = kitap_yazar;
    }

    public int getKitap_sayfa_sayisi() {
        return kitap_sayfa_sayisi;
    }

    public void setKitap_sayfa_sayisi(int kitap_sayfa_sayisi) {
        this.kitap_sayfa_sayisi = kitap_sayfa_sayisi;
    }

    public int getKitap_fiyat() {
        return kitap_fiyat;
    }

    public void setKitap_fiyat(int kitap_fiyat) {
        this.kitap_fiyat = kitap_fiyat;
    }
}


package com.ozcaan11.l50.sqlkitapbilgileri;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : l50 - Özcan YARIMDÜNYA (@ozcaan11)
 * Date   : 11.05.2016 - 16:07
 */

public class Database extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "KitapDb";
    public static final String TABLE_NAME = "Kitap";

    public static final String KITAP_ID = "kitap_id";
    public static final String KITAP_ADI = "kitap_adi";
    public static final String KITAP_YAZARI = "kitap_yazari";
    public static final String KITAP_SAYFA_SAYISI = "kitap_sayfa_sayisi";
    public static final String KITAP_FIYATI = "kitap_fiyati";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " " +
                "( " +
                "" + KITAP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "" + KITAP_ADI + " TEXT, " +
                "" + KITAP_YAZARI + " TEXT, " +
                "" + KITAP_SAYFA_SAYISI + " INTEGER, " +
                "" + KITAP_FIYATI + " INTEGER " +
                ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public List<Kitap> tumKitaplar(String siralama) {
        List<Kitap> kitapList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + siralama;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Kitap kitap = new Kitap();
                kitap.setKitap_id(Integer.parseInt(cursor.getString(0)));
                kitap.setKitap_adi(cursor.getString(1));
                kitap.setKitap_yazar(cursor.getString(2));
                kitap.setKitap_sayfa_sayisi(Integer.parseInt(cursor.getString(3)));
                kitap.setKitap_fiyat(Integer.parseInt(cursor.getString(4)));
                kitapList.add(kitap);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return kitapList;
    }

    public Kitap kitapDetay(int kitapId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + KITAP_ID + " = " + kitapId;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        assert cursor != null;
        Kitap kitap = new Kitap(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)));

        db.close();
        cursor.close();

        return kitap;
    }

    public void kitapEkle(Kitap kitap) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KITAP_ADI, kitap.getKitap_adi());
        values.put(KITAP_YAZARI, kitap.getKitap_yazar());
        values.put(KITAP_SAYFA_SAYISI, kitap.getKitap_sayfa_sayisi());
        values.put(KITAP_FIYATI, kitap.getKitap_fiyat());

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public void kitapSil(Kitap kitap) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KITAP_ID + " = ?", new String[]{String.valueOf(kitap.getKitap_id())});
        db.close();
    }

    public void kitapDuzenle(Kitap kitap) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KITAP_ADI, kitap.getKitap_adi());
        values.put(KITAP_YAZARI, kitap.getKitap_yazar());
        values.put(KITAP_SAYFA_SAYISI, kitap.getKitap_sayfa_sayisi());
        values.put(KITAP_FIYATI, kitap.getKitap_fiyat());
        db.update(TABLE_NAME, values, KITAP_ID + "= ?", new String[]{String.valueOf(kitap.getKitap_id())});
        db.close();
    }
}

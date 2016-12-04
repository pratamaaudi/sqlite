package com.example.moses.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Moses on 11/27/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String NAMA_DATABASE = "Jam3.db";
    public static final String NAMA_TABEL = "katalog";
    public static final String KOL_1 = "id";
    public static final String KOL_2 = "nama";
    public static final String KOL_3 = "harga";

    public DatabaseHelper(Context context) {
        super(context, NAMA_DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + NAMA_TABEL + "(id INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT, harga INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST "+ NAMA_TABEL);
        onCreate(sqLiteDatabase);
    }

    public boolean insert(String nama, int harga) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues inputan = new ContentValues();
        inputan.put(KOL_2, nama);
        inputan.put(KOL_3, harga);
        long hasil = sqLiteDatabase.insert(NAMA_TABEL, null, inputan);
        if (hasil == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor ambilsemuadata() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cur = sqLiteDatabase.rawQuery("SELECT * FROM " + NAMA_TABEL, null);
        return cur;
    }


}

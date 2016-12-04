package com.example.moses.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Moses on 12/4/2016.
 */

public class DatabaseHelperImport extends SQLiteOpenHelper {
    private static String DB_PATH = "/data/data/com.example.moses.sqlite/databases/";
    private static String DB_NAME = "Jam3.db";
    private SQLiteDatabase database;
    private final Context myContext;

    public static final String NAMA_TABEL = "katalog";
    public static final String KOL_1 = "id";
    public static final String KOL_2 = "nama";
    public static final String KOL_3 = "harga";

    public DatabaseHelperImport(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    public void createDB() throws IOException {
        boolean adaDB = checkDB();

        if (adaDB) {

        } else {
            this.getReadableDatabase();
            try {
                copyDB();
            } catch (IOException e) {
                throw new Error("error bro");
            }
        }
    }

    private void copyDB() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    private boolean checkDB() {
        SQLiteDatabase cek = null;
        try {
            String myPATH = DB_PATH + DB_NAME;
            cek = SQLiteDatabase.openDatabase(myPATH, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLException e) {

        }
        if (cek != null) {
            cek.close();
        }
        return cek != null ? true : false;
    }

    public void openDB() {
        String myPath = DB_PATH + DB_NAME;
        database = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if (database != null) {
            database.close();

        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insert(String nama, int harga) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues inputan = new ContentValues();
        inputan.put(KOL_2, nama);
        inputan.put(KOL_3, harga);
        long hasil = database.insert(NAMA_TABEL, null, inputan);
        if (hasil == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor ambilsemuadata() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cur = database.rawQuery("SELECT * FROM " + NAMA_TABEL, null);
        return cur;
    }
}

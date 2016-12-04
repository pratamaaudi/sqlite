package com.example.moses.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOError;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    //DatabaseHelper database;
    DatabaseHelperImport database;
    EditText edtnama, edtharga;
    Button btntambah, btnview, btncheckout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //database = new DatabaseHelper(this);

        edtnama = (EditText) findViewById(R.id.edtnama);
        btntambah = (Button) findViewById(R.id.btntambah);
        btnview = (Button) findViewById(R.id.btnview);
        edtharga = (EditText) findViewById(R.id.edtharga);
        btncheckout = (Button) findViewById(R.id.btncart);


        database = new DatabaseHelperImport(this);
        try{
            database.createDB();
        } catch (IOException ioe){
            throw new Error("Unable to create database");
        }
//
//
//        try{
//            database.openDB();
//        } catch (SQLException sqle){
//            throw sqle;
//        }
    }

    public void tambahdata(View view) {
        boolean isInserted = database.insert(edtnama.getText().toString(), Integer.parseInt(edtharga.getText().toString()));
        if (isInserted == true) {
            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
            edtnama.setText("");
            edtharga.setText("");
        } else
            Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
    }

    public void ambildata(View view) {
        Cursor cur = database.ambilsemuadata();
        if (cur.getCount() == 0) {
            Toast.makeText(MainActivity.this, "Tidak ada data dalam database", Toast.LENGTH_SHORT).show();
        } else {
            StringBuffer buffer = new StringBuffer();
            int totaldata = 0;
            while (cur.moveToNext()) {
                buffer.append("ID : " + cur.getString(0) + "\n");
                buffer.append("NAMA : " + cur.getString(1) + "\n");
                buffer.append("HARGA : " + cur.getString(2) + "\n\n");
                totaldata++;
            }
            buffer.append("TOTAL DATA : " + totaldata);
            Listdata("DATA", buffer.toString());
        }
    }

    public void checkout(View view) {
        Intent intent = new Intent("com.example.moses.sqlite.cart");
        startActivity(intent);
    }

    public void Listdata(String judul, String isi) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(true);
        dialog.setTitle(judul);
        dialog.setMessage(isi);
        dialog.show();
    }
}

package com.example.moses.sqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class cart extends AppCompatActivity {

    private ArrayList<String> pid = new ArrayList<>();
    private ArrayList<String> nama = new ArrayList<>();
    private ArrayList<String> harga = new ArrayList<>();
    private int[] img={R.drawable.img1,R.drawable.img2,R.drawable.img3};
    ListView list;

    DatabaseHelperImport database;
    Cursor cur;

    TextView txtid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        database = new DatabaseHelperImport(this);
        cur = database.ambilsemuadata();
        if(cur.getCount() == 0){
            Toast.makeText(cart.this,"Tidak ada data dalam database",Toast.LENGTH_LONG).show();
        } else {
            while (cur.moveToNext()){
                pid.add(cur.getString(0));
                nama.add(cur.getString(1));
                harga.add(cur.getString(2));
            }
        }


        list = (ListView) findViewById(R.id.list);
        list.setAdapter(new CustomAdapter(this,pid,nama,harga,img));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                cur.moveToPosition(i);
                String curid = cur.getString(0);
            }
        });
        }
    }

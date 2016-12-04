package com.example.moses.sqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class cart extends AppCompatActivity {

    private ArrayList<String> items = new ArrayList<>();
    private int[] img={R.drawable.img1,R.drawable.img2,R.drawable.img3};
    ListView list;

    DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        database = new DatabaseHelper(this);
        Cursor cur = database.ambilsemuadata();
        if(cur.getCount() == 0){
            Toast.makeText(cart.this,"Tidak ada data dalam database",Toast.LENGTH_LONG).show();
        } else {
            while (cur.moveToNext()){
                items.add(cur.getString(0));
            }
        }


        list = (ListView) findViewById(R.id.list);
        list.setAdapter(new CustomAdapter(this,items,img));

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(getApplicationContext(), "item ke "+(i+1),Toast.LENGTH_LONG).show();
            }
        });

        }
    }

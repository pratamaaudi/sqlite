package com.example.moses.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Moses on 12/4/2016.
 */
public class CustomAdapter extends BaseAdapter {
    ArrayList<String> items;
    int img[];
    cart mainActivity;
    LayoutInflater layoutinflater = null;
    public CustomAdapter(cart cart, ArrayList<String> items, int[] img) {
        this.img = img;
        this.items = items;
        this.mainActivity = cart;
        layoutinflater = (LayoutInflater)mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View Rowview = layoutinflater.inflate(R.layout.custom_row,null);
        TextView text = (TextView)Rowview.findViewById(R.id.text);
        text.setText(items.get(i));

        ImageView gambar = (ImageView)Rowview.findViewById(R.id.img);
        gambar.setImageResource(R.drawable.img1);
        return Rowview;
    }
}

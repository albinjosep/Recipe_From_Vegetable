package com.example.recipe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class recipe extends BaseAdapter {
    private Context Context;
    String[] name,description,image;

//    String[] e;


    public recipe(Context applicationContext, String[] name , String[] des, String[] img) {

        this.Context=applicationContext;
        this.name=name;
        this.description=des;
        this.image=img;

    }

    @Override
    public int getCount() {

        return name.length;
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int i, View convertview, ViewGroup parent) {


        LayoutInflater inflator=(LayoutInflater)Context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertview==null)
        {
            gridView=new View(Context);
            gridView=inflator.inflate(R.layout.recipe, null);

        }
        else
        {
            gridView=(View)convertview;

        }

        TextView name1=(TextView)gridView.findViewById(R.id.textView11);
        TextView des1=(TextView)gridView.findViewById(R.id.textView14);

        ImageView im=(ImageView) gridView.findViewById(R.id.imageView3);


        name1.setTextColor(Color.BLACK);
        des1.setTextColor(Color.BLACK);

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(Context);
        String ip=sh.getString("ip","");
        String url="http://" + ip + ":5000"+image[i];
        Picasso.with(Context).load(url).transform(new CircleTransform()). into(im);//circle




        name1.setText(name[i]);
        des1.setText(description[i]);

        return gridView;
    }


}
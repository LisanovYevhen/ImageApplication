package com.example.imageapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.res.Configuration;

import android.os.Bundle;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.felipecsl.gifimageview.library.GifImageView;
import com.squareup.picasso.Picasso;




public class SecondActivity extends AppCompatActivity {
    private String path;
    private GifImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        gifImageView= findViewById(R.id.gif_view_id);
        path=getIntent().getStringExtra("preview_gif");
        Glide.with(this).load(path).into(gifImageView);

        gifImageView.startAnimation();
    }

    private void setImageViewConfiguration(String path){
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            Picasso.with(getApplicationContext()).load(path).resize(1080,2160).into(gifImageView);


        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            Picasso.with(getApplicationContext()).load(path).resize(2160,1080).into(gifImageView);
            Picasso.with(getApplicationContext()).load(path).into(gifImageView);
        }
    }
}
package com.example.imageapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.net.URL;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String LOG_TAG="FirstActivity";
    private Button buttonStart;
    private RecyclerView recyclerView;
    private HardRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        buttonStart=findViewById(R.id.button_start);
        buttonStart.setOnClickListener(this);
        initRecyclerView();

    }
    private void initRecyclerView(){
        recyclerView =  new RecyclerView(this);
        adapter= new HardRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_start:
                new JsonAsyncTask().execute(InternetSetting.createURL());
                break;
        }
    }

 private class JsonAsyncTask extends AsyncTask<URL,Void,String>{

     @Override
     protected String doInBackground(URL... urls) {
         Log.d(LOG_TAG,"№ потока= "+Thread.currentThread().getName());
         return InternetSetting.responseJson(urls[0]);
     }

     @Override
     protected void onPostExecute(String s) {
         Log.d(LOG_TAG,"№ потока= "+Thread.currentThread().getName());
         Log.d(LOG_TAG,"Длина строки Json, меньше должно быть 45к= "+String.valueOf(s.length()));

     }
 }


}
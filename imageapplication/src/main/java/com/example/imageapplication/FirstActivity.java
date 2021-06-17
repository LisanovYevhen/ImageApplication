package com.example.imageapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

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
        recyclerView =findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_start:
                new JsonAsyncTask().execute(InternetSetting.createURL());
                break;
        }
    }

 private class JsonAsyncTask extends AsyncTask<URL,Void, ArrayList<Information>>{

     @Override
     protected ArrayList<Information> doInBackground(URL... urls) {
         return InternetSetting.valueJson(InternetSetting.responseStringForJson(urls[0]));
     }

     @Override
     protected void onPostExecute(ArrayList<Information> information ) {
         adapter=new HardRecyclerViewAdapter(information,getApplicationContext());
         recyclerView.setAdapter(adapter);

     }
 }


}
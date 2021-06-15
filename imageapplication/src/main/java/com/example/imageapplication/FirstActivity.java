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

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {
    public final String LOG_TAG="FirstActivity";
    private Button buttonStart;
    private RecyclerView recyclerView;
    private HardRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        buttonStart=findViewById(R.id.button_start);
        buttonStart.setOnClickListener(this);
       Log.d(LOG_TAG,InternetSetting.createURL().toString());

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
                Log.d(LOG_TAG,"Кнопка");
                break;
        }
    }

   /* private class JsonAsyncTask extends AsyncTask<>{

    }*/


}
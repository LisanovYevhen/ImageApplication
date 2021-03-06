package com.example.imageapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String LOG_TAG="FirstActivity";
    private Button buttonStart;
    private RecyclerView recyclerView;
    private HardRecyclerViewAdapter adapter;

    private EditText editTextQuery;
    private EditText editTextLimit;
    private EditText editTextOffset;

    private Spinner spinnerRating;
    private ArrayAdapter<String> ratingAdapter;

    private Spinner spinnerLanguage;
    private ArrayAdapter<String> languageAdapter;

    private static final String KEY_FOR_PARCELABLE_ARRAY ="bundle";
    private ArrayList<Information> values;




    @Override
    protected void onSaveInstanceState( Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(LOG_TAG,"onSaveInstanceState");
        outState.putParcelableArrayList(KEY_FOR_PARCELABLE_ARRAY,values);
    }

    @Override
    protected void onRestoreInstanceState( Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(LOG_TAG,"onRestoreInstanceState");
        values   = savedInstanceState.getParcelableArrayList(KEY_FOR_PARCELABLE_ARRAY);
        if(values!=null) {
            adapter.setInformationArrayList(values);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG,"onResume() ???????? ??????????  onRestoreInstanceState");
        // ?????? ?????????????? ???????????????????????? ???????????? ?????????????????????? adapter= new HardRecyclerViewAdapter(values, getApplicationContext())
        if (hasConnection(getApplicationContext())) {
            buttonStart.setEnabled(true);
        } else {
            buttonStart.setEnabled(false);
            Toast.makeText(this, "???????????? ???????????????? ?????? Jsona", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        buttonStart=findViewById(R.id.button_start);
        buttonStart.setOnClickListener(this);

        recyclerView =findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        adapter = new HardRecyclerViewAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setOnClickListener(this);

        spinnerRating=findViewById(R.id.spinner_for_rating);
        ratingAdapter= new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.spinner_array_for_rating));
        spinnerRating.setAdapter(ratingAdapter);


        spinnerLanguage=findViewById(R.id.spinner_for_language);
        languageAdapter= new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.spinner_array_for_language));
        spinnerLanguage.setAdapter(languageAdapter);

        editTextQuery=findViewById(R.id.edit_text_query);
        editTextLimit=findViewById(R.id.edit_text_limit);
        editTextOffset=findViewById(R.id.edit_text_offset);
        Log.d(LOG_TAG,"onCrete");
         if (!hasConnection(getApplicationContext())){
             buttonStart.setEnabled(false);
             Toast.makeText(this, "???????????? ???????????????? ?????? Jsona", Toast.LENGTH_SHORT).show();
         }

    }



    private static boolean hasConnection(final Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        return false;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_start:
                String  query=editTextQuery.getText().toString();
                String  limit=editTextLimit.getText().toString();
                String  offset=editTextOffset.getText().toString();
                String rating =spinnerRating.getSelectedItem().toString();
                String language =spinnerLanguage.getSelectedItem().toString();
                Log.d(LOG_TAG,InternetSetting.createURL(query,limit,offset,rating,language).toString());
                new JsonAsyncTask().execute(InternetSetting.createURL(query,limit,offset,rating,language));

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
         values=information;
         adapter.setInformationArrayList(information);

     }

 }


}
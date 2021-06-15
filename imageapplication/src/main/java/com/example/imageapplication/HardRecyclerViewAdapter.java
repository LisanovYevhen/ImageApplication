package com.example.imageapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public  class HardRecyclerViewAdapter extends RecyclerView.Adapter<HardRecyclerViewAdapter.HardRecyclerViewHolder> {
    private ArrayList<Information> informationArrayList = new ArrayList<>();

    public void setInformation(Information information){
        informationArrayList.add(information);
        notifyDataSetChanged();
    }



    @Override
    public HardRecyclerViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new HardRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder( HardRecyclerViewAdapter.HardRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return informationArrayList.size();
    }

 public    class HardRecyclerViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView type;
        private TextView username;
        private TextView title;
        private TextView import_datetime;

        public HardRecyclerViewHolder( View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_view);
            type=itemView.findViewById(R.id.image_view);
            username=itemView.findViewById(R.id.text_view_username);
            title=itemView.findViewById(R.id.text_view_title);
            import_datetime=itemView.findViewById(R.id.text_view_import_datetime);
        }
    }
}

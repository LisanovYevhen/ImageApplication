package com.example.imageapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public  class HardRecyclerViewAdapter extends RecyclerView.Adapter<HardRecyclerViewAdapter.HardRecyclerViewHolder> {
    private ArrayList<Information> informationArrayList;
    private Context context;

    public HardRecyclerViewAdapter(ArrayList<Information> informationArrayList, Context context) {
        this.informationArrayList = informationArrayList;
        this.context=context;
    }

    public HardRecyclerViewAdapter(Context context) {
        informationArrayList= new ArrayList<>();
        this.context = context;
    }

    public void setInformationArrayList(ArrayList<Information> informationArrayList) {
        this.informationArrayList = informationArrayList;
        notifyDataSetChanged();
    }

    @Override
    public HardRecyclerViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        HardRecyclerViewHolder hardRecyclerViewHolder = new HardRecyclerViewHolder(view);

        return  hardRecyclerViewHolder;
    }

    @Override
    public void onBindViewHolder( HardRecyclerViewAdapter.HardRecyclerViewHolder holder, int position) {
        Information information=informationArrayList.get(position);
        holder.title.setText(information.getTitle());
        holder.type.setText(information.getType());
        holder.username.setText(information.getUsername());
        holder.import_datetime.setText(information.getImport_datetime());
        String path=information.getUrl();
        String preview_gif=information.getPreview_gif();
        Picasso.with(context).load(path).resize(100,100).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.image_view:
                        Intent intent = new Intent(context,SecondActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("preview_gif",preview_gif);
                        context.getApplicationContext().startActivity(intent);
                        break;
                }
            }
        });
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
            type=itemView.findViewById(R.id.text_view_type);
            username=itemView.findViewById(R.id.text_view_username);
            title=itemView.findViewById(R.id.text_view_title);
            import_datetime=itemView.findViewById(R.id.text_view_import_datetime);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

    }
}

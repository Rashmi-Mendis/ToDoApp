package com.example.todoapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList task_id, task, task_date, task_time;

    CustomAdapter(Activity activity, Context context, ArrayList task_id, ArrayList task, ArrayList task_date,
                  ArrayList task_time){
        this.activity = activity;
        this.context = context;
        this.task_id = task_id;
        this.task = task;
        this.task_date = task_date;
        this.task_time = task_time;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.task_id_txt.setText(String.valueOf(task_id.get(position)));
        holder.task_txt.setText(String.valueOf(task.get(position)));
        holder.task_date_txt.setText(String.valueOf(task_date.get(position)));
        holder.task_time_txt.setText(String.valueOf(task_time.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(task_id.get(position)));
                intent.putExtra("title", String.valueOf(task.get(position)));
                intent.putExtra("author", String.valueOf(task_date.get(position)));
                intent.putExtra("pages", String.valueOf(task_time.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return task_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView task_id_txt, task_txt, task_date_txt, task_time_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            task_id_txt = itemView.findViewById(R.id.task_id_txt);
            task_txt = itemView.findViewById(R.id.task_txt);
            task_date_txt = itemView.findViewById(R.id.task_date_txt);
            task_time_txt = itemView.findViewById(R.id.task_time_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
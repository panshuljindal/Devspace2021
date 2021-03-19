package com.panshul.devspace.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.panshul.devspace.Model.TaskModel;
import com.panshul.devspace.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    Context context;
    List<TaskModel> taskList;

    public TaskAdapter(Context context, List<TaskModel> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,info,timer;
        ImageView delete,check;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ItemTaskName);
            info= itemView.findViewById(R.id.ItemTaskContent);
            timer = itemView.findViewById(R.id.ItemTimerText);
            delete = itemView.findViewById(R.id.ItemDeleteImage);
            check = itemView.findViewById(R.id.ItemTickImage);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.task_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TaskModel item = taskList.get(position);
        holder.name.setText(item.getTaskName());
        holder.info.setText(item.getTaskContent());
        holder.timer.setText(item.getTime() +" Min");
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
    public void saveData(){
        SharedPreferences preferences = context.getSharedPreferences("com.panshul.devspace.tasklist", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(taskList);
        editor.putString("taskList",json);
        editor.apply();
    }


}

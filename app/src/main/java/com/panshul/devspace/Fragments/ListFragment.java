package com.panshul.devspace.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.panshul.devspace.Adapters.TaskAdapter;
import com.panshul.devspace.Model.TaskModel;
import com.panshul.devspace.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    List<TaskModel> taskList;
    ImageView add;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = view.findViewById(R.id.taskRecyclerView);
        add = view.findViewById(R.id.additionImageView);
        onClickListeners();
        taskList=new ArrayList<>();
        addData();
        loadData();
        adapter();
        saveData();
        return view;
    }
    public void addData(){
        taskList.add(new TaskModel("Task Name","Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Amet amet, morbi aliquam nisl fermentum volutpat " +
                "eleifend id donec. In velit posuere interdum quam volutpat.",
        50,"true"));
        taskList.add(new TaskModel("Task Name","Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Amet amet, morbi aliquam nisl fermentum volutpat " +
                "eleifend id donec. In velit posuere interdum quam volutpat.",
                50,"true"));
        taskList.add(new TaskModel("Task Name","Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Amet amet, morbi aliquam nisl fermentum volutpat " +
                "eleifend id donec. In velit posuere interdum quam volutpat.",
                50,"true"));
        taskList.add(new TaskModel("Task Name","Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Amet amet, morbi aliquam nisl fermentum volutpat " +
                "eleifend id donec. In velit posuere interdum quam volutpat.",
                50,"true"));
        taskList.add(new TaskModel("Task Name","Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Amet amet, morbi aliquam nisl fermentum volutpat " +
                "eleifend id donec. In velit posuere interdum quam volutpat.",
                50,"true"));
    }
    public void adapter(){
        TaskAdapter adapter = new TaskAdapter(view.getContext(),taskList);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
    public void onClickListeners(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskFragment fragment = new TaskFragment();
                FragmentManager manager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
    public void saveData(){
        SharedPreferences preferences = view.getContext().getSharedPreferences("com.panshul.devspace.tasklist", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(taskList);
        editor.putString("taskList",json);
        editor.apply();
    }
    public void loadData(){
        SharedPreferences preferences = view.getContext().getSharedPreferences("com.panshul.devspace.tasklist",Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("taskList","");
        Type type = new TypeToken<ArrayList<TaskModel>>() {}.getType();
        taskList =gson.fromJson(json,type);
        if(taskList==null){
            taskList =new ArrayList<>();
        }
    }
}
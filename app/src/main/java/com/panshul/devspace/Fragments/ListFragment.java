package com.panshul.devspace.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panshul.devspace.Adapters.TaskAdapter;
import com.panshul.devspace.Model.TaskModel;
import com.panshul.devspace.R;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    List<TaskModel> taskList;

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
        taskList=new ArrayList<>();
        addData();
        adapter();
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
}
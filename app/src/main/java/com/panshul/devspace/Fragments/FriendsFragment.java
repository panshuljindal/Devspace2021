package com.panshul.devspace.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panshul.devspace.Model.PlaylistModel;
import com.panshul.devspace.R;

import java.util.ArrayList;
import java.util.List;

public class FriendsFragment extends Fragment {

    View view;
    List<PlaylistModel> playList;
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_friends, container, false);

        playList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.playListRecyclerView);
        addData();
        adapter();


        return view;
    }
    public void addData(){

    }
    public void adapter(){
        //SongsAdapter adapter = new SongsAdapter(view.getContext(),playList,MusicFragment.this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }
}
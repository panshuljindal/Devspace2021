package com.panshul.devspace.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panshul.devspace.Adapters.SongsAdapter;
import com.panshul.devspace.Model.PlaylistModel;
import com.panshul.devspace.R;

import java.util.ArrayList;
import java.util.List;

public class MusicFragment extends Fragment {

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
        view= inflater.inflate(R.layout.fragment_music, container, false);

        playList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.playListRecyclerView);
        addData();
        adapter();


        return view;
    }
    public void addData(){
        playList.add(new PlaylistModel("PlayListName","efijnewufnhq","Playlist by Panshul"));
        playList.add(new PlaylistModel("PlayListName","efijnewufnhq","Playlist by Panshul"));
        playList.add(new PlaylistModel("PlayListName","efijnewufnhq","Playlist by Panshul"));
        playList.add(new PlaylistModel("PlayListName","efijnewufnhq","Playlist by Panshul"));
    }
    public void adapter(){
        SongsAdapter adapter = new SongsAdapter(view.getContext(),playList,MusicFragment.this);
        GridLayoutManager manager = new GridLayoutManager(view.getContext(),2);
        //LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
       // manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}
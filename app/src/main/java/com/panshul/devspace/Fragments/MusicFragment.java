package com.panshul.devspace.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.panshul.devspace.Adapters.SongsAdapter;
import com.panshul.devspace.Model.PlaylistModel;
import com.panshul.devspace.Model.TaskModel;
import com.panshul.devspace.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MusicFragment extends Fragment {

    View view;
    List<PlaylistModel> playList;
    RecyclerView recyclerView;
    ImageView plus,profile;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
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
        plus = view.findViewById(R.id.plusPlaylist);
        profile = view.findViewById(R.id.profileImageViewPlaylist);
        onClickListeners();

        pref = view.getContext().getSharedPreferences("com.panshul.devspace.music",Context.MODE_PRIVATE);
        editor = pref.edit();

        //addData();
        loadData();
        adapter();
        saveData();
        return view;
    }
    public void onClickListeners(){
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_Playlist_Fragment fragment = new Add_Playlist_Fragment();
                FragmentManager manager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void addData(){
        if (playList.isEmpty()) {
            playList.add(new PlaylistModel("Rap As a Poem", "efijnewufnhq", "Playlist by Panshul"));
            playList.add(new PlaylistModel("Rap As a Poem", "efijnewufnhq", "Playlist by Panshul"));
            playList.add(new PlaylistModel("Rap As a Poem", "efijnewufnhq", "Playlist by Panshul"));
            playList.add(new PlaylistModel("Rap As a Poem", "efijnewufnhq", "Playlist by Panshul"));
            adapter();
        }
        else {
            loadData();
        }
    }
    public void adapter(){
        SongsAdapter adapter = new SongsAdapter(view.getContext(),playList,MusicFragment.this);
        GridLayoutManager manager = new GridLayoutManager(view.getContext(),2);
        //LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
       // manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
    public void saveData(){
        SharedPreferences preferences = view.getContext().getSharedPreferences("com.panshul.devspace.spotifyList", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(playList);
        editor.putString("songs",json);
        editor.apply();
    }
    public void loadData(){
        if (playList.isEmpty()) {
            SharedPreferences preferences = view.getContext().getSharedPreferences("com.panshul.devspace.spotifyList", Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = preferences.getString("songs", "");
            Type type = new TypeToken<ArrayList<PlaylistModel>>() {
            }.getType();
            playList = gson.fromJson(json, type);
            if (playList == null) {
                playList = new ArrayList<>();
            }
        }else {
            addData();
        }
    }
}
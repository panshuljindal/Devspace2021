package com.panshul.devspace.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.panshul.devspace.Activity.Profile_Page;
import com.panshul.devspace.Adapters.FriendAdapter;
import com.panshul.devspace.Model.FriendsModel;
import com.panshul.devspace.Model.PlaylistModel;
import com.panshul.devspace.Model.TaskModel;
import com.panshul.devspace.Model.UidModel;
import com.panshul.devspace.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FriendsFragment extends Fragment {

    View view;
    List<FriendsModel> friendsList;
    RecyclerView recyclerView;
    SharedPreferences pref;
    List<String> uids;
    DatabaseReference myref,myref1;
    ImageView add,profile;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_friends, container, false);
        uids = new ArrayList<>();
        friendsList = new ArrayList<>();

        recyclerView = view.findViewById(R.id.friendRecyclerView);
        add = view.findViewById(R.id.friendsAddImageview);
        profile = view.findViewById(R.id.profile7);
        pref = view.getContext().getSharedPreferences("com.panshul.devspace.userdata", Context.MODE_PRIVATE);



        onClickListeners();
        loadData();
        addData();
        adapter();


        return view;
    }
    public void onClickListeners(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog();
                dialog.show( getFragmentManager(),"fragment");
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), Profile_Page.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }
    public void addData(){
        String uid = pref.getString("uid","");
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        myref = db.getReference("Room").child(uid).child("friends");
        myref1 = db.getReference("Users");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                uids.clear();
                for (DataSnapshot ds:snapshot.getChildren()){
                    UidModel model = ds.getValue(UidModel.class);
                    String uid1 = model.getUid();
                    uids.add(uid1);
                }
                myref1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        friendsList.clear();
                        for (String uid2:uids){
                            String name = snapshot.child(uid2).child("name").getValue().toString();
                            String score = snapshot.child(uid2).child("points").getValue().toString();
                            friendsList.add(new FriendsModel(name,score,uid2));
                        }
                        saveData();
                        adapter();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void adapter(){
        FriendAdapter adapter = new FriendAdapter(view.getContext(),friendsList);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
    public void saveData(){
        SharedPreferences preferences = view.getContext().getSharedPreferences("com.panshul.devspace.friends", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(friendsList);
        editor.putString("friendList",json);
        editor.apply();
    }
    public void loadData(){
        SharedPreferences preferences = view.getContext().getSharedPreferences("com.panshul.devspace.friends",Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("friendList","");
        Type type = new TypeToken<ArrayList<FriendsModel>>() {}.getType();
        friendsList =gson.fromJson(json,type);
        if(friendsList==null){
            friendsList =new ArrayList<>();
        }
    }
}
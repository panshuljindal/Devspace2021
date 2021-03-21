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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.panshul.devspace.Model.FriendsModel;
import com.panshul.devspace.R;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.MyViewHolder> {

    Context mcontext;
    List<FriendsModel> list1;
    String uid;

    public FriendAdapter(Context mcontext, List<FriendsModel> list1) {
        this.mcontext = mcontext;
        this.list1 = list1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,score;
        ImageView delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.frndName);
            score = itemView.findViewById(R.id.noOfSessions);
            delete = itemView.findViewById(R.id.friendDeleteImage);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.friend_list_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        FriendsModel item = list1.get(position);
        holder.name.setText(item.getName());
        holder.score.setText(item.getScore());
        SharedPreferences pref = mcontext.getSharedPreferences("com.panshul.devspace.userdata",Context.MODE_PRIVATE);
        uid = pref.getString("uid","");
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference myref = db.getReference("Room");

                myref.child(uid).child("friends").child(item.getFriendId()).child("uid").removeValue();
                myref.child(item.getFriendId()).child("friends").child(uid).child("uid").removeValue();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }


}

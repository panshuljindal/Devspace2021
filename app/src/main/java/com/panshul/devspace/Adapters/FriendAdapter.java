package com.panshul.devspace.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.panshul.devspace.Model.FriendsModel;
import com.panshul.devspace.R;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.MyViewHolder> {

    Context mcontext;
    List<FriendsModel> list1;

    public FriendAdapter(Context mcontext, List<FriendsModel> list1) {
        this.mcontext = mcontext;
        this.list1 = list1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,score;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.frndName);
            score = itemView.findViewById(R.id.noOfSessions);

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

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }


}

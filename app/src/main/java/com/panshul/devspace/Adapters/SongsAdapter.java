package com.panshul.devspace.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.panshul.devspace.Fragments.MusicFragment;
import com.panshul.devspace.Model.PlaylistModel;
import com.panshul.devspace.R;

import org.w3c.dom.Text;

import java.util.List;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.MyViewHolder> {

    Context context;
    List<PlaylistModel> playList;
    MusicFragment fragment;

    public SongsAdapter(Context context, List<PlaylistModel> playList, MusicFragment fragment) {
        this.context = context;
        this.playList = playList;
        this.fragment = fragment;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView playListName,playListBy;
        ConstraintLayout layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            playListBy = itemView.findViewById(R.id.playListBy11);
            playListName = itemView.findViewById(R.id.playListName11);
            layout = itemView.findViewById(R.id.playListConstraint);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.playlist_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        PlaylistModel item = playList.get(position);
        holder.playListBy.setText(item.getPlayListBy());
        holder.playListName.setText(item.getPlayListName());
    }

    @Override
    public int getItemCount() {
        return playList.size();
    }


}

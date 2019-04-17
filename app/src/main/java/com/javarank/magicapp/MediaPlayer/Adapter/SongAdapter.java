package com.javarank.magicapp.MediaPlayer.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.javarank.magicapp.MediaPlayer.Model.SongsInfo;
import com.javarank.magicapp.R;


import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {

    ArrayList<SongsInfo> songsInfos;
    Context context;
    private int lastCheckedPosition = -1;

    public SongAdapter(Context context,ArrayList<SongsInfo> songsInfos) {
        this.songsInfos = songsInfos;
        this.context = context;
    }

    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_list_items,parent,false);
        return new SongHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SongHolder holder, final int position) {

        final SongsInfo c = songsInfos.get(position);
        holder.name.setText(c.songName);
        holder.artist.setText(c.artistName);
        holder.action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null ){
                    onItemClickListener.onItemClick(holder.action,v,c,holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return songsInfos.size();
    }

    public class SongHolder extends RecyclerView.ViewHolder {

        TextView name,artist;
        Button action;
        public SongHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvSongName);
            artist = itemView.findViewById(R.id.tvArtistName);
            action = itemView.findViewById(R.id.btnPlay);

            action.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lastCheckedPosition = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });

        }
    }


    OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        public void onItemClick(final Button b,View view ,SongsInfo songInfo,int position);

    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

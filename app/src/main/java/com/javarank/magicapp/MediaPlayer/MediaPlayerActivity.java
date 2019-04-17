package com.javarank.magicapp.MediaPlayer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;

import com.javarank.magicapp.MainActivity;
import com.javarank.magicapp.MediaPlayer.Adapter.SongAdapter;
import com.javarank.magicapp.MediaPlayer.Model.SongsInfo;
import com.javarank.magicapp.R;

import java.io.IOException;
import java.util.ArrayList;

public class MediaPlayerActivity extends AppCompatActivity {
    ArrayList<SongsInfo> songs = new ArrayList<>();
    RecyclerView recyclerView;

    SongAdapter songAdapter;
    MediaPlayer mediaPlayer;
    //online version


    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);



        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Music Player");

        recyclerView = findViewById(R.id.recyclerView);
        songAdapter = new SongAdapter(this, songs);

        LinearLayoutManager lm = new LinearLayoutManager(this);
//        DividerItemDecoration did = new DividerItemDecoration(recyclerView.getContext(), lm.getOrientation());
        recyclerView.addItemDecoration(new DividerItemDecoration(MediaPlayerActivity.this, DividerItemDecoration.VERTICAL));;

        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(songAdapter);

        RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(this) {
            @Override protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }
        };




        songAdapter.setOnItemClickListener(new SongAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final Button b, View view, SongsInfo songInfo, int position) {
                try {
                    if (b.getBackground().getConstantState() == getResources().getDrawable(R.drawable.ic_stop).getConstantState()) {
                        b.setBackground(getResources().getDrawable(R.drawable.ic_play));
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    } else {
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setDataSource(songInfo.getSongUrl());
                        mediaPlayer.prepareAsync();
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                mp.start();
                                b.setBackground(getResources().getDrawable(R.drawable.ic_stop));
                            }
                        });
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


                recyclerView.smoothScrollToPosition(position);
                songAdapter.notifyDataSetChanged();
            }
        });

        CheckPermission();

    }




    private void CheckPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return;
            }
        } else {
            loadSongs();
        }
    }

    private void loadSongs() {
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                    String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                    SongsInfo s = new SongsInfo(name, artist, url);
                    songs.add(s);
                } while (cursor.moveToNext());
            }
            cursor.close();
            songAdapter = new SongAdapter(this, songs);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadSongs();
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    CheckPermission();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

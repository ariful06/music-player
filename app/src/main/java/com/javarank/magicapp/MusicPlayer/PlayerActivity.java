package com.javarank.magicapp.MusicPlayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.javarank.magicapp.R;

import java.io.File;
import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    static MediaPlayer mp;
    ArrayList<File> mySongs;
    ImageView btPlay, btFF, btBB, btNext, btPrevious;
    SeekBar seekBar;
    int position;
    Uri uri;
    int totalDuration;
    int currentPosition;
    Thread updateSeekbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Music Player");
        Intent i = getIntent();
        btPlay = findViewById(R.id.btnPlay);
        btFF = findViewById(R.id.btnForward);
        btBB = findViewById(R.id.btnBackward);
        btNext = findViewById(R.id.btnNext);
        btPrevious = findViewById(R.id.btnPrevious);
        seekBar = findViewById(R.id.seekbar);
        updateSeekbar = new Thread(){
            @Override
            public void run() {
                totalDuration = mp.getDuration();
                currentPosition = mp.getCurrentPosition();
                while (currentPosition <totalDuration){
                    try {
                        sleep(500);
                        currentPosition = mp.getCurrentPosition();
                        seekBar.setProgress(currentPosition);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                super.run();
            }
        };

        if (mp != null){
            mp.stop();
            mp.release();
        }
        Bundle b = i.getExtras();
        mySongs = (ArrayList) b.getParcelableArrayList("songlist");
        position = b.getInt("position", 0);

        uri = Uri.parse(String.valueOf(mySongs.get(position)));
        mp = MediaPlayer.create(getApplicationContext(), uri);
        mp.start();

        if (mp.isPlaying()){
            btPlay.setImageDrawable(getResources().getDrawable(R.drawable.ic_play));
        }else{
            btPlay.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop));
        }
        seekBar.setMax(mp.getDuration());
        updateSeekbar.start();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mp.seekTo(seekBar.getProgress());
            }
        });
        setOnClickListener();

    }

    public void setOnClickListener(){
        btPlay.setOnClickListener(this);
        btFF.setOnClickListener(this);
        btBB.setOnClickListener(this);
        btPrevious.setOnClickListener(this);
        btNext.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnPlay:
                if (mp.isPlaying()) {
                    btPlay.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop));
                    mp.pause();
                } else {
                    btPlay.setImageDrawable(getResources().getDrawable(R.drawable.ic_play));
                    mp.start();
                }
                break;

            case R.id.btnNext:
                mp.stop();
                mp.release();
                position = (position + 1) % mySongs.size();
                uri = Uri.parse(mySongs.get(position).toString());
                mp = MediaPlayer.create(getApplicationContext(), uri);
                mp.start();
                seekBar.setMax(mp.getDuration());
                break;

            case R.id.btnPrevious:
                mp.stop();
                mp.release();
                position = (position - 1 < 0) ? mySongs.size() - 1 : position - 1;
                uri = Uri.parse(mySongs.get(position).toString());
                mp = MediaPlayer.create(getApplicationContext(), uri);
                mp.start();
                seekBar.setMax(mp.getDuration());
                break;

            case R.id.btnBackward:
                mp.seekTo(mp.getCurrentPosition() - 5000);
                break;

            case R.id.btnForward:
                mp.seekTo(mp.getCurrentPosition() + 5000);
                break;

        }
    }


}

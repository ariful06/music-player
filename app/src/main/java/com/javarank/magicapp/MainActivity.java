package com.javarank.magicapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.javarank.magicapp.Adapter.TaskAdapter;
import com.javarank.magicapp.Calculator.CalculatorActivity;
import com.javarank.magicapp.MediaPlayer.MediaPlayerActivity;

import java.util.Random;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    RecyclerView recyclerView;


    Toolbar toolbar;
    int randomNum;
    int minimum =1;
    int maximum = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        randomNum = randomGenerator(minimum,maximum);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Home");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));;
        runAnimation(recyclerView,randomNum);
    }

    private int randomGenerator(int minimum, int maximum) {
        Random rand = new Random();
        int result = minimum + rand.nextInt((maximum - minimum) + 1);
        return result;
    }

    private void runAnimation(RecyclerView recyclerView, int type) {
        Context context = recyclerView.getContext();
        LayoutAnimationController controller = null;
        if (type == 1){
            controller = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_animation_from_right);
        }else if (type ==2){
            controller = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_animation_from_bottom);
        }else if (type ==3){
            controller = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_animation_fall_down);
        }

        TaskAdapter adapter = new TaskAdapter();
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    @Override
    public void onClick(View view) {

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

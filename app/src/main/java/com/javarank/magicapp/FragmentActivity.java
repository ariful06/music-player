package com.javarank.magicapp;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.javarank.magicapp.Fragment.FirstFragment;
import com.javarank.magicapp.Fragment.SecondFragment;
import com.javarank.magicapp.Fragment.ThiredFragment;

public class FragmentActivity extends AppCompatActivity {


    Button fragmentOne, fragmentTwo, fragmentThree;
    Fragment mainFragment = null;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        fragmentOne = findViewById(R.id.fragmanet_one);
        fragmentTwo = findViewById(R.id.fragmanet_two);
        fragmentThree = findViewById(R.id.fragmanet_three);

        bundle = new Bundle();
        mainFragment = new Fragment();
        mainFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container,mainFragment);
        fragmentTransaction.commit();

        fragmentOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putInt("first",1234254);
            }
        });

        fragmentTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainFragment = new SecondFragment();
                bundle.putInt("second",2345);
            }
        });
        fragmentThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainFragment = new ThiredFragment();
                bundle.putInt("Third",64564);
            }
        });

    }
}

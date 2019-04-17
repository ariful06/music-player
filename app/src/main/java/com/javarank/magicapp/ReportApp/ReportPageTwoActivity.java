package com.javarank.magicapp.ReportApp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.javarank.magicapp.R;

import butterknife.ButterKnife;
import butterknife.BindView;
import es.dmoral.toasty.Toasty;


public class ReportPageTwoActivity extends AppCompatActivity {
    @BindView(R.id.text_view_user_name)
    TextView userName;
    @BindView(R.id.text_view_email)
    TextView email;
    Button exitButton,editProfile;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_page_two);
        ButterKnife.bind(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Report");

        editProfile = findViewById(R.id.eidt);
        Intent getDetails = getIntent();
        String name = getDetails.getStringExtra("user_name");
        String emailAdress = getDetails.getStringExtra("email");
        userName.setText(name);
        email.setText(emailAdress);
        editor = getSharedPreferences("UserInfo", MODE_PRIVATE).edit();
        editor.putString("user_name",name);
        editor.putString("email",emailAdress);
        editor.apply();
        exitButton = findViewById(R.id.exit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toasty.success(ReportPageTwoActivity.this, "Successfully Exited!", Toast.LENGTH_SHORT, true).show();
                finishAffinity();
            }
        });

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

package com.javarank.magicapp.ReportApp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.javarank.magicapp.MainActivity;
import com.javarank.magicapp.R;
import com.javarank.magicapp.ReportApp.Callback.ApiClient;
import com.javarank.magicapp.ReportApp.Callback.ApiInterface;
import com.javarank.magicapp.ReportApp.Fragments.LoginFragment;
import com.javarank.magicapp.ReportApp.Fragments.SignupFragment;
import com.javarank.magicapp.ReportApp.Helper.PrefConfig;

public class ReportActivity extends AppCompatActivity implements LoginFragment.OnLoginFromActivityListener {
    public static PrefConfig prefConfig;
    FrameLayout container;

    public static ApiInterface apiInterface;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        container = findViewById(R.id.fragment_cotainer);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        prefConfig = new PrefConfig(this);
        if (container != null) {
            if (savedInstanceState != null) {
                return;
            }
            if (prefConfig.readLoginStatus()) {
//                getSupportFragmentManager().beginTransaction().add(R.id.fragment_cotainer, new WelcomeFragment()).commit();
                Intent intent = new Intent(ReportActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_cotainer, new LoginFragment()).commit();
            }
        }
    }

    @Override
    public void performSignup() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cotainer, new SignupFragment()).addToBackStack(null).commit();
    }

    @Override
    public void performLogin(String email) {
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cotainer,new WelcomeFragment()).commit();
        Intent intent = new Intent(ReportActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

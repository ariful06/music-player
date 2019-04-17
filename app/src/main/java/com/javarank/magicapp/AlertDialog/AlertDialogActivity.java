package com.javarank.magicapp.AlertDialog;


import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.javarank.magicapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class AlertDialogActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.showDialog)
    Button showDialogButton;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        ButterKnife.bind(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Alert Dialog");
        showDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AlertDialogActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.alert_dialog,null);

                final EditText etEmail = mView.findViewById(R.id.edit_text_email);
                final EditText etPassword = mView.findViewById(R.id.edit_text_password);
                loginButton = mView.findViewById(R.id.loginButton);


                builder.setView(mView);
                final AlertDialog dialog = builder.create();
                dialog.show();
                loginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!etEmail.getText().toString().equals("") && !etPassword.getText().toString().equals("")){
                            if (etEmail.getText().toString().equals("ariful@gmail.com") && etPassword.getText().toString().equals("2662")){
                                Toasty.success(AlertDialogActivity.this,"Successfully login", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }else{
                            if (etEmail.getText().toString().equals("")){
                                Toasty.error(AlertDialogActivity.this,"Please Enter Your Email", Toast.LENGTH_SHORT).show();
                            }
                            if (etPassword.getText().toString().equals("")){
                                Toasty.error(AlertDialogActivity.this,"Please Enter Your Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
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

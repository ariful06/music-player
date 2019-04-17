package com.javarank.magicapp.ReportApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.javarank.magicapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowReportActivity extends AppCompatActivity {


    @BindView(R.id.edit_text_name)
    EditText editTextName;
    @BindView(R.id.edit_text_email)
    EditText editTextEmail;
    @BindView(R.id.edit_text_password)
    EditText editTextPassword;
    @BindView(R.id.edit_text_confirm_password)
    EditText editTextConfirmPassword;

    @BindView(R.id.loginButton)
    Button loginButton;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    EditText userName,email,password,confirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_report);
        ButterKnife.bind(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Report");


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextName.getText().toString().trim().equals("")){
                    editTextName.setError("Please enter User Name");
                }else{
                    if (editTextEmail.getText().toString().equals("")){
                        editTextEmail.setError("Please enter Email");
                    }else{
                        if (editTextPassword.getText().toString().equals("")){
                            editTextPassword.setError("Please enter Password");
                        }else{
                            if (editTextConfirmPassword.getText().toString().equals("")){
                                editTextConfirmPassword.setError("Please enter Confirm Password");
                            }
                            final String USER_NAME = editTextName.getText().toString().trim();
                            final String USER_EMAIL = editTextEmail.getText().toString().trim();
                            final String PASSWORD = editTextPassword.getText().toString();
                            final String CONFIRM_PASSWORD = editTextConfirmPassword.getText().toString();
                            Intent intent = new Intent(ShowReportActivity.this,ReportPageTwoActivity.class);
                            intent.putExtra("user_name",USER_NAME);
                            intent.putExtra("email",USER_EMAIL);
                            intent.putExtra("password",PASSWORD);
                            intent.putExtra("confirm_password",CONFIRM_PASSWORD);
                            startActivity(intent);
                        }
                    }
                }
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

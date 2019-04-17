package com.javarank.magicapp.Test.SharedPregerence;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.javarank.magicapp.R;

import es.dmoral.toasty.Toasty;

public class TestSharedPregerence extends AppCompatActivity {

    EditText name,email,password,age;
    Button button;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_shared_pregerence);

        name = findViewById(R.id.edit_text_name);
        email = findViewById(R.id.edit_text_email);
        password = findViewById(R.id.edit_text_phone);
        age = findViewById(R.id.edit_text_age);

        button = findViewById(R.id.save_button);

        editor = getSharedPreferences("MyPref",MODE_PRIVATE).edit();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringName = name.getText().toString();
                String stringEmail = email.getText().toString();
                String stringPassword = password.getText().toString();
                String stringAge = age.getText().toString();

                editor.putString("user_name",stringName);
                editor.putString("email",stringEmail);
                editor.putString("password",stringPassword);
                editor.putString("age",stringAge);
                editor.commit();
                Toasty.success(TestSharedPregerence.this,"Successfully Saved to the Shared Preference", Toast.LENGTH_LONG).show();

            }
        });


    }
}

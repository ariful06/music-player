package com.javarank.magicapp.SharedPreference;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.javarank.magicapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class SharedPreferenceActivity extends AppCompatActivity {

    @BindView(R.id.etText)
    EditText editText;

    @BindView(R.id.save)
    Button save;
    @BindView(R.id.showText)
    TextView showSavedData;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);
        ButterKnife.bind(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Shared Preference");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (save.getText().toString().equals("Save")){
                    String text = editText.getText().toString();
                    if (!text.equals("")){
                        SharedPreferences.Editor editor = getSharedPreferences("SharedPref", MODE_PRIVATE).edit();
                        editor.putString("text",text);
                        editor.apply();
                        Toasty.success(SharedPreferenceActivity.this,"Successfully saved in shared preference", Toast.LENGTH_LONG).show();
                    }else{
                        Toasty.error(SharedPreferenceActivity.this,"Field is empty", Toast.LENGTH_LONG).show();
                    }
                    save.setText("Show Now");
                }
                else if (save.getText().toString().equals("Show Now")){
                    SharedPreferences prefs = getSharedPreferences("SharedPref", MODE_PRIVATE);


                        showSavedData.setText(prefs.getString("text", ""));
                        save.setText("Save");
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

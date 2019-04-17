package com.javarank.magicapp.Toast;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.javarank.magicapp.R;

import es.dmoral.toasty.Toasty;

public class ToastActivity extends AppCompatActivity {


    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Different Toasts");

        String[] family = new String[]{"Fazlul Haque","Fatema Begum","Mahbubul Haque","Ariful Haque","Fahmida Haque","Nahida Haque"};

        ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,family);
        final ListView listViewFamily = findViewById(R.id.listViewFamiliy);

        listViewFamily.setAdapter(adapter);

        listViewFamily.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                int itemPosition = position;
                String value = (String) listViewFamily.getItemAtPosition(position);

                switch (value){
                    case "Fazlul Haque":
                        Toasty.custom(ToastActivity.this, value+" is my Father", R.drawable.ic_user, getResources().getColor(R.color.colorPrimary), Toast.LENGTH_LONG,true,true).show();
                        break;

                    case "Fatema Begum":
                        Toasty.info(ToastActivity.this,value+" is my mother",R.drawable.ic_user).show();
                        break;

                    case "Mahbubul Haque":
                        Toasty.info(ToastActivity.this,value+" is my Brother",R.drawable.ic_user).show();
                        break;

                    case "Ariful Haque":
                        Toasty.error(ToastActivity.this,"This is me "+value,R.drawable.ic_user).show();
                        break;

                        case "Fahmida Haque":
                        Toasty.warning(ToastActivity.this,value+" is my younger Sister",R.drawable.ic_user).show();
                        break;

                    case "Nahida Haque":
                        Toasty.info(ToastActivity.this,value+" is my little Start",R.drawable.ic_user).show();
                        break;

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

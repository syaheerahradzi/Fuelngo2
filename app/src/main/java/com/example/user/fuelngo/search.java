package com.example.user.fuelngo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class search extends AppCompatActivity {

    EditText etsearch;
    Spinner spinbrand;
    CheckBox restroom, Atm, prayerroom, store;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addItemOnSpinner();

        etsearch= (EditText) findViewById(R.id.etsearch);
        spinbrand = (Spinner) findViewById(R.id.spinbrand);
        restroom = (CheckBox) findViewById(R.id.restroom);
        Atm = (CheckBox) findViewById(R.id.Atm);
        prayerroom = (CheckBox) findViewById(R.id.prayerroom);
        store = (CheckBox) findViewById(R.id.store);
        btnSearch = (Button) findViewById(R.id.btnsearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(search.this, searchresult.class));
            }
        });
    }

    public void addItemOnSpinner(){
        spinbrand = (Spinner) findViewById(R.id.spinbrand);
    }

}



package com.example.user.fuelngo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity  extends AppCompatActivity {

    private ImageButton imgnearby, imgsearch, imgaddlocation, imgaddevent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgnearby = (ImageButton) findViewById(R.id.imgnearby);
        imgsearch = (ImageButton) findViewById(R.id.imgsearch);
        imgaddlocation = (ImageButton) findViewById(R.id.imgaddlocation);
        imgaddevent = (ImageButton) findViewById(R.id.imgaddevent);

        imgnearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapsActivity.class));

            }
        });

        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, search.class));

            }
        });

        imgaddlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, addlocation.class));

            }
        });

        imgaddevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, event.class));

            }
        });

    }
}


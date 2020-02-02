package com.example.mygpschatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button messageButton;
    Button mapButton;
    Button textViewButton;

    //runs as the default activity so app opens on this home page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageButton = findViewById(R.id.messageButton);
        mapButton = findViewById(R.id.mapButton);
        textViewButton = findViewById(R.id.textViewButton);

        //when the send message button is clicked it start the message activity
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, MessageActivity.class);
                startActivity(myIntent);
            }
        });

        //when the map view button is clicked it start the maps activity
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, MapsActivity2.class);
                startActivity(myIntent);
            }
        });

        //when the text view button is clicked it start the text view activity
        textViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, TextViewActivity.class);
                startActivity(myIntent);
            }
        });


    }
}

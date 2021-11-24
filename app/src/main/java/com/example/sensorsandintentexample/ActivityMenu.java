package com.example.sensorsandintentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class ActivityMenu extends AppCompatActivity {

    private MaterialButton menu_BTN_accelerometer;
    private MaterialButton menu_BTN_light;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menu_BTN_accelerometer = findViewById(R.id.menu_BTN_accelerometer);
        menu_BTN_light = findViewById(R.id.menu_BTN_light);
        menu_BTN_accelerometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame("ACC");
            }
        });
        menu_BTN_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame("LIGHT");
            }
        });

    }

    private void startGame(String sensor) {
        Intent intent = new Intent(this, ActivityGame.class);
        Bundle bundle = new Bundle();
        bundle.putString(ActivityGame.SENSOR_TYPE,sensor);
        bundle.putString(ActivityGame.NAME,"sensor");
        intent.putExtra("bundle",bundle);
        startActivity(intent);
//        finish();
    }
}
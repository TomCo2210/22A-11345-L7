package com.example.sensorsandintentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.google.android.material.textview.MaterialTextView;

import java.text.DecimalFormat;

public class ActivityGame extends AppCompatActivity {
    public static final String SENSOR_TYPE = "SENSOR_TYPE";
    public static final String NAME = "NAME";
    private MaterialTextView game_LBL_title;

    private SensorManager sensorManager;
    private Sensor sensor;

    private SensorEventListener accSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            DecimalFormat df = new DecimalFormat("##.##");
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            game_LBL_title.setText("X: " + df.format(x)+ "\nY: "+df.format(y)+ "\nZ: "+df.format(z));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        game_LBL_title = findViewById(R.id.game_LBL_title);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        game_LBL_title.setText(bundle.getString(SENSOR_TYPE));

        initSensor();
    }


    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(accSensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(accSensorEventListener);
    }

    private void initSensor() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public boolean isSensorExists(int sensorType) {
        return (sensorManager.getDefaultSensor(sensorType) != null);
    }
}
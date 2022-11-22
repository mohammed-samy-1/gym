package com.example.mygym_moSamy.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.widget.Button;
import android.widget.Toast;

import com.example.mygym_moSamy.R;
import com.example.mygym_moSamy.DataBasesAndModels.Training;
import com.example.mygym_moSamy.DataBasesAndModels.TrainingDB;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    Button btnPlans , btnAllTrainings,btnAbout;
    TrainingDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        btnAllTrainings.setOnClickListener(v -> {



            Intent intent = new Intent(MainActivity.this,AllTrainingsActivity.class);
            startActivity(intent);
        });

        btnPlans.setOnClickListener(v -> {
            Intent intent= new Intent(MainActivity.this ,PlanActivity.class);
            startActivity(intent);
        });
    }

    private void initViews() {
        Log.d(TAG, "initViews: started");
        btnPlans =findViewById(R.id.btnPlans);
        btnAllTrainings =findViewById(R.id.btnAllActivities);
        btnAbout =findViewById(R.id.btnAbout);
        db = new TrainingDB(this);
    }
    // TODO: 4/19/2021 try to add in the background or use room

}
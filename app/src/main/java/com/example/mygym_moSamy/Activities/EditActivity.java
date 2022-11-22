package com.example.mygym_moSamy.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mygym_moSamy.Adapter.PlanAdapter;
import com.example.mygym_moSamy.DataBasesAndModels.Plan;
import com.example.mygym_moSamy.DataBasesAndModels.TrainingDB;
import com.example.mygym_moSamy.R;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity implements PlanAdapter.RemovePlan {
    TextView txtName;
    Button btnAdd ;
    RecyclerView recyclerView;
    TrainingDB db;
    ArrayList<Plan> plansByDay;
    PlanAdapter adapter;

    @Override
    protected void onResume() {
        super.onResume();
        notifyPlanChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        notifyPlanChanged();
    }

    private  void notifyPlanChanged() {
        initViews();
        Intent intent = getIntent();
        if (null!= intent){
            String day = intent.getStringExtra("day");
            if (null!= day ){
                plansByDay = db.getPlansByDay(day);
                recyclerView.setAdapter(adapter);
                adapter.setPlans(plansByDay);
                recyclerView.setLayoutManager(new GridLayoutManager(EditActivity.this,2));
            }
        }
        btnAdd.setOnClickListener(v -> {
            Intent intent1= new Intent(EditActivity.this, AllTrainingsActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
        });
    }

    private  void initViews() {
        txtName = findViewById(R.id.txtDay);
        btnAdd = findViewById(R.id.btnAddMorePlans);
        recyclerView = findViewById(R.id.rvEdit);
        db =new TrainingDB(this);
        adapter =new PlanAdapter(this);
        adapter.setType("edit");
    }

    @Override
    public void onRemovePlanResult(Plan plan) {
        TrainingDB trainingDB = new TrainingDB(this);
        if (trainingDB.deletePlan(plan)){
            Toast.makeText(this,"plan deleted successfully", Toast.LENGTH_SHORT).show();
            adapter.setPlans(trainingDB.getPlansByDay(plan.getDay()));
            notifyPlanChanged();
            adapter.notifyDataSetChanged();
        }
    }
}
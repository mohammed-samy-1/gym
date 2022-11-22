package com.example.mygym_moSamy.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import com.example.mygym_moSamy.Adapter.PlanAdapter;
import com.example.mygym_moSamy.DataBasesAndModels.Plan;
import com.example.mygym_moSamy.DataBasesAndModels.TrainingDB;
import com.example.mygym_moSamy.R;

import java.util.ArrayList;

public class PlanActivity extends AppCompatActivity {

    private TextView editMon , editTue, editWed,editThr,editFri,editSat ,editSun;
    private RecyclerView rvMon , rvTue, rvWed,rvThr ,rvFri ,rvSat ,rvSun;
    private ConstraintLayout noPlansLayout;
    private NestedScrollView scrollView;
    private Button btnAddOne;

    TrainingDB trainingDB;
    ArrayList<Plan> plans;

    PlanAdapter monAdapter,tueAdapter ,wedAdapter, thrAdapter,friAdapter,satAdapter,sunAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        initViews();
        checkIfListIsEmpty();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        initViews();
        checkIfListIsEmpty();
    }

    private void checkIfListIsEmpty() {
        if (plans != null) {
            if (plans.isEmpty()) {
                noPlansLayout.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.GONE);
                btnAddOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PlanActivity.this, AllTrainingsActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
            } else {
                noPlansLayout.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                initRV();
                editOnClicks();
            }
        }else {
            btnAddOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlanActivity.this, AllTrainingsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
        }
    }

    private void editOnClicks() {
        Intent intent = new Intent(PlanActivity.this,EditActivity.class);
        editMon.setOnClickListener(v -> {
            intent.putExtra("day", "monday");
            startActivity(intent);
        });

        editTue.setOnClickListener(v -> {
            intent.putExtra("day", "tuesday");
            startActivity(intent);
        });

        editWed.setOnClickListener(v -> {
            intent.putExtra("day", "wednesday");
            startActivity(intent);
        });

        editThr.setOnClickListener(v -> {
            intent.putExtra("day", "thursday");
            startActivity(intent);
        });

        editFri.setOnClickListener(v -> {
            intent.putExtra("day", "friday");
            startActivity(intent);
        });

        editSat.setOnClickListener(v -> {
            intent.putExtra("day", "saturday");
            startActivity(intent);
        });

        editSun.setOnClickListener(v -> {
            intent.putExtra("day", "sunday");
            startActivity(intent);
        });

    }

    private void initRV() {
        monAdapter = new PlanAdapter(this);
        rvMon.setAdapter(monAdapter);
        rvMon.setLayoutManager(new LinearLayoutManager(this , RecyclerView.HORIZONTAL,false));
        monAdapter.setPlans(getPlansByDay("monday"));

        tueAdapter = new PlanAdapter(this);
        rvTue.setAdapter(tueAdapter);
        rvTue.setLayoutManager(new LinearLayoutManager(this , RecyclerView.HORIZONTAL,false));
        tueAdapter.setPlans(getPlansByDay("tuesday"));

        wedAdapter = new PlanAdapter(this);
        rvWed.setAdapter(wedAdapter);
        rvWed.setLayoutManager(new LinearLayoutManager(this , RecyclerView.HORIZONTAL,false));
        wedAdapter.setPlans(getPlansByDay("wednesday"));

        thrAdapter = new PlanAdapter(this);
        rvThr.setAdapter(thrAdapter);
        rvThr.setLayoutManager(new LinearLayoutManager(this , RecyclerView.HORIZONTAL,false));
        thrAdapter.setPlans(getPlansByDay("Thursday"));

        friAdapter = new PlanAdapter(this);
        rvFri.setAdapter(friAdapter);
        rvFri.setLayoutManager(new LinearLayoutManager(this , RecyclerView.HORIZONTAL,false));
        friAdapter.setPlans(getPlansByDay("Friday"));

        satAdapter = new PlanAdapter(this);
        rvSat.setAdapter(satAdapter);
        rvSat.setLayoutManager(new LinearLayoutManager(this , RecyclerView.HORIZONTAL,false));
        satAdapter.setPlans(getPlansByDay("Saturday"));


    }

    // TODO: 4/22/2021 Remove this and use the one from the data base
    public  ArrayList<Plan> getPlansByDay(String day){
        ArrayList<Plan> allPlans = trainingDB.getAllPlans();
        ArrayList<Plan> plans = new ArrayList<>();
        for (Plan p :allPlans){
            if (p.getDay().equalsIgnoreCase(day) ){
                plans.add(p);
            }
        }

        return plans;
    }

    @Override
    public void onBackPressed() {
        Intent intent= new Intent(PlanActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void initViews() {
        editMon = findViewById(R.id.editMon);
        editTue = findViewById(R.id.edittuePlan);
        editWed = findViewById(R.id.editWedPlan);
        editThr = findViewById(R.id.editThuPlan);
        editFri = findViewById(R.id.editFri);
        editSat = findViewById(R.id.editsat);
        editSun = findViewById(R.id.editSun);

        rvMon = findViewById(R.id.rvMonPlan);
        rvTue = findViewById(R.id.rvTuePlan);
        rvWed = findViewById(R.id.rvWed);
        rvThr = findViewById(R.id.rvThrPlan);
        rvFri = findViewById(R.id.rvFri);
        rvSat = findViewById(R.id.rvSat);
        rvSun = findViewById(R.id.rvSun);

        noPlansLayout =findViewById(R.id.noPlanLayout);

        scrollView = findViewById(R.id.scrolView);

        btnAddOne =findViewById(R.id.btnAddOne);

        trainingDB = new TrainingDB(this);
        plans = trainingDB.getAllPlans();
    }
}
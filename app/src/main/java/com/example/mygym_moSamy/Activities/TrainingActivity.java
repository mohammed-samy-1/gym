package com.example.mygym_moSamy.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mygym_moSamy.DataBasesAndModels.Plan;
import com.example.mygym_moSamy.DataBasesAndModels.PlanDetailDialog;
import com.example.mygym_moSamy.DataBasesAndModels.TrainingDB;
import com.example.mygym_moSamy.R;
import com.example.mygym_moSamy.DataBasesAndModels.Training;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class TrainingActivity extends AppCompatActivity implements PlanDetailDialog.PassPlanInterface {
    public static final String TRAINING_KEY = "TrainingKey";
    TextView tvName ,tvLD;
    ImageView imageView;
    Button btnADd ;
    TrainingDB trainingDB ;

    private static final String TAG = "TrainingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        initViews();
        Intent intent = getIntent();
        if (null != intent) {
            Training training = intent.getParcelableExtra(TRAINING_KEY);
            if (null != training){
                tvName.setText(training.getName());
                tvLD.setText(training.getLongDesc());
                Glide.with(TrainingActivity.this)
                        .asGif()
                        .load(training.getImageUrl())
                        .into(imageView);
                // TODO: 4/20/2021 make onClick listener
                btnADd.setOnClickListener(v -> {
                    PlanDetailDialog dialog = new PlanDetailDialog();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(TRAINING_KEY, training);
                    dialog.setArguments(bundle);
                    dialog.show(getSupportFragmentManager(),"plan Details");
                });
            }
        }else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void initViews() {
        Log.d(TAG, "initViews: started");
        tvName =findViewById(R.id.trainingName);
        tvLD =findViewById(R.id.LD);
        imageView =findViewById(R.id.imageView2);
        btnADd =findViewById(R.id.btnAdd2MyPlan);
        trainingDB = new TrainingDB(this);
    }

    @Override
    public void getPlan(Plan Plan) {
        if (trainingDB.addPlan(Plan)){
            Toast.makeText(this, "Plan added", Toast.LENGTH_SHORT).show();
            // TODO: 4/20/2021 navigate the user to plan Activity
            Intent intent = new Intent(this, PlanActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
package com.example.mygym_moSamy.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mygym_moSamy.Adapter.TrainingAdapter;
import com.example.mygym_moSamy.R;
import com.example.mygym_moSamy.DataBasesAndModels.Training;
import com.example.mygym_moSamy.DataBasesAndModels.TrainingDB;

import java.util.ArrayList;

public class AllTrainingsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TrainingDB db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_trainings);

        createTheList();
        db = new TrainingDB(this);
        ArrayList<Training> allTrainings = db.getAllTrainings();

        recyclerView= findViewById(R.id.RV1);
        TrainingAdapter trainingAdapter = new TrainingAdapter(this);
        recyclerView.setAdapter(trainingAdapter);
        trainingAdapter.setTrainings(allTrainings);
        trainingAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new GridLayoutManager(this ,2));

    }

    @Override
    public void onBackPressed() {
        Intent intent =new Intent(AllTrainingsActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    //add trainings;
    private void createTheList() {
        db = new TrainingDB(this);
        // TODO: 4/22/2021 add male picUrl later
        Training walking = new Training(getString(R.string.walkingName),getString(R.string.walkingLd),getString(R.string.walkingSd), getString(R.string.walkingUrl));
        Training intervalTraining = new Training(getString(R.string.intInterval_training),"Whether you're a beginner or an exercise veteran, a walker or an aerobic dancer, adding interval training to your cardiovascular workout will boost your fitness level and help you lose weight.","Whether you're a beginner or an exercise veteran...",getString(R.string.IntervalTrainingURL));
        Training training3 = new Training("Squats","Strength training is essential, the experts say. \"The more muscular fitness you have,\" says Cotton, \"the greater the capacity you have to burn calories.\"\n" +
                "And our experts tended to favor strength-training exercises that target multiple muscle groups. Squats, which work the quadriceps, hamstrings, and gluteals, are an excellent example.","Strength training is essential, the experts say...",getString(R.string.squatImageUrl));
        Training training4 = new Training("Lunges","Like squats, lunges work all the major muscles of the lower body: gluteals, quadriceps, and hamstrings.\n" +
                "\n" +
                "A lunge is a great exercise because it mimics life, it mimics walking,\" only exaggerated, says Petersen.","Like squats, lunges work all the major muscles of the lower body:...",getString(R.string.lugesImageURl));
        Training training5 = new Training(" Push-ups","If done correctly, the push-up can strengthen the chest, shoulders, triceps, and even the core trunk muscles, all at one time.\n" +
                "\n" +
                "\"I'm very much into planking exercises, almost yoga-type moves,\" says Petersen. \"Anytime you have the pelvis and the core [abdominals and back] in a suspended position, you have to rely on your own adherent strength to stabilize you.\"\n" +
                "\n","If done correctly, the push-up can strengthen the chest, shoulders...",getString(R.string.pushUpsImageUrl));
        Training training6 = new Training(getString(R.string.Abdominal_Crunches),getString(R.string.adnominalLd),getString(R.string.anominalSd),getString(R.string.abominalURL));
         Training training7 = new Training("رفع الجانبي الخلفي بالدمبل\n","قد يجادل البعض في أن الجانب الجانبي المنحني بيد واحدة – مما يسمح لك بتركيز كل جهودك جنبًا إلى جنب في وقت واحد – أفضل من النسخة ذات السلاحين. نحن لا نتفق. تزيد النسخة الأحادية من القدرة على الغش ، مما يسمح لك بتدوير الخصر أكثر عند التمزق. يؤدي القيام بذراعيك في نفس الوقت إلى تقليل هذا النوع من السرعة ، مما يضع المزيد من الضغط على الحمل الخلفي بحيث يمكن أن يحمل الحمل.\n" +
                 "\n" +
                 "المنطقة الرئيسية المستهدفة:  دالية الكتف الخلفية\n" +
                 "\n" +
                 "نقاط القوة:\n" +
                 "تمديد الانحناء متعدد الاستخدامات ويمكن القيام به عن طريق الوقوف أو الجلوس في نهاية مقعد مسطح مع ثني ركبتيك. واستخدام الدمبل يعني أن العضلات الأخرى تلعب دورًا لتحقيق الاستقرار – مما قد يعني أنه ليس لديك الكثير من التأخير الخلفي ولكن يساعد على بناء جسم أكثر وظيفية بشكل عام.","مع وجود دمبل في كل يد وصدرك لأعلى ، وظهر مسطح ، وركبتين مثنيتين قليلاً ","https://learningjoan.com/wp-content/uploads/2020/04/Bent-Over-Dumbbell-Lateral-Raise.gif");

        // TODO: 4/19/2021 Create onClick listeners
        if (db.getAllTrainings().isEmpty()) {
            try {
                db.addTraining(walking);
                db.addTraining(intervalTraining);
                db.addTraining(training3);
                db.addTraining(training4);
                db.addTraining(training5);
                db.addTraining(training6);
                db.addTraining(training7);
            }catch (Exception e){
                Toast.makeText(this, "something wrong", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
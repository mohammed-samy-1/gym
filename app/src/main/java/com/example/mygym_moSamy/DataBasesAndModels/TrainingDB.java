package com.example.mygym_moSamy.DataBasesAndModels;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.ArrayList;

public class TrainingDB extends SQLiteOpenHelper {

    public static final String TRAININGS_DB = "TrainingsDB";
    public static final String TRAININGS_TABLE = "TRAININGS_TABLE";
    public static final String SHORTDESC = "SHORTDESC";
    public static final String LONGDESC = "LONGDESC";
    public static final String IMAGEURL = "IMAGEURL";
    public static final String NAME = "NAME";
    public static final String PLANS_TABLE = "plans_table";
    public static final String TRAINING = "training";
    public static final String MIN = "min";
    public static final String DAY = "day";
    public static final String IS_ACCOMPLISHED = "isAccomplished";
    ArrayList<Plan> plans;
    Gson gson;

    public TrainingDB(@Nullable Context context) {
        super(context, TRAININGS_DB, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " +TRAININGS_TABLE+ " ( ID INTEGER PRIMARY KEY AUTOINCREMENT , " + NAME + " TEXT ,  " + SHORTDESC + " TEXT , " + LONGDESC + " TEXT ,  " + IMAGEURL + " TEXT )";
        String queryPlans= "CREATE TABLE " + PLANS_TABLE+ " ( ID INTEGER PRIMARY KEY AUTOINCREMENT , " + TRAINING + " TEXT , " + MIN + " INTEGER , " + DAY + " TEXT , " + IS_ACCOMPLISHED + " INTEGER)";
        db.execSQL(query);
        db.execSQL(queryPlans);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TRAININGS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PLANS_TABLE);
        onCreate(db);
    }

    // adding a plan to the date base
    public boolean addPlan (Plan Plan){
        SQLiteDatabase database  = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        gson = new Gson();
        // convert to string
        String training = gson.toJson(Plan.getTraining());
        try {
            values.put(TRAINING , training);
            values.put(MIN , Plan.getMin());
            values.put(DAY , Plan.getDay());
            values.put(IS_ACCOMPLISHED, Plan.isAccomplished());
            long insert = database.insert(PLANS_TABLE, null, values);
            database.close();
            return insert != -1;
        }catch (Exception e){
            e.printStackTrace();
            database.close();
            return false;
        }

    }
    public boolean deletePlan (Plan plan){
        // TODO: 4/22/2021 delete this
        SQLiteDatabase db= this.getWritableDatabase();
        String query = "DELETE FROM "+ PLANS_TABLE + " WHERE ID = "+ plan.getId() ;
        Cursor cursor = db.rawQuery(query, null);
        return cursor.moveToFirst();
    }

    public  ArrayList<Plan> getAllPlans(){
        plans = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        String query ="SELECT *  FROM "+PLANS_TABLE;
        Cursor cursor = database.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String training = cursor.getString(1);
                gson = new Gson();
                Training training1 = gson.fromJson(training,Training.class);
                int min = cursor.getInt(2);
                String day = cursor.getString(3);
                boolean isAccomplished = cursor.getInt(4) == 1;
                Plan plan = new Plan(training1,min,day,isAccomplished);
                plan.setId(id);
                plans.add(plan);
            }while (cursor.moveToNext());
            cursor.close();
        }
        database.close();
        return plans;

    }
    public void addTraining(Training training){
        SQLiteDatabase database = TrainingDB.this.getWritableDatabase();
        ContentValues values = new ContentValues();
        try {
            values.put(NAME , training.getName());
            values.put(SHORTDESC ,training.getShortDesc());
            values.put(LONGDESC ,training.getLongDesc());
            values.put(IMAGEURL ,training.getImageUrl());
            database.insert(TRAININGS_TABLE, null, values);

        }catch (Exception e){
            e.printStackTrace();
        }
        database.close();

    }
    public ArrayList<Training> getAllTrainings(){
        ArrayList<Training> trainings= new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM "+ TRAININGS_TABLE;

        Cursor cursor = database.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                Training training = new Training(cursor.getInt(0),cursor.getString(1),cursor.getString(3),cursor.getString(2),cursor.getString(4));
                trainings.add(training);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return trainings;
    }
    public  ArrayList<Plan> getPlansByDay(String day){
        ArrayList<Plan> allPlans;
        allPlans = getAllPlans();
        ArrayList<Plan> plans = new ArrayList<>();
        for (Plan p :allPlans){
            if (p.getDay().equalsIgnoreCase(day) ){
                plans.add(p);
            }
        }
        return plans;
    }

}

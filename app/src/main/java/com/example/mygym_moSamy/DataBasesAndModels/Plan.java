package com.example.mygym_moSamy.DataBasesAndModels;

public class Plan {
    int id ;
    private Training training ;
    private int min ;
    private String day ;
    private boolean isAccomplished ;

    public Plan(Training training, int min, String day, boolean isAccomplished) {
        this.training = training;
        this.min = min;
        this.day = day;
        this.isAccomplished = isAccomplished;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", training=" + training +
                ", min=" + min +
                ", day='" + day + '\'' +
                ", isAccomplished=" + isAccomplished +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public boolean isAccomplished() {
        return isAccomplished;
    }

    public void setAccomplished(boolean accomplished) {
        isAccomplished = accomplished;
    }
}

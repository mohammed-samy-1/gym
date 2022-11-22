package com.example.mygym_moSamy.DataBasesAndModels;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.example.mygym_moSamy.Activities.TrainingActivity;
import com.example.mygym_moSamy.R;
import com.google.android.material.switchmaterial.SwitchMaterial;



public class PlanDetailDialog extends DialogFragment/*this is a fragment*/ {

    // iterface to pass the plan from the fragment to the activity
    public interface PassPlanInterface {
        void getPlan (Plan Plan);
    }
    //the name of th training
    TextView txtName ;
    // the button to add or dismiss the dialog (the fragment)
    Button btnAdd ,btnCancel;
    // TODO: 4/20/2021 ill use this note later to send with intent to create the alarm
    //the minute is the time the user will pas to finish the training
    EditText note ,minutes;
    // TODO: 4/20/2021 later ill use this to make the alarm for the training
    // TODO: 4/20/2021 if i learnt notifications i have to send it using notification too
    SwitchMaterial aSwitch;
    Spinner spinner;//  the days were the user can expand it to get the list and choose a day
    private PassPlanInterface planInterface;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //its a fragment so i can inflate the view i created like this
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_plan,null);
        initViews(view);
        // building the alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view);
        Bundle bundle = getArguments();// getting the training from the activity by callback
        if (null != bundle){
            Training training = bundle.getParcelable(TrainingActivity.TRAINING_KEY);
            if (null != training){
                txtName.setText(training.getName());
                btnAdd.setOnClickListener(v -> {
                    String day = spinner.getSelectedItem().toString();
                    if (!minutes.getText().toString().isEmpty()) {
                        int min = Integer.parseInt(minutes.getText().toString());

                        Plan Plan = new Plan(training, min, day, false);
                        try {
                            planInterface = (PassPlanInterface) getActivity();
                            planInterface.getPlan(Plan);
                            if (aSwitch.isChecked()) {
                                // TODO: 4/21/2021 show in the activity
                                Toast alarm = Toast.makeText(getActivity(), "alarm", Toast.LENGTH_SHORT);
                            }
                            dismiss();
                        } catch (ClassCastException e) {
                            e.printStackTrace();
                            dismiss();
                        }
                    }
                });
                btnCancel.setOnClickListener(v -> dismiss());
            }
        }
        return builder.create();
    }

    private void initViews(View view) {
        txtName =view.findViewById(R.id.dialogTrainingName);
        btnAdd =view.findViewById(R.id.btnAdd);
        btnCancel =view.findViewById(R.id.btnCancel);
        btnCancel =view.findViewById(R.id.btnCancel);
        note =view.findViewById(R.id.note);
        minutes =view.findViewById(R.id.minuts);
        spinner =view.findViewById(R.id.spinnerDays);
        aSwitch = view.findViewById(R.id.setAlarm);

    }
}

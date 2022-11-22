package com.example.mygym_moSamy.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mygym_moSamy.Activities.TrainingActivity;
import com.example.mygym_moSamy.DataBasesAndModels.Plan;
import com.example.mygym_moSamy.DataBasesAndModels.TrainingDB;
import com.example.mygym_moSamy.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

import static android.graphics.Color.RED;
import static com.example.mygym_moSamy.Activities.TrainingActivity.TRAINING_KEY;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {
    public interface RemovePlan{
        void onRemovePlanResult(Plan plan);
    }

    private RemovePlan remove;

    List<Plan> plans;
    Context context;
    String type ="";

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
        notifyDataSetChanged();
    }

    public PlanAdapter( Context context) {
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_item, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(plans.get(position).getTraining().getName());
        holder.txtTime.setText(String.valueOf(plans.get(position).getMin()));
        holder.txtSd.setText(plans.get(position).getTraining().getShortDesc());
        Glide.with(context)
                .asGif()
                .load(plans.get(position).getTraining().getImageUrl())
                .into(holder.imgPlan);
        if (plans.get(position).isAccomplished()){
            holder.imgNotChecked.setVisibility(View.GONE);
            holder.imgChecked.setVisibility(View.VISIBLE);

        }else {
            holder.imgNotChecked.setVisibility(View.VISIBLE);
            holder.imgChecked.setVisibility(View.GONE);

        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TrainingActivity.class);
                intent.putExtra(TRAINING_KEY,plans.get(position).getTraining());
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        if (type.equals("edit")){
            holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setTitle("Delete")
                            .setMessage("Are you sure you want to delete"+ plans.get(position).getTraining().getName())
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //there's a proplem
                                    try {
                                        remove = (RemovePlan) context;
                                        remove.onRemovePlanResult(plans.get(position));
                                    }catch (ClassCastException e){
                                        e.printStackTrace();
                                    }

                                }
                            });
                    builder.create().show();
                    return true;
                }
            });
            holder.imgNotChecked.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Finished")
                        .setMessage("Have you finished "+plans.get(position).getTraining().getName())
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                holder.imgNotChecked.setVisibility(View.GONE);
                                holder.imgChecked.setVisibility(View.VISIBLE);
                                // TODO: 4/22/2021 change this in the data base and notify
                                plans.get(position).setAccomplished(true);
                            }
                        });
                builder.create().show();
            });
        }
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName , txtTime,txtSd;
        ImageView imgPlan ,imgChecked,imgNotChecked;
        MaterialCardView cardView;

        public ViewHolder(View view) {
            super(view);
            txtName= view.findViewById(R.id.txtPlanName);
            txtTime= view.findViewById(R.id.txtTime);
            txtSd= view.findViewById(R.id.planSd);

            imgPlan= view.findViewById(R.id.imgPlan);
            imgChecked= view.findViewById(R.id.checked);
            imgNotChecked= view.findViewById(R.id.notChecked);

            cardView= view.findViewById(R.id.parent);
        }
    }

    public void setType(String type) {
        this.type = type;
    }
}

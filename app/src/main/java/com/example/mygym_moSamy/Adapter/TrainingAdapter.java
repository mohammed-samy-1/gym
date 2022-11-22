package com.example.mygym_moSamy.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mygym_moSamy.Activities.TrainingActivity;
import com.example.mygym_moSamy.R;
import com.example.mygym_moSamy.DataBasesAndModels.Training;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.ViewHolder>{
    ArrayList<Training> trainings = new ArrayList<>();
    Context context;
    private static final String TAG = "TrainingAdapter";

    public TrainingAdapter( Context context) {

        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.training_item,parent ,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.txtName.setText(trainings.get(position).getName());
        holder.txtShortDesc.setText(trainings.get(position).getShortDesc());
        Glide.with(context)
                .asGif()
                .load(trainings.get(position).getImageUrl())
                .into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 4/19/2021 navigate to another activity
                Intent intent = new Intent(context , TrainingActivity.class);
                intent.putExtra(TrainingActivity.TRAINING_KEY, trainings.get(position));
                // because we r not in an activity
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trainings.size();
    }


    public void setTrainings(ArrayList<Training> trainings) {
        this.trainings = trainings;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        MaterialCardView cardView;
        TextView txtName , txtShortDesc;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            txtName = itemView.findViewById(R.id.txtActivityName);
            txtShortDesc = itemView.findViewById(R.id.txtShortDesc);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}

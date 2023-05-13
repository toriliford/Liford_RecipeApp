package com.example.liford_recipeapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liford_recipeapp.Models.InstructionsResponse;
import com.example.liford_recipeapp.R;

import java.util.List;

public class InstructionsAdapter extends RecyclerView.Adapter<InstructionsViewHolder>{

    Context context;
    List<InstructionsResponse> list;

    public InstructionsAdapter(Context context, List<InstructionsResponse> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instructions, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionsViewHolder holder, int position) {
        holder.instructionName.setText(list.get(position).name);
        holder.recyclerInstructionSteps.setHasFixedSize(true);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class InstructionsViewHolder extends RecyclerView.ViewHolder{

    TextView instructionName;
    RecyclerView recyclerInstructionSteps;

    public InstructionsViewHolder(@NonNull View itemView) {
        super(itemView);
        instructionName = itemView.findViewById(R.id.instructionName);
        recyclerInstructionSteps = itemView.findViewById(R.id.recyclerInstructionSteps);
    }
}

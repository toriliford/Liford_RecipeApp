package com.example.liford_recipeapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liford_recipeapp.Listeners.RecipeClickListener;
import com.example.liford_recipeapp.Models.Recipe;
import com.example.liford_recipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder>{

    Context context;
    List<Recipe> list;

    RecipeClickListener rcListener;

    public RandomRecipeAdapter(Context context, List<Recipe> list, RecipeClickListener rcListener) {
        this.context = context;
        this.list = list;
        this.rcListener = rcListener;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        //pull title from recipe and add to textview
        holder.recipeNameTV.setText(list.get(position).title);
        holder.recipeNameTV.setSelected(true);
        //pull tags/dish types
        holder.recipeTagTV.setText(list.get(position).dishTypes.toString());

        //pull image add to image view
        Picasso.get().load(list.get(position).image).into(holder.imageView);

        holder.cardViewContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rcListener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

//create viewholder class
class RandomRecipeViewHolder extends RecyclerView.ViewHolder {
    CardView cardViewContainer;
    TextView recipeNameTV;
    TextView recipeTagTV;
    ImageView imageView;

    public RandomRecipeViewHolder(@NonNull View itemView) {
        super(itemView);

        cardViewContainer = itemView.findViewById(R.id.cardViewContainer);
        recipeNameTV = itemView.findViewById(R.id.recipeNameTV);
        recipeTagTV = itemView.findViewById(R.id.recipeTagTV);
        imageView = itemView.findViewById(R.id.imageView);
    }
}
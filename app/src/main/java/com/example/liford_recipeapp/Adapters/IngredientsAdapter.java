package com.example.liford_recipeapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liford_recipeapp.Models.ExtendedIngredient;
import com.example.liford_recipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsViewHolder>{
    Context context;
    List<ExtendedIngredient> list;

    public IngredientsAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_recipe_ingredients, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        holder.textViewIngredientsName.setText(list.get(position).name);
        holder.textViewIngredientsName.setSelected(true);
        holder.textViewIngredients.setText(list.get(position).original);
        holder.textViewIngredients.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/" + list.get(position).image).into(holder.ingredientsImageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class IngredientsViewHolder extends RecyclerView.ViewHolder
{
    TextView textViewIngredients, textViewIngredientsName;
    ImageView ingredientsImageView;
    public IngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewIngredients = itemView.findViewById(R.id.textViewIngredients);
        textViewIngredientsName = itemView.findViewById(R.id.textViewIngredientsName);
        ingredientsImageView = itemView.findViewById(R.id.ingredientsImageView);
    }
}

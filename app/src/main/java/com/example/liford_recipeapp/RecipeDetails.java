package com.example.liford_recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liford_recipeapp.Adapters.IngredientsAdapter;
import com.example.liford_recipeapp.Listeners.RecipeDetailsListener;
import com.example.liford_recipeapp.Models.RecipeDetailsResponse;
import com.squareup.picasso.Picasso;

public class RecipeDetails extends AppCompatActivity {

    int id;
    Button backbBtn;
    TextView recipeNameTV, recipeSummaryTV;
    ImageView recipeImageView;
    RecyclerView recyclerRecipeIngredients;
    RequestManager manager;
    ProgressDialog dialog;
    IngredientsAdapter ingredientsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        findViews();

        id = Integer.parseInt(getIntent().getStringExtra("id"));

        manager = new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListener, id);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");
        dialog.show();
    }

    private void findViews() {
        recipeNameTV = findViewById(R.id.recipeNameTV);
        recipeSummaryTV = findViewById(R.id.recipeSummaryTV);
        recipeImageView = findViewById(R.id.recipeImageView);
        recyclerRecipeIngredients = findViewById(R.id.recyclerRecipeIngredients);
    }

    private final RecipeDetailsListener recipeDetailsListener = new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            dialog.dismiss();
            recipeNameTV.setText(response.title);
            recipeSummaryTV.setText(response.summary);
            Picasso.get().load(response.image).into(recipeImageView);

            recyclerRecipeIngredients.setHasFixedSize(true);
            recyclerRecipeIngredients.setLayoutManager(new LinearLayoutManager(RecipeDetails.this, LinearLayoutManager.HORIZONTAL, false));
            ingredientsAdapter = new IngredientsAdapter(RecipeDetails.this, response.extendedIngredients);
            recyclerRecipeIngredients.setAdapter(ingredientsAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetails.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}
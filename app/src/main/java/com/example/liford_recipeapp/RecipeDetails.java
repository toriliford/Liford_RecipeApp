package com.example.liford_recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liford_recipeapp.Adapters.IngredientsAdapter;
import com.example.liford_recipeapp.Adapters.InstructionStepAdapter;
import com.example.liford_recipeapp.Adapters.InstructionsAdapter;
import com.example.liford_recipeapp.Listeners.InstructionsListener;
import com.example.liford_recipeapp.Listeners.RecipeClickListener;
import com.example.liford_recipeapp.Listeners.RecipeDetailsListener;
import com.example.liford_recipeapp.Models.InstructionsResponse;
import com.example.liford_recipeapp.Models.RecipeDetailsResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeDetails extends AppCompatActivity {

    int id;
    Button backBtn;
    TextView recipeNameTV, recipeSummaryTV;
    ImageView recipeImageView;
    RecyclerView recyclerRecipeIngredients, recyclerRecipeSteps;
    RequestManager manager;
    ProgressDialog dialog;
    IngredientsAdapter ingredientsAdapter;
    InstructionsAdapter instructionsAdapter;
    InstructionStepAdapter instructionStepAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecipeDetails.this, MainActivity.class);
                startActivity(intent);
            }
        });

        findViews();

        id = Integer.parseInt(getIntent().getStringExtra("id"));

        manager = new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListener, id);
        manager.getInstructions(instructionsListener, id);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");
        dialog.show();
    }

    private void findViews() {
        recipeNameTV = findViewById(R.id.recipeNameTV);
        recipeSummaryTV = findViewById(R.id.recipeSummaryTV);
        recipeImageView = findViewById(R.id.recipeImageView);
        recyclerRecipeIngredients = findViewById(R.id.recyclerRecipeIngredients);
        recyclerRecipeSteps = findViewById(R.id.recyclerRecipeSteps);
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

    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(RecipeDetails.this, RecipeDetails.class).putExtra("id", id));
        }
    };

    private final InstructionsListener instructionsListener = new InstructionsListener() {
        @Override
        public void didFetch(List<InstructionsResponse> response, String message) {
            recyclerRecipeSteps.setHasFixedSize(true);
            recyclerRecipeSteps.setLayoutManager(new LinearLayoutManager(RecipeDetails.this, LinearLayoutManager.HORIZONTAL, false));
            instructionsAdapter = new InstructionsAdapter(RecipeDetails.this, response);
            recyclerRecipeSteps.setAdapter(instructionsAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetails.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}
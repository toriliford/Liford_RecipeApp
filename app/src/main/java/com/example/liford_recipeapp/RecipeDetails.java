package com.example.liford_recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class RecipeDetails extends AppCompatActivity {

    int id;
    Button backbBtn;
    TextView recipeNameTV, recipeSummaryTV,

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        findViews();

        id = Integer.parseInt(getIntent().getStringExtra("id"));


    }

    private void findViews() {

    }
}
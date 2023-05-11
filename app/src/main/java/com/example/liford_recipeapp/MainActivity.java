package com.example.liford_recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button categoryBtn;
    Button keywordBtn;
    Button savedRecipeBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryBtn = findViewById(R.id.categoryBtn);
        keywordBtn = findViewById(R.id.keywordBtn);
        savedRecipeBtn = findViewById(R.id.savedRecipesBtn);

        //create intents for each button
        Intent categoryIntent = new Intent(this, CategoryChoice.class);
        Intent keywordIntent = new Intent(this, KeywordChoice.class);
        Intent savedRecipeIntent = new Intent(this, SavedRecipeChoice.class);

        //on-click listener for category choice
        categoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(categoryIntent);
            }
        });

        //on-click listener for keyword choice
        keywordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(keywordIntent);
            }
        });

        //on-click listener for saved recipes choice
        savedRecipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(savedRecipeIntent);
            }
        });


    }
}
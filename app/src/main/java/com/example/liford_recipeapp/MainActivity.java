package com.example.liford_recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.liford_recipeapp.Adapters.RandomRecipeAdapter;
import com.example.liford_recipeapp.Listeners.RandomRecipeResponseListener;
import com.example.liford_recipeapp.Models.RandomRecipeApiGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button categoryBtn;
    Button keywordBtn;
    Button savedRecipeBtn;

    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipeAdapter randomRecipeAdapter;
    RecyclerView recyclerView;

    List<String> tags = new ArrayList<>();

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

        //add all tags so it can be random
        tags.addAll(Arrays.asList(getResources().getStringArray(R.array.tags)));

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");

        manager = new RequestManager(this);
        manager.getRandomRecipes(randomRecipeResponseListener, tags);
        dialog.show();
    }

    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiGenerator response, String message) {
            //dismiss dialog when fetched
            dialog.dismiss();
            recyclerView = findViewById(R.id.homeRecyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
            randomRecipeAdapter = new RandomRecipeAdapter(MainActivity.this, response.recipes);
            recyclerView.setAdapter(randomRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
        }
    };
}
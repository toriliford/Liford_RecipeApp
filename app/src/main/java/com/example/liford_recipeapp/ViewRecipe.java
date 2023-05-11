package com.example.liford_recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewRecipe extends AppCompatActivity {

    //this class is where we show the actual recipe

    Button backBtn;
    private TextView recipeName;
    private TextView recipeCategory;
    private TextView ingredients;
    private TextView recipeSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        recipeName = findViewById(R.id.recipeNameTxt);
        recipeCategory = findViewById(R.id.recipeCategoryTxt);
        ingredients = findViewById(R.id.ingredientsTxt);
        recipeSteps = findViewById(R.id.recipeStepsTxt);

        Intent intent = getIntent();

        String Title = intent.getExtras().getString("recipeName");
        String Category = intent.getExtras().getString("recipeCategory");
        String[] Ingredients = intent.getExtras().getStringArray("ingredients");
        String[] Recipe = intent.getExtras().getStringArray("recipeSteps");

        recipeName.setText(Title);
        recipeCategory.setText(Category);
        ingredients.setText(arrayToString(Ingredients));
        recipeSteps.setText(arrayToString(Recipe));


        //set back button to do its job once again
        Intent backIntent = new Intent(this, MainActivity.class);  //default back to main for now
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(backIntent);
            }
        });

    }

    public String arrayToString(String[] strings)
    {
        String str = "";
        for(String s : strings)
        {
            str += (s + "\n");
        }
        return str;
    }
}
package com.example.liford_recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.liford_recipeapp.Adapters.RandomRecipeAdapter;
import com.example.liford_recipeapp.Listeners.RandomRecipeResponseListener;
import com.example.liford_recipeapp.Models.RandomRecipeApiGenerator;

import java.util.Arrays;
import java.util.List;

public class CategoryChoice extends AppCompatActivity {

    Button backBtn;
    Spinner spinner;


    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipeAdapter randomRecipeAdapter;
    RecyclerView recyclerView;

    List<String> tags;


    //I DONT UNDERSTAND WHY THIS ACTIVITY WONT WORK
    //i commented out the code that didn't work, so the spinner isn't actually functional
    //and it only shows 5 random recipes like the main activity..

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_choice);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryChoice.this, MainActivity.class);
                startActivity(intent);
            }
        });

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");

        //tags.addAll(Arrays.asList(getResources().getStringArray(R.array.tags)));
        manager = new RequestManager(this);
        manager.getRandomRecipes(randomRecipeResponseListener, tags);

        spinner = findViewById(R.id.spinnerTags);
        ArrayAdapter arrayAdapter;
        arrayAdapter = ArrayAdapter.createFromResource(this, R.array.tags, R.layout.spinner_text);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinner.setAdapter(arrayAdapter);

        manager = new RequestManager(this);
        //spinner.setOnItemSelectedListener(spinnerSelectedListener);

    }

    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiGenerator response, String message) {
            //dismiss dialog when fetched
            //dialog.dismiss();
            recyclerView = findViewById(R.id.categoryRecyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(CategoryChoice.this, 1));
            randomRecipeAdapter = new RandomRecipeAdapter(CategoryChoice.this, response.recipes);
            recyclerView.setAdapter(randomRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(CategoryChoice.this, message, Toast.LENGTH_SHORT).show();
        }
    };

//    private final AdapterView.OnItemSelectedListener spinnerSelectedListener = new AdapterView.OnItemSelectedListener() {
//        @Override
//        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//            if(tags != null){tags.clear();}
//
//            tags.add(adapterView.getSelectedItem().toString());
//            manager.getRandomRecipes(randomRecipeResponseListener, tags);
//
//            dialog.show();
//        }
//
//        @Override
//        public void onNothingSelected(AdapterView<?> adapterView) {
//            //tags.add("main course");
//            manager.getRandomRecipes(randomRecipeResponseListener, tags);
//
//            //dialog.show();
//        }
//    };
}
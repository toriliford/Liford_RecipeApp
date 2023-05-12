package com.example.liford_recipeapp.Listeners;

import com.example.liford_recipeapp.Models.RandomRecipeApiGenerator;

public interface RandomRecipeResponseListener {
    void didFetch(RandomRecipeApiGenerator response, String message);
    void didError(String message);
}

package com.example.liford_recipeapp.Listeners;

import com.example.liford_recipeapp.Models.InstructionsResponse;

import java.util.List;

public interface InstructionsListener {

    void didFetch(List<InstructionsResponse> response, String message);
    void didError(String message);
}

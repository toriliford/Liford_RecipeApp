package com.example.liford_recipeapp;

import android.content.Context;

import com.example.liford_recipeapp.Listeners.InstructionsListener;
import com.example.liford_recipeapp.Listeners.RandomRecipeResponseListener;
import com.example.liford_recipeapp.Listeners.RecipeDetailsListener;
import com.example.liford_recipeapp.Models.InstructionsResponse;
import com.example.liford_recipeapp.Models.RandomRecipeApiGenerator;
import com.example.liford_recipeapp.Models.RecipeDetailsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {

    //DIFFICULT SECTION: Calling the API
    //took a lot of googling and searching the actual api website to figure this out

    //create context
    Context context;
    //create retrofit object
    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.spoonacular.com/").addConverterFactory(GsonConverterFactory.create()).build();

    public RequestManager(Context context) {
        this.context = context;
    }

    //we can call this method from any activity to return all data of random recipes
    public void getRandomRecipes(RandomRecipeResponseListener listener, List<String> tags)
    {
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
        //heres the actual call itself, pulling from spoonacular with correct parameters
        Call<RandomRecipeApiGenerator> call = callRandomRecipes.callRandomRecipe(context.getString(R.string.api_key), "5", tags);
        call.enqueue(new Callback<RandomRecipeApiGenerator>() {
            @Override
            //if theres a response from the call..
            public void onResponse(Call<RandomRecipeApiGenerator> call, Response<RandomRecipeApiGenerator> response) {
                //if response isnt successful, call error method
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                //else, return message
                listener.didFetch(response.body(), response.message());
            }
            //if no response from the call..
            @Override
            public void onFailure(Call<RandomRecipeApiGenerator> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void getRecipeDetails(RecipeDetailsListener listener, int id)
    {
        CallRecipeDetails callRecipeDetails = retrofit.create(CallRecipeDetails.class);
        Call<RecipeDetailsResponse> call = callRecipeDetails.callRecipeDetails(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailsResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void getInstructions(InstructionsListener listener, int id){
        CallInstructions callInstructions = retrofit.create(CallInstructions.class);
        Call<List<InstructionsResponse>> call = callInstructions.callInstructions(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<List<InstructionsResponse>>() {
            @Override
            public void onResponse(Call<List<InstructionsResponse>> call, Response<List<InstructionsResponse>> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<InstructionsResponse>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    //create interface to call random recipe from spoonacular API
    private interface CallRandomRecipes
    {   //"get" the rest of the URL according to spoonacular api website
        @GET("recipes/random")
        Call<RandomRecipeApiGenerator> callRandomRecipe(
                //pass the api key and the number of recipes to print, and keyword tag
                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags") List<String> tags
        );
    }

    private interface CallRecipeDetails
    {
        @GET("recipes/{id}/information")
        Call<RecipeDetailsResponse> callRecipeDetails(
                @Path("id") int id,
                @Query("apiKey")String apiKey
        );
    }

    private interface CallInstructions{
        @GET("recipes/{id}/analyzedInstructions")
        Call <List<InstructionsResponse>> callInstructions(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }
}

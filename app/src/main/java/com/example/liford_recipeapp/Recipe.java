package com.example.liford_recipeapp;

public class Recipe {

    private String recipeName;
    private String recipeCategory;
    private String[] ingredients;
    private String[] recipeSteps;
    private int thumbnail;

    public Recipe(String recipeName, String recipeCategory, String[] ingredients, String[] recipeSteps, int thumbnail)
    {
        this.recipeName = recipeName;
        this.recipeCategory = recipeCategory;
        this.ingredients = ingredients;
        this.recipeSteps = recipeSteps;
        this.thumbnail = thumbnail;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(String recipeCategory) {
        this.recipeCategory = recipeCategory;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String[] getRecipeSteps() {
        return recipeSteps;
    }

    public void setRecipeSteps(String[] recipeSteps) {
        this.recipeSteps = recipeSteps;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}

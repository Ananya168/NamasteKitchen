package com.example.namastekitchen;


public class Recipe {
    private String name;
    private String description; // If you need this for displaying
    private String cuisine; // If you categorize by cuisine
    private String ingredients; // Can be a single string or structured
    private String procedure; // Can be a single string or structured

    // Constructor
    public Recipe(String name, String cuisine, String ingredients, String procedure) {
        this.name = name;
        this.cuisine = cuisine;
        this.ingredients = ingredients;
        this.procedure = procedure;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getCuisine() {
        return cuisine;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getProcedure() {
        return procedure;
    }
}

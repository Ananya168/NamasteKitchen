package com.example.namastekitchen;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RecipeDatabase {

    private static final String TAG = "RecipeDatabase";
    private List<Recipe> recipes;

    // Constructor
    public RecipeDatabase(Context context) {
        recipes = loadRecipesFromJSON(context);
    }

    // Method to get a recipe based on meal name and cuisine
    public Recipe getRecipe(String meal, String cuisine) {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(meal) && recipe.getCuisine().equalsIgnoreCase(cuisine)) {
                return recipe;
            }
        }
        return null; // Return null if no recipe matches
    }

    // Load recipes from JSON file
    private List<Recipe> loadRecipesFromJSON(Context context) {
        List<Recipe> recipeList = new ArrayList<>();
        String json = null;

        try {
            InputStream inputStream = context.getAssets().open("recipes.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            json = stringBuilder.toString();
            reader.close();
        } catch (IOException e) {
            Log.e(TAG, "Error reading JSON file", e);
        }

        // Parse the JSON data
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject recipeObject = jsonArray.getJSONObject(i);
                String name = recipeObject.getString("name");
                String cuisine = recipeObject.getString("cuisine"); // Add cuisine
                String ingredients = recipeObject.getString("ingredients"); // Add ingredients
                String procedure = recipeObject.getString("procedure"); // Add procedure

                // Create the Recipe object with all four parameters
                Recipe recipe = new Recipe(name, cuisine, ingredients, procedure);
                recipeList.add(recipe);
            }
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing JSON data", e);
        }

        return recipeList;
    }

    // Search for recipes based on the query
    public List<Recipe> searchRecipes(String query) {
        List<Recipe> matchedRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getName().toLowerCase().contains(query.toLowerCase())) {
                matchedRecipes.add(recipe);
            }
        }
        return matchedRecipes;
    }
}

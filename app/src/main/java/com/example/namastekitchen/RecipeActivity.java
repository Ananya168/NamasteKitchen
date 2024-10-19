package com.example.namastekitchen;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String meal = getIntent().getStringExtra("meal");
        String cuisine = getIntent().getStringExtra("cuisine");

        // Initialize RecipeDatabase here (assuming it's in your app context)
        RecipeDatabase recipeDatabase = new RecipeDatabase(this);

        // Get the recipe from the dataset based on the meal and cuisine
        Recipe recipe = recipeDatabase.getRecipe(meal, cuisine);

        if (recipe != null) {
            TextView recipeTitle = findViewById(R.id.recipeTitle);
            recipeTitle.setText(recipe.getName()); // Assuming getName() is the correct method

            TextView ingredients = findViewById(R.id.ingredients);
            ingredients.setText(recipe.getIngredients()); // Assuming getIngredients() returns a string

            TextView procedure = findViewById(R.id.procedure);
            procedure.setText(recipe.getProcedure()); // Assuming getProcedure() returns a string
        } else {
            Toast.makeText(this, "Recipe not found", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if no recipe is found
        }
    }
}

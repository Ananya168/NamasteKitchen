package com.example.namastekitchen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.namastekitchen.RecipeDatabase;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private RecipeDatabase recipeDatabase; // Declare RecipeDatabase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the RecipeDatabase
        recipeDatabase = new RecipeDatabase(this);

        EditText searchField = findViewById(R.id.searchField);
        Button searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchField.getText().toString().trim();
                List<Recipe> results = recipeDatabase.searchRecipes(query); // Use the instantiated RecipeDatabase

                if (results != null && !results.isEmpty()) {
                    // Display the results (e.g., in a RecyclerView or Toast for now)
                    StringBuilder resultText = new StringBuilder("Results:\n");
                    for (Recipe recipe : results) {
                        resultText.append(recipe.getName()).append("\n");
                    }
                    Toast.makeText(SearchActivity.this, resultText.toString(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SearchActivity.this, "No recipes found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

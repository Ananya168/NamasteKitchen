package com.example.namastekitchen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView logo;
    private TextView welcomeText;
    private Button breakfastButton, lunchButton, snackButton, dinnerButton, brunchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ensure this matches your new layout

        // Initialize views
        logo = findViewById(R.id.logo); // ImageView for the logo
        welcomeText = findViewById(R.id.welcome_text); // TextView for welcome text
        breakfastButton = findViewById(R.id.breakfast_option); // Button for Breakfast
        lunchButton = findViewById(R.id.lunch_option); // Button for Lunch
        snackButton = findViewById(R.id.snack_option); // Button for Snacks
        dinnerButton = findViewById(R.id.dinner_option); // Button for Dinner
        brunchButton = findViewById(R.id.brunch_option); // Button for Brunch

        // Initially set the welcome text to be invisible
        welcomeText.setVisibility(View.GONE);

        // Use a Handler to delay the actions
        new Handler().postDelayed(() -> {
            // Hide the logo and show the welcome text
            logo.setVisibility(View.GONE);
            welcomeText.setVisibility(View.VISIBLE);
        }, 5000); // Delay for 5 seconds

        // Set click listeners for all meal options
        setMealOptionClickListener(breakfastButton, "Breakfast");
        setMealOptionClickListener(lunchButton, "Lunch");
        setMealOptionClickListener(snackButton, "Snacks");
        setMealOptionClickListener(dinnerButton, "Dinner");
        setMealOptionClickListener(brunchButton, "Brunch");
    }

    private void setMealOptionClickListener(Button button, String meal) {
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CuisineActivity.class);
            intent.putExtra("meal", meal);
            startActivity(intent);
        });
    }
}

package com.example.carflip;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carflip.ui.buycarpage.BuycarsFragment;

public class CarLogoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_cars_by_logo_layout);

        // Retrieve the selected car icon position from the intent
        int selectedCarIcon = getIntent().getIntExtra("selectedCarIcon", 0);

        // Use the position to determine which car logo to display
        int carLogoResourceId = getCarLogoResource(selectedCarIcon);

        // Find the ImageView in the layout and set the car logo
        ImageView carLogoImageView = findViewById(R.id.imageViewCarLogo);
        carLogoImageView.setImageResource(carLogoResourceId);

        // Check if the displayed car logo is the default one
        if (carLogoResourceId == R.drawable.more_icon) {
            // Launch the FragmentBuycars fragment
            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new BuycarsFragment())
                    .commit();
        }
    }

    // Method to map the position to the corresponding car logo resource
    private int getCarLogoResource(int selectedCarIcon) {
        switch (selectedCarIcon) {
            case 0:
                return R.drawable.maruti_suzuki;
            case 1:
                return R.drawable.tata;
            case 2:
                return R.drawable.hyundai;
            case 3:
                return R.drawable.mahindra;
            case 4:
                return R.drawable.bmw;
            case 5:
                return R.drawable.toyota;
            case 6:
                return R.drawable.honda;
            default:
                return R.drawable.more_icon; // Default car logo resource
        }
    }
}

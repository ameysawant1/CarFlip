package com.example.carflip;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carflip.Onboarding.OnboardingActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);

        // Hide the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Set the activity to fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Start the AsyncTask to fetch data while showing the splash screen
        new DataLoadingTask().execute();
    }

    // AsyncTask to simulate data loading process
    private class DataLoadingTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            // Simulate fetching data
            try {
                // Simulate some delay for fetching data
                Thread.sleep(2000); // 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // After data loading is complete, start the OnboardingActivity
            Intent onboardingIntent = new Intent(SplashActivity.this, OnboardingActivity.class);
            startActivity(onboardingIntent);

            // Finish the current activity to prevent going back to the splash screen
            finish();
        }
    }
}

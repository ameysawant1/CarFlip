package com.example.carflip.Onboarding;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.carflip.R;
import com.example.carflip.Login;
import com.example.carflip.Register2;

public class OnboardingActivity extends AppCompatActivity {

    private LinearLayout layoutDots;
    private OnboardingPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isOnboardingCompleted()) {
            startHomePage();
            finish(); // Finish the onboarding activity to prevent going back to it
            return;
        }
        setContentView(R.layout.activity_onboarding_page);

        ViewPager viewPager = findViewById(R.id.viewPager);
        ImageButton btnNext = findViewById(R.id.btnContinueTologin);
        layoutDots = findViewById(R.id.layoutDots);

        pagerAdapter = new OnboardingPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        addDots(0); // Add initial dots

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                addDots(position); // Update dots when page changes
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = viewPager.getCurrentItem();
                if (currentItem < pagerAdapter.getCount() - 1) {
                    viewPager.setCurrentItem(currentItem + 1);
                } else {
                    // Mark onboarding as completed
                    markOnboardingAsCompleted();

                    // Move to the homepage
                    startHomePage();
                }
            }
        });
    }

    private void addDots(int currentPage) {
        int dotsCount = pagerAdapter.getCount();
        layoutDots.removeAllViews();
        for (int i = 0; i < dotsCount; i++) {
            ImageView dot = new ImageView(this);
            dot.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dot_inactive));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8, 0, 8, 0);
            layoutDots.addView(dot, params);
        }
        if (dotsCount > 0) {
            ImageView dot = (ImageView) layoutDots.getChildAt(currentPage);
            if (dot != null) {
                dot.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dot_active));
            }
        }
    }

    // Check if onboarding is completed by reading a value from SharedPreferences
    private boolean isOnboardingCompleted() {
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        return preferences.getBoolean("onboarding_completed", false);
    }

    // Save the completion status to SharedPreferences when onboarding is finished
    private void markOnboardingAsCompleted() {
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("onboarding_completed", true);
        editor.apply();
    }

    // Move to the HomePage
    private void startHomePage() {
        Intent intent = new Intent(OnboardingActivity.this, Login.class);
        startActivity(intent);
        finish(); // Finish the onboarding activity to prevent going back to it
    }
}

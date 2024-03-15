package com.example.carflip.ui.loanpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.carflip.R;

public class Loan_Page_Fragment extends Fragment {

    private SeekBar loanAmountSeekBar;
    private SeekBar loanTimeInYearsSeekBar;
    private SeekBar interestRateSeekBar;
    private Button calculateButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_loan_page, container, false);

        loanAmountSeekBar = rootView.findViewById(R.id.loan_amount_seekBar);
        loanTimeInYearsSeekBar = rootView.findViewById(R.id.loan_time_year_seekBar);
        interestRateSeekBar = rootView.findViewById(R.id.interest_rate_seekBar);
        calculateButton = rootView.findViewById(R.id.button_to_calculate_loan);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndDisplayResult();
            }
        });

        return rootView;
    }

    private void calculateAndDisplayResult() {
        double loanAmount = loanAmountSeekBar.getProgress() * 1000; // Assuming the seekBar range is in thousands
        int loanTermYears = loanTimeInYearsSeekBar.getProgress();
        double annualInterestRate = interestRateSeekBar.getProgress() * 0.1; // Assuming the seekBar range is in tenths

        double monthlyInterestRate = annualInterestRate / 12 / 100;
        int numberOfPayments = loanTermYears * 12;
        double monthlyPayment = (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));

        String result = "Monthly Payment: $" + monthlyPayment;

        // Display the result in a Toast message
        Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
    }
}

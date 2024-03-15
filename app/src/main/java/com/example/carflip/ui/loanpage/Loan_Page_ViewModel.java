package com.example.carflip.ui.loanpage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Loan_Page_ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public Loan_Page_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
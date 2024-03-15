package com.example.carflip.ui.buycarpage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BuycarsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BuycarsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("buycarsViewModelpage");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

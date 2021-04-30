package com.example.ulearn.ui.notifications;

import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ulearn.R;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private TextView miid;
    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");

    }



    public LiveData<String> getText() {
        return mText;
    }
}
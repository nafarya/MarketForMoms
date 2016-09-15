package com.example.dan.mommarket.presenter.delayed;

import android.os.Bundle;

import com.example.dan.mommarket.views.Delayed;

/**
 * Created by dan on 14.09.16.
 */

public interface DelayedPresenter {
    void onCreateView(Bundle savedInstanceState);
    void updateList();
    void setView(Delayed delayed);
    void removeView();
}

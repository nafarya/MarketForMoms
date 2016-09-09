package com.example.dan.mommarket.presenter.mainscreen;

import android.os.Bundle;

import com.example.dan.mommarket.views.MainScreen;

/**
 * Created by dan on 02.09.16.
 */

public interface MainScreenPresenter {
    void onCreateView(Bundle savedInstanceState);
    void setView(MainScreen mainScreen);
    void removeView();
}

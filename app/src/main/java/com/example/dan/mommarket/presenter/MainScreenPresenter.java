package com.example.dan.mommarket.presenter;

import android.os.Bundle;

import com.example.dan.mommarket.view.MainScreenView;

/**
 * Created by dan on 02.09.16.
 */

public interface MainScreenPresenter {
    void onCreateView(Bundle savedIntanceState);
    void setView(MainScreenView mainScreenView);
    void removeView();
}

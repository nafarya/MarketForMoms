package com.example.dan.mommarket.presenter;

import android.os.Bundle;

import com.example.dan.mommarket.view.MainAdviceListView;

/**
 * Created by dan on 26.08.16.
 */

public interface MainAdviceListPresenter {
    void onCreateView(Bundle savedIntanceState);
    void updateList();
    void setView(MainAdviceListView adviceListView);
    void removeView();
}

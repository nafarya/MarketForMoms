package com.example.dan.mommarket.presenter.advice;

import android.os.Bundle;

import com.example.dan.mommarket.views.AdviceList;

/**
 * Created by dan on 26.08.16.
 */

public interface AdviceListPresenter {
    void onCreateView(Bundle savedInstanceState);
    void updateList();
    void setView(AdviceList adviceListView);
    void removeView();
}

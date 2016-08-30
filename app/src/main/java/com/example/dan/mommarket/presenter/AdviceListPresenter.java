package com.example.dan.mommarket.presenter;

import android.os.Bundle;

import com.example.dan.mommarket.view.AdviceListViewImpl;

/**
 * Created by dan on 30.08.16.
 */

public interface AdviceListPresenter {
    void onCreateView(Bundle savedIntanceState);
    void updateList();
    void setView(AdviceListViewImpl adviceListView);
    void removeView();

}

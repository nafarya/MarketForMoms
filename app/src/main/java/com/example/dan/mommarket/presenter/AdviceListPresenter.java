package com.example.dan.mommarket.presenter;

import android.os.Bundle;

import com.example.dan.mommarket.view.AdviceListView;
import com.example.dan.mommarket.view.CatalogView;

/**
 * Created by dan on 26.08.16.
 */

public interface AdviceListPresenter {
    void onCreateView(Bundle savedIntanceState);
    void updateList();
    void setView(AdviceListView adviceListView);
    void removeView();
}

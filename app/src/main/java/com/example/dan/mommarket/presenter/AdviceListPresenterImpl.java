package com.example.dan.mommarket.presenter;

import android.os.Bundle;

import com.example.dan.mommarket.view.AdviceListView;
import com.example.dan.mommarket.view.AdviceListViewImpl;

/**
 * Created by dan on 30.08.16.
 */

public class AdviceListPresenterImpl implements AdviceListPresenter {

    private AdviceListView adviceListView;
    private static AdviceListPresenterImpl instance;

    @Override
    public void onCreateView(Bundle savedIntanceState) {
        if (savedIntanceState == null) {
            adviceListView.showAdvices();
        }
    }


    public static synchronized AdviceListPresenterImpl getInstance() {
        if (instance == null) {
            instance = new AdviceListPresenterImpl();
        }
        return instance;
    }

    @Override
    public void updateList() {

    }

    @Override
    public void setView(AdviceListViewImpl adviceListView) {
        this.adviceListView = adviceListView;
    }

    @Override
    public void removeView() {
        adviceListView = null;
    }
}

package com.example.dan.mommarket.presenter.advice;

import android.os.Bundle;

import com.example.dan.mommarket.views.AdviceDetail;
import com.example.dan.mommarket.fragments.advice.AdviceDetailFragment;

/**
 * Created by dan on 30.08.16.
 */

public class AdviceDetailPresenterImpl implements AdviceDetailPresenter {

    private static AdviceDetailPresenterImpl instance;
    private AdviceDetail adviceDetail;

    public static synchronized AdviceDetailPresenterImpl getInstance() {
        if (instance == null) {
            instance = new AdviceDetailPresenterImpl();
        }
        return instance;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            adviceDetail.showAdvices();
        }
    }

    @Override
    public void updateList() {

    }

    @Override
    public void setView(AdviceDetailFragment adviceListView) {
        this.adviceDetail = adviceListView;
    }

    @Override
    public void removeView() {
        adviceDetail = null;
    }
}

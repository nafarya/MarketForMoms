package com.example.dan.mommarket.presenter.advice;

import android.os.Bundle;

import com.example.dan.mommarket.fragments.advice.AdviceDetailFragment;
import com.example.dan.mommarket.views.ProductList;

/**
 * Created by dan on 30.08.16.
 */

public interface AdviceDetailPresenter {
    void onCreateView(Bundle savedInstanceState);
    void updateList();
    void setView(AdviceDetailFragment adviceListView);
    void removeView();

}

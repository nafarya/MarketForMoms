package com.example.dan.mommarket.presenter.advice;

import android.os.Bundle;

import com.example.dan.mommarket.database.ProductDataSource;
import com.example.dan.mommarket.database.AdviceDataSource;
import com.example.dan.mommarket.model.Advice;
import com.example.dan.mommarket.views.AdviceDetail;
import com.example.dan.mommarket.fragments.advice.AdviceDetailFragment;

/**
 * Created by dan on 30.08.16.
 */

public class AdviceDetailPresenterImpl implements AdviceDetailPresenter {

    private static AdviceDetailPresenterImpl instance;
    private AdviceDetail adviceDetail;
    private int productCategoryId = 2201;

    private Advice advice;

    public static synchronized AdviceDetailPresenterImpl getInstance() {
        if (instance == null) {
            instance = new AdviceDetailPresenterImpl();
        }
        return instance;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        adviceDetail.showProducts(ProductDataSource.getListProductsByCategoryId(productCategoryId));
        adviceDetail.showAdvice(AdviceDataSource.getAdviceById(1));

       /* if (savedInstanceState == null) {
            advice = AdviceDataSource.getAdviceById(1);
            this.adviceDetail.showAdvice(advice);
        } else {
            advice = AdviceDataSource.getAdviceById(savedInstanceState.getInt("AdviceId"));
            this.adviceDetail.showAdvice(advice);
        }*/
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

package com.example.dan.mommarket.presenter;

import android.os.Bundle;

import com.example.dan.mommarket.database.AdviceDataSource;
import com.example.dan.mommarket.model.Advice;
import com.example.dan.mommarket.view.MainAdviceListView;

import java.util.List;

/**
 * Created by dan on 26.08.16.
 */

public class MainAdviceListPresenterImpl implements MainAdviceListPresenter {

    private MainAdviceListView mainAdviceListView;

    private List<Advice> adviceList;

    public MainAdviceListPresenterImpl() {
    }

    private static MainAdviceListPresenterImpl instance;

    public static synchronized MainAdviceListPresenterImpl getInstance() {
        if (instance == null) {
            instance = new MainAdviceListPresenterImpl();
        }
        return instance;
    }

    @Override
    public void onCreateView(Bundle savedIntanceState) {
        if (savedIntanceState == null) {
            adviceList = AdviceDataSource.getAllAdvices();
            mainAdviceListView.showAdvices(adviceList);
        }
    }

    @Override
    public void updateList() {
        //ProductDataSource productDataSource = new ProductDataSource(context);
        //viewCatalog.showProducts(productDataSource.getAllProducts());
    }

    @Override
    public void setView(MainAdviceListView mainAdviceListView) {
        this.mainAdviceListView = mainAdviceListView;

    }

    @Override
    public void removeView() {
        mainAdviceListView = null;
    }
}

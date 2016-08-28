package com.example.dan.mommarket.presenter;

import android.os.Bundle;

import com.example.dan.mommarket.database.AdviceDataSource;
import com.example.dan.mommarket.model.Advice;
import com.example.dan.mommarket.view.AdviceListView;
import com.example.dan.mommarket.view.CatalogView;

import java.util.List;

/**
 * Created by dan on 26.08.16.
 */

public class AdviceListPresenterImpl implements AdviceListPresenter {

    private AdviceListView adviceListView;

    private List<Advice> adviceList;

    public AdviceListPresenterImpl() {
    }

    private static AdviceListPresenterImpl instance;

    public static synchronized AdviceListPresenterImpl getInstance() {
        if (instance == null) {
            instance = new AdviceListPresenterImpl();
        }
        return instance;
    }

    @Override
    public void onCreateView(Bundle savedIntanceState) {
        if (savedIntanceState == null) {
            adviceList = AdviceDataSource.getAllAdvices();
            adviceListView.showAdvices(adviceList);
        }
    }

    @Override
    public void updateList() {
        //ProductDataSource productDataSource = new ProductDataSource(context);
        //viewCatalog.showProducts(productDataSource.getAllProducts());
    }

    @Override
    public void setView(AdviceListView adviceListView) {
        this.adviceListView = adviceListView;

    }

    @Override
    public void removeView() {
        adviceListView = null;
    }
}

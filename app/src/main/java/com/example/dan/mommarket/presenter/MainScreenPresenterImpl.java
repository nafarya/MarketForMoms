package com.example.dan.mommarket.presenter;

import android.drm.ProcessedData;
import android.os.Bundle;

import com.example.dan.mommarket.database.AdviceDataSource;
import com.example.dan.mommarket.database.ProductDataSource;
import com.example.dan.mommarket.model.Advice;
import com.example.dan.mommarket.view.MainScreenView;

import java.util.List;

/**
 * Created by dan on 02.09.16.
 */

public class MainScreenPresenterImpl implements MainScreenPresenter {
    private MainScreenView mainScreenView;
    private static MainScreenPresenterImpl instance;
    private final int numOfAdvices = 2;


    public static synchronized MainScreenPresenterImpl getInstance() {
        if (instance == null) {
            instance = new MainScreenPresenterImpl();
        }
        return instance;
    }

    @Override
    public void onCreateView(Bundle savedIntanceState) {
        mainScreenView.showAdvices(AdviceDataSource.getAllAdvices(), numOfAdvices);
        mainScreenView.showProducts(ProductDataSource.getAllProducts());
    }

    @Override
    public void setView(MainScreenView mainScreenView) {
        this.mainScreenView = mainScreenView;
    }

    @Override
    public void removeView() {

    }
}

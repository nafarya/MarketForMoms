package com.example.dan.mommarket.presenter.mainscreen;

import android.os.Bundle;

import com.example.dan.mommarket.database.AdviceDataSource;
import com.example.dan.mommarket.database.ProductDataSource;
import com.example.dan.mommarket.views.MainScreen;

/**
 * Created by dan on 02.09.16.
 */

public class MainScreenPresenterImpl implements MainScreenPresenter {
    private static MainScreenPresenterImpl instance;
    private final int numOfAdvices = 2;
    private MainScreen mainScreen;

    public static synchronized MainScreenPresenterImpl getInstance() {
        if (instance == null) {
            instance = new MainScreenPresenterImpl();
        }
        return instance;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        mainScreen.showAdvices(AdviceDataSource.getAllAdvices(), numOfAdvices);
        mainScreen.showProducts(ProductDataSource.getAllProducts());
    }

    @Override
    public void setView(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
    }

    @Override
    public void removeView() {

    }
}

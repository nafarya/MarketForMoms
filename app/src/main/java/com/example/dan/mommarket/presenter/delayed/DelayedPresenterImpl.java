package com.example.dan.mommarket.presenter.delayed;

import android.os.Bundle;

import com.example.dan.mommarket.database.ProductDataSource;
import com.example.dan.mommarket.model.Product;
import com.example.dan.mommarket.views.Delayed;

import java.util.List;

/**
 * Created by dan on 14.09.16.
 */

public class DelayedPresenterImpl implements DelayedPresenter {

    private Delayed delayedView;
    private static DelayedPresenterImpl instance;
    private List<Product> productList;

    public static synchronized DelayedPresenterImpl getInstance() {
        if (instance == null) {
            instance = new DelayedPresenterImpl();
        }
        return instance;
    }

    public void deleteProductFromList(int productId) {

    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            productList = ProductDataSource.getListDelayedProducts(1);
            delayedView.showDelayedProducts(productList);
        }
    }

    @Override
    public void updateList() {
        productList = ProductDataSource.getListDelayedProducts(1);
        delayedView.showDelayedProducts(productList);
    }

    @Override
    public void setView(Delayed delayed) {
        this.delayedView = delayed;
    }

    @Override
    public void removeView() {
        this.delayedView = null;
    }
}

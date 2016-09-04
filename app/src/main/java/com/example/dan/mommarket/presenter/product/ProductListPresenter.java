package com.example.dan.mommarket.presenter.product;

import android.os.Bundle;

import com.example.dan.mommarket.views.ProductList;

/**
 * Created by dan on 26.08.16.
 */

public interface ProductListPresenter {
    void onCreateView(Bundle savedIntanceState);
    void updateCatalog();
    void setView(ProductList productList);
    void removeView();
}

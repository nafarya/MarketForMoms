package com.example.dan.mommarket.presenter;

import android.os.Bundle;

import com.example.dan.mommarket.view.ProductListView;

/**
 * Created by dan on 26.08.16.
 */

public interface ProductListPresenter {
    void onCreateView(Bundle savedIntanceState);
    void updateCatalog();
    void setView(ProductListView productListView);
    void removeView();
}

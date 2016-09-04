package com.example.dan.mommarket.presenter.category;

import android.os.Bundle;

import com.example.dan.mommarket.views.Catalog;

/**
 * Created by dan on 26.08.16.
 */

public interface CatalogPresenter {
    void onCreateView(Bundle savedInstanceState);
    void updateCatalog();
    void setView(Catalog catalog);
    void removeView();
}

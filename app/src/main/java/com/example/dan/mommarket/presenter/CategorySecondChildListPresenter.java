package com.example.dan.mommarket.presenter;

import android.os.Bundle;

import com.example.dan.mommarket.view.CategorySecondChildListView;

/**
 * Created by dan on 26.08.16.
 */

public interface CategorySecondChildListPresenter {
    void onCreateView(Bundle savedInstanceState);

    void updateCatalog();

    void setView(CategorySecondChildListView categoryChildListView);

    void removeView();
}

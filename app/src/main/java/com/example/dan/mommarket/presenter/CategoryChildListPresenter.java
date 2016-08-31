package com.example.dan.mommarket.presenter;

import android.os.Bundle;

import com.example.dan.mommarket.view.CategoryChildListView;
import com.example.dan.mommarket.view.CategoryListView;

/**
 * Created by dan on 26.08.16.
 */

public interface CategoryChildListPresenter {
    void onCreateView(Bundle savedInstanceState);
    void updateCatalog();
    void setView(CategoryChildListView categoryChildListView);
    void removeView();
}

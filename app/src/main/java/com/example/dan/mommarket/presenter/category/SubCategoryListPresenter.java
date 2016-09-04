package com.example.dan.mommarket.presenter.category;

import android.os.Bundle;

import com.example.dan.mommarket.views.SubCategory;

/**
 * Created by dan on 26.08.16.
 */

public interface SubCategoryListPresenter {
    void onCreateView(Bundle savedInstanceState);

    void updateCatalog();

    void setView(SubCategory categoryChildListView);

    void removeView();
}

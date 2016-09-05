package com.example.dan.mommarket.presenter.category;

import android.os.Bundle;

import com.example.dan.mommarket.views.Category;

/**
 * Created by dan on 26.08.16.
 */

public interface CategoryPresenter {
    void onCreateView(Bundle savedInstanceState);
    void updateCategory();
    void setView(Category category);
    void removeView();
}

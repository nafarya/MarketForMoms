package com.example.dan.mommarket.presenter.category;

import android.os.Bundle;

import com.example.dan.mommarket.database.CategoryDataSource;
import com.example.dan.mommarket.model.ProductCategory;
import com.example.dan.mommarket.views.SubCategory;

import java.util.List;

/**
 * Created by dan on 26.08.16.
 */

public class SubCategoryListPresenterImpl implements SubCategoryListPresenter {

    private static SubCategoryListPresenterImpl instance;
    private SubCategory subCategory;

    public SubCategoryListPresenterImpl() {
    }

    public static synchronized SubCategoryListPresenterImpl getInstance() {
        if (instance == null) {
            instance = new SubCategoryListPresenterImpl();
        }
        return instance;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            List<ProductCategory> categoryChildList = CategoryDataSource.getChildCategories(7);
            subCategory.showProducts(categoryChildList);
        } else {
            List<ProductCategory> categoryChildList = CategoryDataSource.getChildCategories(savedInstanceState.getInt("ParentCategory"));
            subCategory.showProducts(categoryChildList);
        }
    }

    @Override
    public void updateSubCategory() {

    }

    @Override
    public void setView(SubCategory categoryChildListView) {
        this.subCategory = categoryChildListView;
    }

    @Override
    public void removeView() {
        subCategory = null;
    }
}

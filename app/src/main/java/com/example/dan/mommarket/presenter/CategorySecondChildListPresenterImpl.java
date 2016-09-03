package com.example.dan.mommarket.presenter;

import android.os.Bundle;

import com.example.dan.mommarket.database.CategoryDataSource;
import com.example.dan.mommarket.model.ProductCategory;
import com.example.dan.mommarket.view.CategorySecondChildListView;

import java.util.List;

/**
 * Created by dan on 26.08.16.
 */

public class CategorySecondChildListPresenterImpl implements CategorySecondChildListPresenter {

    private static CategorySecondChildListPresenterImpl instance;
    // private Context context;
    private CategorySecondChildListView categorySecondChildListView;
    private List<ProductCategory> categoryList;

    public CategorySecondChildListPresenterImpl() {
    }

    public static synchronized CategorySecondChildListPresenterImpl getInstance() {
        if (instance == null) {
            instance = new CategorySecondChildListPresenterImpl();
        }
        return instance;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            //ProductDataSource productDataSource = new ProductDataSource(context);
            List<ProductCategory> categoryChildList = CategoryDataSource.getChildCategories(7);
            categorySecondChildListView.showProducts(categoryChildList);
        } else {
            List<ProductCategory> categoryChildList = CategoryDataSource.getChildCategories(savedInstanceState.getInt("ParentCategory"));
            categorySecondChildListView.showProducts(categoryChildList);
        }
    }

    @Override
    public void updateCatalog() {
        //ProductDataSource productDataSource = new ProductDataSource(context);
        //viewCatalog.showProducts(productDataSource.getAllProducts());
    }

    @Override
    public void setView(CategorySecondChildListView categoryChildListView) {
        this.categorySecondChildListView = categoryChildListView;
    }

    @Override
    public void removeView() {
        categorySecondChildListView = null;
    }
}

package com.example.dan.mommarket.presenter;

import android.os.Bundle;

import com.example.dan.mommarket.database.CategoryDataSource;
import com.example.dan.mommarket.model.ProductCategory;
import com.example.dan.mommarket.view.CategoryListView;

import java.util.List;

/**
 * Created by dan on 26.08.16.
 */

public class CategoryListPresenterImpl implements CategoryListPresenter {

    private CategoryListView categoryListView;
    // private Context context;

    private List<ProductCategory> categoryList;

    public CategoryListPresenterImpl() {
    }
/*
    public PresenterCatalogImpl(Context context) {
        this.context = context;
    }
*/

    private static CategoryListPresenterImpl instance;

    public static synchronized CategoryListPresenterImpl getInstance() {
        if (instance == null) {
            instance = new CategoryListPresenterImpl();
        }
        return instance;
    }

    @Override
    public void onCreateView(Bundle savedIntanceState) {
        if (savedIntanceState == null) {

            //ProductDataSource productDataSource = new ProductDataSource(context);
            List<ProductCategory> categoryList = CategoryDataSource.getAllCategories();
            categoryListView.showProducts(categoryList);
        }
    }

    @Override
    public void updateCatalog() {
        //ProductDataSource productDataSource = new ProductDataSource(context);
        //viewCatalog.showProducts(productDataSource.getAllProducts());
    }

    @Override
    public void setView(CategoryListView categoryListView) {
        this.categoryListView = categoryListView;

    }

    @Override
    public void removeView() {
        categoryListView = null;
    }
}

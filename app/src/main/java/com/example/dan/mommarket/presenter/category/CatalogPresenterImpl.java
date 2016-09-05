package com.example.dan.mommarket.presenter.category;

import android.os.Bundle;

import com.example.dan.mommarket.database.CategoryDataSource;
import com.example.dan.mommarket.model.ProductCategory;
import com.example.dan.mommarket.views.Catalog;

import java.util.List;

/**
 * Created by dan on 26.08.16.
 */

public class CatalogPresenterImpl implements CatalogPresenter {

    private static CatalogPresenterImpl instance;
    private Catalog catalog;
    private List<ProductCategory> categoryList;

    public CatalogPresenterImpl() {
    }

    public static synchronized CatalogPresenterImpl getInstance() {
        if (instance == null) {
            instance = new CatalogPresenterImpl();
        }
        return instance;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            List<ProductCategory> categoryList = CategoryDataSource.getChildCategories(2);
            catalog.showProducts(categoryList);
        } else {
            List<ProductCategory> categoryList = CategoryDataSource.getChildCategories(savedInstanceState.getInt("ParentCategory"));
            catalog.showProducts(categoryList);
        }
    }

    @Override
    public void updateCatalog() {
        
    }

    @Override
    public void setView(Catalog catalog) {
        this.catalog = catalog;

    }

    @Override
    public void removeView() {
        catalog = null;
    }
}

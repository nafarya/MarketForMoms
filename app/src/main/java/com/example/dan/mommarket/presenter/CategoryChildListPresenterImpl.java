package com.example.dan.mommarket.presenter;

import android.os.Bundle;

import com.example.dan.mommarket.database.CategoryDataSource;
import com.example.dan.mommarket.model.ProductCategory;
import com.example.dan.mommarket.view.CategoryChildListView;
import com.example.dan.mommarket.view.CategoryListView;

import java.util.List;

/**
 * Created by dan on 26.08.16.
 */

public class CategoryChildListPresenterImpl implements CategoryChildListPresenter {

    private CategoryChildListView categoryChildListView;
    // private Context context;

    private List<ProductCategory> categoryList;

    public CategoryChildListPresenterImpl() {
    }

    private static CategoryChildListPresenterImpl instance;

    public static synchronized CategoryChildListPresenterImpl getInstance() {
        if (instance == null) {
            instance = new CategoryChildListPresenterImpl();
        }
        return instance;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            //ProductDataSource productDataSource = new ProductDataSource(context);
            List<ProductCategory> categoryChildList = CategoryDataSource.getChildCategories(7);
            categoryChildListView.showProducts(categoryChildList);
        }  else{
            List<ProductCategory> categoryChildList = CategoryDataSource.getChildCategories(savedInstanceState.getInt("ParentCategory"));
            categoryChildListView.showProducts(categoryChildList);
        }
    }

    @Override
    public void updateCatalog() {
        //ProductDataSource productDataSource = new ProductDataSource(context);
        //viewCatalog.showProducts(productDataSource.getAllProducts());
    }

    @Override
    public void setView(CategoryChildListView categoryChildListView) {
        this.categoryChildListView = categoryChildListView;
    }

    @Override
    public void removeView() {
        categoryChildListView = null;
    }
}

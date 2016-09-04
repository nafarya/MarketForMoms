package com.example.dan.mommarket.presenter.category;

import android.os.Bundle;

import com.example.dan.mommarket.database.CategoryDataSource;
import com.example.dan.mommarket.model.ProductCategory;
import com.example.dan.mommarket.views.Category;

import java.util.List;

/**
 * Created by dan on 26.08.16.
 */

public class CategoryPresenterImpl implements CategoryPresenter {

    private static CategoryPresenterImpl instance;
    // private Context context;
    private Category category;
    private List<ProductCategory> categoryList;

    public CategoryPresenterImpl() {
    }

    public static synchronized CategoryPresenterImpl getInstance() {
        if (instance == null) {
            instance = new CategoryPresenterImpl();
        }
        return instance;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            //ProductDataSource productDataSource = new ProductDataSource(context);
            List<ProductCategory> categoryChildList = CategoryDataSource.getChildCategories(7);
            category.showProducts(categoryChildList);
        }  else{
            List<ProductCategory> categoryChildList = CategoryDataSource.getChildCategories(savedInstanceState.getInt("ParentCategory"));
            category.showProducts(categoryChildList);
        }
    }

    @Override
    public void updateCatalog() {
        //ProductDataSource productDataSource = new ProductDataSource(context);
        //viewCatalog.showProducts(productDataSource.getAllProducts());
    }

    @Override
    public void setView(Category category) {
        this.category = category;
    }

    @Override
    public void removeView() {
        category = null;
    }
}

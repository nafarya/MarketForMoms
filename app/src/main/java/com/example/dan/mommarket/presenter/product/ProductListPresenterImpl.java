package com.example.dan.mommarket.presenter.product;

import android.os.Bundle;

import com.example.dan.mommarket.database.ProductDataSource;
import com.example.dan.mommarket.model.Product;
import com.example.dan.mommarket.views.ProductList;

import java.util.List;

/**
 * Created by dan on 26.08.16.
 */

public class ProductListPresenterImpl implements ProductListPresenter {

    private static ProductListPresenterImpl instance;
    private ProductList productList;

    public ProductListPresenterImpl() {
    }

    public static synchronized ProductListPresenterImpl getInstance() {
        if (instance == null) {
            instance = new ProductListPresenterImpl();
        }
        return instance;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            List<Product> productList = ProductDataSource.getAllProducts();
            this.productList.showProducts(productList);
        }
        if (savedInstanceState == null) {
            List<Product> productList = ProductDataSource.getAllProducts();
            this.productList.showProducts(productList);
        }  else{
            List<Product> productList = ProductDataSource.getListProductsByCategoryId(savedInstanceState.getInt("ParentCategory"));
            this.productList.showProducts(productList);
        }
    }

    @Override
    public void updateCatalog() {
        //ProductDataSource productDataSource = new ProductDataSource(context);
        //viewCatalog.showProducts(productDataSource.getAllProducts());
    }

    @Override
    public void setView(ProductList productList) {
        this.productList = productList;

    }

    @Override
    public void removeView() {
        productList = null;
    }
}

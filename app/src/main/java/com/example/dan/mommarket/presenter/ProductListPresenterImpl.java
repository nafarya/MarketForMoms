package com.example.dan.mommarket.presenter;

import android.os.Bundle;

import com.example.dan.mommarket.database.ProductDataSource;
import com.example.dan.mommarket.model.Product;
import com.example.dan.mommarket.view.ProductListView;

import java.util.List;

/**
 * Created by dan on 26.08.16.
 */

public class ProductListPresenterImpl implements ProductListPresenter {

    private ProductListView productListView;
    // private Context context;

    private List<Product> productList;

    public ProductListPresenterImpl() {
    }
/*
    public PresenterCatalogImpl(Context context) {
        this.context = context;
    }
*/

    private static ProductListPresenterImpl instance;

    public static synchronized ProductListPresenterImpl getInstance() {
        if (instance == null) {
            instance = new ProductListPresenterImpl();
        }
        return instance;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            //ProductDataSource productDataSource = new ProductDataSource(context);
            List<Product> productList = ProductDataSource.getAllProducts();
            productListView.showProducts(productList);
        }
        if (savedInstanceState == null) {
            List<Product> productList = ProductDataSource.getAllProducts();
            productListView.showProducts(productList);
        }  else{
            List<Product> productList = ProductDataSource.getListProductsByCategoryId(savedInstanceState.getInt("ParentCategory"));
            productListView.showProducts(productList);
        }
    }

    @Override
    public void updateCatalog() {
        //ProductDataSource productDataSource = new ProductDataSource(context);
        //viewCatalog.showProducts(productDataSource.getAllProducts());
    }

    @Override
    public void setView(ProductListView productListView) {
        this.productListView = productListView;

    }

    @Override
    public void removeView() {
        productListView = null;
    }
}

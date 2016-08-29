package com.example.dan.mommarket.presenter;

import android.content.Context;
import android.os.Bundle;

import com.example.dan.mommarket.adapter.CatalogRVAdapter;
import com.example.dan.mommarket.database.CategoryDataSource;
import com.example.dan.mommarket.database.Contract;
import com.example.dan.mommarket.database.ProductDataSource;
import com.example.dan.mommarket.model.Product;
import com.example.dan.mommarket.view.ViewCatalog;
import com.example.dan.mommarket.view.ViewCatalogImpl;

import java.util.List;

/**
 * Created by dan on 26.08.16.
 */

public class PresenterCatalogImpl implements PresenterCatalog {

    private ViewCatalog viewCatalog;
    private Context context;

    private List<Product> productList;

    public PresenterCatalogImpl() {}

    public PresenterCatalogImpl(Context context) {
        this.context = context;
    }

    @Override
    public void onCreateView(Bundle savedIntanceState) {
        if (savedIntanceState == null) {

            //ProductDataSource productDataSource = new ProductDataSource(context);
            List<Product> productList = ProductDataSource.getAllProducts();
            viewCatalog.showProducts(productList);
        }
    }

    @Override
    public void updateCatalog() {
        //ProductDataSource productDataSource = new ProductDataSource(context);
        //viewCatalog.showProducts(productDataSource.getAllProducts());
    }

    @Override
    public void setView(ViewCatalog viewCatalog) {
        this.viewCatalog = viewCatalog;

    }

    @Override
    public void removeView() {
        viewCatalog = null;
    }
}

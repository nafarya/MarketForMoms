package com.example.dan.mommarket.presenter;

import android.os.Bundle;

import com.example.dan.mommarket.database.ProductDataSource;
import com.example.dan.mommarket.model.Product;
import com.example.dan.mommarket.view.CatalogView;

import java.util.List;

/**
 * Created by dan on 26.08.16.
 */

public class CatalogPresenterImpl implements CatalogPresenter {

    private CatalogView catalogView;
    // private Context context;

    private List<Product> productList;

    public CatalogPresenterImpl() {
    }
/*
    public PresenterCatalogImpl(Context context) {
        this.context = context;
    }
*/

    private static CatalogPresenterImpl instance;

    public static synchronized CatalogPresenterImpl getInstance() {
        if (instance == null) {
            instance = new CatalogPresenterImpl();
        }
        return instance;
    }

    @Override
    public void onCreateView(Bundle savedIntanceState) {
        if (savedIntanceState == null) {

            //ProductDataSource productDataSource = new ProductDataSource(context);
            List<Product> productList = ProductDataSource.getAllProducts();
            catalogView.showProducts(productList);
        }
    }

    @Override
    public void updateCatalog() {
        //ProductDataSource productDataSource = new ProductDataSource(context);
        //viewCatalog.showProducts(productDataSource.getAllProducts());
    }

    @Override
    public void setView(CatalogView catalogView) {
        this.catalogView = catalogView;

    }

    @Override
    public void removeView() {
        catalogView = null;
    }
}

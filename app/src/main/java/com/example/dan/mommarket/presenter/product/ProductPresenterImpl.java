package com.example.dan.mommarket.presenter.product;

import android.os.Bundle;

import com.example.dan.mommarket.database.OfferDataSource;
import com.example.dan.mommarket.database.ProductDataSource;
import com.example.dan.mommarket.model.Offer;
import com.example.dan.mommarket.model.Product;
import com.example.dan.mommarket.views.ProductCard;

import java.util.List;

/**
 * Created by dan on 06.09.16.
 */


public class ProductPresenterImpl implements ProductPresenter {

    private static ProductPresenterImpl instance;
    private ProductCard productCard;
    private Product product;
    private List<Offer> offers;

    public ProductPresenterImpl() {
    }

    public static synchronized ProductPresenterImpl getInstance() {
        if (instance == null) {
            instance = new ProductPresenterImpl();
        }
        return instance;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            product = ProductDataSource.getProductById(1);
            this.productCard.showProduct(product);
            offers = OfferDataSource.getOffersByProductId(1);
            this.productCard.showOffers(offers);
        } else {
            product = ProductDataSource.getProductById(savedInstanceState.getInt("ProductId"));
            this.productCard.showProduct(product);
            offers = OfferDataSource.getOffersByProductId(savedInstanceState.getInt("ProductId"));
            this.productCard.showOffers(offers);
        }
    }

    @Override
    public void updateProductCard() {

    }

    @Override
    public void setView(ProductCard productCard) {
        this.productCard = productCard;
    }

    @Override
    public void removeView() {

    }
}

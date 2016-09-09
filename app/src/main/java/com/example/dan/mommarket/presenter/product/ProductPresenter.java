package com.example.dan.mommarket.presenter.product;

import android.os.Bundle;

import com.example.dan.mommarket.views.ProductCard;


/**
 * Created by dan on 06.09.16.
 */

public interface ProductPresenter {
    void onCreateView(Bundle savedInstanceState);

    void updateProductCard();

    void setView(ProductCard productCard);

    void removeView();
}

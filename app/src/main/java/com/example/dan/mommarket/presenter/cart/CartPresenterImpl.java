package com.example.dan.mommarket.presenter.cart;

import android.os.Bundle;
import android.util.Log;

import com.example.dan.mommarket.database.CartDataSource;
import com.example.dan.mommarket.presenter.category.CatalogPresenterImpl;
import com.example.dan.mommarket.views.CartRoot;

import static okhttp3.internal.Internal.instance;

/**
 * Created by dan on 09.09.16.
 */

public class CartPresenterImpl implements CartPresenter {

    private static CartPresenterImpl instance;
    private CartRoot cartRoot;

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        cartRoot.showShopList(CartDataSource.getCartList());
        Log.i("asdasda", String.valueOf(CartDataSource.getCartList().size()));
    }

    @Override
    public void setView(CartRoot cartRoot) {
        this.cartRoot = cartRoot;
    }

    @Override
    public void removeView() {

    }

    public static synchronized CartPresenterImpl getInstance() {
        if (instance == null) {
            instance = new CartPresenterImpl();
        }
        return instance;
    }
}

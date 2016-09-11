package com.example.dan.mommarket.presenter.cart;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dan.mommarket.database.CartDataSource;
import com.example.dan.mommarket.database.ShopDataSource;
import com.example.dan.mommarket.fragments.cart.CartPagerFragment;
import com.example.dan.mommarket.model.Cart;
import com.example.dan.mommarket.presenter.category.CatalogPresenterImpl;
import com.example.dan.mommarket.views.CartPager;
import com.example.dan.mommarket.views.CartRoot;

import static okhttp3.internal.Internal.instance;

/**
 * Created by dan on 09.09.16.
 */

public class CartPresenterImpl implements CartPresenter {

    private static CartPresenterImpl instance;
    private CartRoot cartRoot;
    private CartPager cartPager;
    private Cart cart;

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            cart = CartDataSource.getCart(1);
        } else {
            cart = CartDataSource.getCart(savedInstanceState.getInt("CartType"));
        }
        cartRoot.showShopList(ShopDataSource.getShopCartList());
    }

    @Override
    public void onCreatePagerView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            cart = CartDataSource.getCart(1);
        } else {
            cart = CartDataSource.getCart(savedInstanceState.getInt("CartType"));
        }
        cartPager.showCart(cart);
    }

    @Override
    public void setView(CartRoot cartRoot) {
        this.cartRoot = cartRoot;
    }

    @Override
    public void setPagerView(CartPager cartPager) {
        this.cartPager = cartPager;
    }

    @Override
    public void refreshShopList(int tag) {
        cart = CartDataSource.getCart(tag);
        cartRoot.showShopList(ShopDataSource.getShopCartList());
    }

    @Override
    public void refreshCartPager(int tag) {
        cart = CartDataSource.getCart(tag);
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

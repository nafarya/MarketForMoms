package com.example.dan.mommarket.presenter.cart;

import android.os.Bundle;

import com.example.dan.mommarket.database.CartDataSource;
import com.example.dan.mommarket.database.OfferItemDataSource;
import com.example.dan.mommarket.database.ShopDataSource;
import com.example.dan.mommarket.model.Cart;
import com.example.dan.mommarket.model.OfferItem;
import com.example.dan.mommarket.views.CartPager;
import com.example.dan.mommarket.views.CartRoot;
import com.example.dan.mommarket.views.OfferItemDialog;

/**
 * Created by dan on 09.09.16.
 */

public class CartPresenterImpl implements CartPresenter {

    private static CartPresenterImpl instance;
    private CartRoot cartRoot;
    private CartPager cartPager;
    private OfferItemDialog offerItemDialog;
    private Cart cart;
    private OfferItem offerItem;

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            cart = CartDataSource.getCart(1);
            cartRoot.showShopList(ShopDataSource.getShopCartList(1), 1);
        } else {
            cart = CartDataSource.getCart(savedInstanceState.getInt("CartType"));
            cartRoot.showShopList(ShopDataSource.getShopCartList(savedInstanceState.getInt("CartType")), savedInstanceState.getInt("CartType"));
        }
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
    public void onCreateOfferItemView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            offerItem = OfferItemDataSource.getOfferItemById(1);
        } else {
            offerItem = OfferItemDataSource.getOfferItemById(savedInstanceState.getInt("OfferItemId"));
        }
        offerItemDialog.showOfferItem(offerItem);
    }

    @Override
    public void setView(CartRoot cartRoot) {
        this.cartRoot = cartRoot;
    }

    @Override
    public void setDialogView(OfferItemDialog offerItemDialog) {
        this.offerItemDialog = offerItemDialog;
    }

    @Override
    public void setPagerView(CartPager cartPager) {
        this.cartPager = cartPager;
    }

    @Override
    public void refreshShopList(int tag) {
        cart = CartDataSource.getCart(tag);
        cartRoot.showShopList(ShopDataSource.getShopCartList(tag), tag);
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

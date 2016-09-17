package com.example.dan.mommarket.presenter.cart;

import android.os.Bundle;

import com.example.dan.mommarket.database.CartDataSource;
import com.example.dan.mommarket.database.ShopDataSource;
import com.example.dan.mommarket.model.Cart;
import com.example.dan.mommarket.model.Shop;
import com.example.dan.mommarket.views.CartOrder;
import com.example.dan.mommarket.views.CartOrderDelivery;

import java.util.List;

/**
 * Created by dan on 14.09.16.
 */

public class CartOrderPresenterImpl implements CartOrderPresenter {

    private static CartOrderPresenterImpl instance;
    private Cart cart;
    private CartOrderDelivery deliveryView;
    private CartOrder contectsView;
    private CartOrder paymentsView;
    private int orderId = 2;
    private List<Shop> shopList;

    public static synchronized CartOrderPresenterImpl getInstance() {
        if (instance == null) {
            instance = new CartOrderPresenterImpl();
        }
        return instance;
    }

    @Override
    public void onCreateDeliveryView(Bundle savedInstanceState) {
        cart = CartDataSource.getOrder(orderId);
        this.deliveryView.showOrder(cart);
        shopList = ShopDataSource.getShopListByCheckList(orderId);
        this.deliveryView.showShopList(shopList);
    }

    @Override
    public void onCreateContactsView(Bundle savedInstanceState) {
        cart = CartDataSource.getOrder(orderId);
        this.contectsView.showOrder(cart);
    }

    @Override
    public void onCreatePaymentsView(Bundle savedInstanceState) {
        cart = CartDataSource.getOrder(orderId);
        this.paymentsView.showOrder(cart);
    }

    @Override
    public void setDeliveryView(CartOrderDelivery cartOrder) {
        this.deliveryView = cartOrder;
    }

    @Override
    public void setContactsView(CartOrder cartOrder) {
        this.contectsView = cartOrder;
    }

    @Override
    public void setPaymentsView(CartOrder cartOrder) {
        this.paymentsView = cartOrder;
    }

    @Override
    public void updateCart(Cart cart) {
        this.cart.update();
    }

    @Override
    public void removeView() {
        this.paymentsView = null;
        this.contectsView = null;
        this.deliveryView = null;
    }
}

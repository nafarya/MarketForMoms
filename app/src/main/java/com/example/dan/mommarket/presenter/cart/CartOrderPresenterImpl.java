package com.example.dan.mommarket.presenter.cart;

import android.os.Bundle;

import com.example.dan.mommarket.database.CartDataSource;
import com.example.dan.mommarket.model.Cart;
import com.example.dan.mommarket.views.CartOrder;

/**
 * Created by dan on 14.09.16.
 */

public class CartOrderPresenterImpl implements CartOrderPresenter {

    private static CartOrderPresenterImpl instance;
    private Cart cart;
    private CartOrder deliveryView;
    private CartOrder contectsView;
    private CartOrder paymentsView;

    public static synchronized CartOrderPresenterImpl getInstance() {
        if (instance == null) {
            instance = new CartOrderPresenterImpl();
        }
        return instance;
    }

    @Override
    public void onCreateDeliveryView(Bundle savedInstanceState) {
        cart = CartDataSource.getOrderCart();
        this.deliveryView.showOrder(cart);
    }

    @Override
    public void onCreateContactsView(Bundle savedInstanceState) {
        cart = CartDataSource.getOrderCart();
        this.contectsView.showOrder(cart);
    }

    @Override
    public void onCreatePaymentsView(Bundle savedInstanceState) {
        cart = CartDataSource.getOrderCart();
        this.paymentsView.showOrder(cart);
    }

    @Override
    public void setDeliveryView(CartOrder cartOrder) {
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

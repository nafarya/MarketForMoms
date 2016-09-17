package com.example.dan.mommarket.presenter.cart;

import android.os.Bundle;

import com.example.dan.mommarket.model.Cart;
import com.example.dan.mommarket.views.CartOrder;
import com.example.dan.mommarket.views.CartPager;
import com.example.dan.mommarket.views.CartRoot;
import com.example.dan.mommarket.views.OfferItemDialog;

/**
 * Created by dan on 09.09.16.
 */

public interface CartOrderPresenter {
    void onCreateDeliveryView(Bundle savedInstanceState);

    void onCreateContactsView(Bundle savedInstanceState);

    void onCreatePaymentsView(Bundle savedInstanceState);

    void setDeliveryView(CartOrder cartOrder);

    void setContactsView(CartOrder cartOrder);

    void setPaymentsView(CartOrder cartOrder);

    void updateCart(Cart cart);

    void removeView();
}

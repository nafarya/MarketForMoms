package com.example.dan.mommarket.presenter.cart;

import android.os.Bundle;

import com.example.dan.mommarket.views.CartPager;
import com.example.dan.mommarket.views.CartRoot;
import com.example.dan.mommarket.views.OfferItemDialog;

/**
 * Created by dan on 09.09.16.
 */

public interface CartPresenter {
    void onCreateView(Bundle savedInstanceState);

    void onCreatePagerView(Bundle savedInstanceState);

    void onCreateOfferItemView(Bundle savedInstanceState);

    void setView(CartRoot cartRoot);

    void setDialogView(OfferItemDialog offerItemDialog);

    void setPagerView(CartPager cartPager, int pagerPosition);

    void refreshShopList(int tag);

    void refreshFragment();

    void setTag(int tag);

    void refreshCartPager(int tag);

    void removeView();

    void updateOrder();
}

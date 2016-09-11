package com.example.dan.mommarket.presenter.cart;

import android.os.Bundle;

import com.example.dan.mommarket.views.CartPager;
import com.example.dan.mommarket.views.CartRoot;

/**
 * Created by dan on 09.09.16.
 */

public interface CartPresenter {
    void onCreateView(Bundle savedInstanceState);

    void onCreatePagerView(Bundle savedInstanceState);

    void setView(CartRoot cartRoot);

    void setPagerView(CartPager cartPager);

    void refreshShopList(int tag);

    void refreshCartPager(int tag);

    void removeView();
}

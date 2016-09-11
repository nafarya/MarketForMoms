package com.example.dan.mommarket.presenter.cart;

import android.os.Bundle;
import android.view.View;

import com.example.dan.mommarket.views.CartRoot;

/**
 * Created by dan on 09.09.16.
 */

public interface CartPresenter {
    void onCreateView(Bundle savedInstanceState);

    void setView(CartRoot cartRoot);

    void refreshShopList(int tag);

    void removeView();
}

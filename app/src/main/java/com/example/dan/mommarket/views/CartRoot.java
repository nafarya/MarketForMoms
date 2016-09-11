package com.example.dan.mommarket.views;

import com.example.dan.mommarket.model.Cart;
import com.example.dan.mommarket.model.Shop;

import java.util.List;

/**
 * Created by dan on 09.09.16.
 */

public interface CartRoot {
    void showCartPager();
    void showShopList(List<Shop> shopList);
}

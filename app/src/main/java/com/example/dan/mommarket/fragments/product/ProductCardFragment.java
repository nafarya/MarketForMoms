package com.example.dan.mommarket.fragments.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.adapter.ShopListAdapter;
import com.example.dan.mommarket.model.Shop;
import com.example.dan.mommarket.views.ProductCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dan on 06.09.16.
 */

public class ProductCardFragment extends Fragment implements ProductCard {
    private RecyclerView recyclerView;
    private ShopListAdapter shopListAdapter;

    private List<Shop> shopList;
    Shop shop;


    @Override
    public void showProduct() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_product_card, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.rv_product_card_shops);
        shopList = new ArrayList<>();
        shop = new Shop();
        shop.setName("Lara Kids");
        shopList.add(shop);
        shopList.add(shop);
        shopList.add(shop);
        shopList.add(shop);
        shopListAdapter = new ShopListAdapter(shopList);
        recyclerView.setAdapter(shopListAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        return v;
    }
}

package com.example.dan.mommarket.fragments.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.adapter.CartRootPagerAdapter;
import com.example.dan.mommarket.views.CartRoot;

/**
 * Created by dan on 09.09.16.
 */

public class CartRootFragment extends Fragment implements CartRoot {
    private View view;
    private ViewPager viewPager;
    private CartRootPagerAdapter cartRootPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart_root, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.cart_root_view_pager);
        cartRootPagerAdapter = new CartRootPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(cartRootPagerAdapter);
        viewPager.setPageMargin(-100);
        return view;
    }

    @Override
    public void showShopList() {

    }


}

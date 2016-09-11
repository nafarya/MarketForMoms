package com.example.dan.mommarket.fragments.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.adapter.CartRootPagerAdapter;
import com.example.dan.mommarket.adapter.CartShopListAdapter;
import com.example.dan.mommarket.model.Shop;
import com.example.dan.mommarket.presenter.cart.CartPresenter;
import com.example.dan.mommarket.presenter.cart.CartPresenterImpl;
import com.example.dan.mommarket.views.CartRoot;

import java.util.List;

/**
 * Created by dan on 09.09.16.
 */

public class CartRootFragment extends Fragment implements CartRoot {
    private View view;
    private ViewPager viewPager;
    private CartRootPagerAdapter cartRootPagerAdapter;
    RecyclerView shopListRecyclerView;

    CartPresenter cartPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart_root, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.cart_root_view_pager);
        shopListRecyclerView = (RecyclerView) view.findViewById(R.id.cart_root_rv);

        cartPresenter = CartPresenterImpl.getInstance();
        cartPresenter.setView(this);
        cartPresenter.onCreateView(savedInstanceState != null ? savedInstanceState : this.getArguments());

        cartRootPagerAdapter = new CartRootPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(cartRootPagerAdapter);
        viewPager.setCurrentItem(1);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                cartPresenter.refreshShopList(position);
                cartPresenter.refreshCartPager(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return view;
    }
/*
    @Override
    public void showCartPager() {
        cartRootPagerAdapter = new CartRootPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(cartRootPagerAdapter);
        viewPager.setCurrentItem(1);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                cartPresenter.refreshShopList(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
*/
    @Override
    public void showShopList(List<Shop> shopList) {
        CartShopListAdapter cartShopListAdapter = new CartShopListAdapter(shopList);
        shopListRecyclerView.setAdapter(cartShopListAdapter);
        shopListRecyclerView.requestLayout();
    }

}

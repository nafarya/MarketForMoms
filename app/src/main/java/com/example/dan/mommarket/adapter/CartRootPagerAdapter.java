package com.example.dan.mommarket.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.example.dan.mommarket.fragments.cart.CartShopListFragment;

/**
 * Created by dan on 09.09.16.
 */

public class CartRootPagerAdapter extends FragmentStatePagerAdapter {

    private int pageCount = 3;

    public CartRootPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment curFragment = null;
        switch (position) {
            case 0:
                curFragment = new CartShopListFragment();
                break;
            case 1:
                curFragment = new CartShopListFragment();
                break;
            case 2:
                curFragment = new CartShopListFragment();
        }
        return  curFragment;

    }

    @Override
    public int getCount() {
        return pageCount;
    }
}

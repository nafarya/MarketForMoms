package com.example.dan.mommarket.adapter;

import android.os.Bundle;
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
        Bundle bundle = new Bundle();
        switch (position) {
            case 0:
                curFragment = new CartShopListFragment();
                bundle.putString("main_tag", "лучша цена");
                curFragment.setArguments(bundle);
                break;
            case 1:
                curFragment = new CartShopListFragment();
                bundle.putString("main_tag", "ваш выбор");
                curFragment.setArguments(bundle);
                break;
            case 2:
                curFragment = new CartShopListFragment();
                bundle.putString("main_tag", "быстрая доставка");
                curFragment.setArguments(bundle);
        }
        return  curFragment;

    }

    @Override
    public int getCount() {
        return pageCount;
    }
}

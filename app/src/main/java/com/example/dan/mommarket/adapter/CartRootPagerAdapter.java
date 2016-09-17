package com.example.dan.mommarket.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.example.dan.mommarket.fragments.cart.CartPagerFragment;

/**
 * Created by dan on 09.09.16.
 */

public class CartRootPagerAdapter extends FragmentStatePagerAdapter {

    private int pageCount = 3;

    public CartRootPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment curFragment = null;
        Bundle bundle = new Bundle();
        switch (position) {
            case 0:
                curFragment = new CartPagerFragment();
                bundle.putInt("CartType", 0);
                curFragment.setArguments(bundle);
                break;
            case 1:
                curFragment = new CartPagerFragment();
                bundle.putInt("CartType", 1);
                curFragment.setArguments(bundle);
                break;
            case 2:
                curFragment = new CartPagerFragment();
                bundle.putInt("CartType", 2);
                curFragment.setArguments(bundle);
        }
        return  curFragment;

    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        Log.i("pagerAdapter ", String.valueOf(position));
        return super.instantiateItem(container, position);
    }

    @Override
    public int getCount() {
        return pageCount;
    }
}

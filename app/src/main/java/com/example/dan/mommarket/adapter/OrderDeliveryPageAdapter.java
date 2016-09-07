package com.example.dan.mommarket.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dan.mommarket.fragments.order.OrderDeliveryCourierFragment;
import com.example.dan.mommarket.fragments.order.OrderDeliveryPickupFragment;

/**
 * Created by GEORGY on 06.09.2016.
 */

public class OrderDeliveryPageAdapter extends FragmentStatePagerAdapter {

    private final String courier = "Курьером";
    private final String pickup = "Самовывоз";
    private int tabCount = 2;

    public OrderDeliveryPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment curFragment = null;
        Bundle bundle = new Bundle();
        switch (position) {
            case 0:
                curFragment = new OrderDeliveryCourierFragment();
                break;
            case 1:
                curFragment = new OrderDeliveryPickupFragment();
                break;
        }
        return curFragment;
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = " ";
        switch (position) {
            case 0:
                title = courier;
                break;
            case 1:
                title = pickup;
                break;
        }
        return title;
    }
}
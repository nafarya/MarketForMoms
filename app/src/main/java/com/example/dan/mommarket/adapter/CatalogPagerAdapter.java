package com.example.dan.mommarket.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.view.CategoryListViewImpl;

/**
 * Created by dan on 03.09.16.
 */

public class CatalogPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount = 2;
    private final String forChildren = "для детей";
    private final String forParents = "для родителей";

    public CatalogPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment curFragment = null;
        Bundle bundle = new Bundle();
        switch (position) {
            case 0:
                curFragment = new CategoryListViewImpl();
                bundle.putInt("ParentCategory", 2);
                curFragment.setArguments(bundle);
                break;
            case 1:
                curFragment = new CategoryListViewImpl();
                bundle.putInt("ParentCategory", 3);
                curFragment.setArguments(bundle);
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
                title = forChildren;
                break;
            case 1:
                title = forParents;
                break;
        }
        return title;
    }
}

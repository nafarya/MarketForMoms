package com.example.dan.mommarket.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.adapter.CatalogPagerAdapter;

/**
 * Created by dan on 03.09.16.
 */

public class CategoryRootFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private CatalogPagerAdapter catalogPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog_root, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.root_catalog_tab_layout);
        viewPager = (ViewPager) view.findViewById(R.id.root_catalog_view_pager);
        catalogPagerAdapter = new CatalogPagerAdapter(getFragmentManager());
        viewPager.setAdapter(catalogPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}

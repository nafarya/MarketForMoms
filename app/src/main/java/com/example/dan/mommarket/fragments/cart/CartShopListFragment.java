package com.example.dan.mommarket.fragments.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.adapter.CartShopListAdapter;

/**
 * Created by dan on 10.09.16.
 */

public class CartShopListFragment extends Fragment {

    View view;
    TextView textTag;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart_shop_list, container, false);
        textTag = (TextView) view.findViewById(R.id.cart_shop_main_tag);
        textTag.setText(getArguments().getString("main_tag"));


        return view;
    }
}

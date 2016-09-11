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
import com.example.dan.mommarket.presenter.cart.CartPresenter;

/**
 * Created by dan on 10.09.16.
 */

public class CartPagerFragment extends Fragment {

    View view;
    TextView textTag;
    String text;
    RecyclerView recyclerView;
    CartPresenter cartPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart_pager, container, false);
        textTag = (TextView) view.findViewById(R.id.cart_shop_main_tag);
        switch (getArguments().getInt("main_tag")) {
            case 0:
                text  = "ЛУЧШАЯ ЦЕНА";
                break;
            case 1:
                text = "ВАШ ВЫБОР";
                break;
            case 2:
                text = "БЫСТРАЯ ДОСТАВКА";
                break;
        }
        textTag.setText(text);
        return view;
    }
}

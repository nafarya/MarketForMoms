package com.example.dan.mommarket.fragments.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.views.ProductCard;

/**
 * Created by dan on 06.09.16.
 */

public class ProductCardFragment extends Fragment implements ProductCard {
    @Override
    public void showProduct() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_products_card, container, false);
        return v;
    }
}

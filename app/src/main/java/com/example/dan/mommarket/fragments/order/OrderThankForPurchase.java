package com.example.dan.mommarket.fragments.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dan.mommarket.Navigator;
import com.example.dan.mommarket.R;

/**
 * Created by dan on 14.09.16.
 */

public class OrderThankForPurchase extends Fragment {
    private Navigator navigator;
    private Button backToCatalogButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.order_thank_for_purchase, container, false);
        backToCatalogButton = (Button) v.findViewById(R.id.order_thank_for_purchase_button_back_to_catalog);
        backToCatalogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigator.navigateToCatalog();
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navigator = (Navigator) getActivity();
    }
}

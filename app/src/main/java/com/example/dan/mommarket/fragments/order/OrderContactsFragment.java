package com.example.dan.mommarket.fragments.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dan.mommarket.Navigator;
import com.example.dan.mommarket.R;
import com.example.dan.mommarket.adapter.OrderDeliveryPageAdapter;

/**
 * Created by GEORGY on 06.09.2016.
 */

public class OrderContactsFragment extends Fragment {
    Navigator navigator;
    private TextView nextButton;
    private OrderDeliveryPageAdapter orderDeliveryPageAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_contacts, container, false);
        nextButton = (TextView) view.findViewById(R.id.delivery_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigateToOrder(3);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navigator = (Navigator) getActivity();
    }
}

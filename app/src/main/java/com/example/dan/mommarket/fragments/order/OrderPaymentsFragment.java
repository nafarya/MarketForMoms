package com.example.dan.mommarket.fragments.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.dan.mommarket.Navigator;
import com.example.dan.mommarket.R;
import com.example.dan.mommarket.model.Cart;
import com.example.dan.mommarket.presenter.cart.CartOrderPresenter;
import com.example.dan.mommarket.presenter.cart.CartOrderPresenterImpl;
import com.example.dan.mommarket.views.CartOrder;

/**
 * Created by GEORGY on 06.09.2016.
 */

public class OrderPaymentsFragment extends Fragment implements CartOrder {
    Navigator navigator;
    private TextView nextButton;
    private CartOrderPresenter cartOrderPresenter;
    View view;
    private Cart cart;
    private RadioButton cashView;
    private RadioButton cartView;
    private RadioGroup radioGroup;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.order_payments, container, false);

        radioGroup = ((RadioGroup) view.findViewById(R.id.ordar_payment_radio_group));
        cashView = ((RadioButton) view.findViewById(R.id.order_cash));
        cartView = ((RadioButton) view.findViewById(R.id.order_cart));

        cartOrderPresenter = CartOrderPresenterImpl.getInstance();
        cartOrderPresenter.setPaymentsView(this);
        cartOrderPresenter.onCreatePaymentsView(savedInstanceState);

        nextButton = (TextView) view.findViewById(R.id.order_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigateToOrder(4);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.order_cash) {
                    cart.setCash(true);
                } else if (i == R.id.order_cart) {
                    cart.setCash(false);
                }
            }
        });

        view.findViewById(R.id.order_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigateToOrder(1);
            }
        });

        view.findViewById(R.id.order_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigateToOrder(2);
            }
        });

        view.findViewById(R.id.order_tree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigateToOrder(3);
            }
        });

        view.findViewById(R.id.order_delivery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigateToOrder(1);
            }
        });

        view.findViewById(R.id.order_contacts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigateToOrder(2);
            }
        });

        view.findViewById(R.id.order_payments).setOnClickListener(new View.OnClickListener() {
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

    @Override
    public void showOrder(Cart cart) {
        this.cart =cart;
        ((TextView) view.findViewById(R.id.order_sum_value)).setText(String.valueOf(cart.getSum()) + " " + getResources().getString(R.string.currency));

        if (cart.isCash()) {
            cashView.setChecked(true);
        }else{
            cartView.setChecked(true);
        }
    }
}

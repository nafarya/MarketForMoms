package com.example.dan.mommarket.fragments.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

public class OrderDeliveryFragment extends Fragment implements CartOrder {
    Navigator navigator;
    View view;
    EditText cityView;
    EditText streetView;
    EditText appartmentView;
    EditText commentView;

    private CartOrderPresenter cartOrderPresenter;
    private TextView nextButton;
    private Cart cart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.order_delivery_main, container, false);

        cityView = (EditText) view.findViewById(R.id.order_city);
        streetView = (EditText) view.findViewById(R.id.order_street);
        appartmentView = (EditText) view.findViewById(R.id.order_appartment);
        commentView = (EditText) view.findViewById(R.id.order_comment);
        nextButton = (TextView) view.findViewById(R.id.order_next);

        cartOrderPresenter = CartOrderPresenterImpl.getInstance();
        cartOrderPresenter.setDeliveryView(this);
        cartOrderPresenter.onCreateDeliveryView(savedInstanceState);

        cityView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                cart.setCity(editable.toString());
            }
        });

        streetView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                cart.setStreet(editable.toString());
            }
        });

        appartmentView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                cart.setAppartment(editable.toString());
            }
        });

        commentView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                cart.setComment(editable.toString());
            }
        });



        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigateToOrder(2);
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
        this.cart = cart;
        ((TextView) view.findViewById(R.id.order_sum_value)).setText(String.valueOf(cart.getSum()) + " " + getResources().getString(R.string.currency));

        cityView.setText(String.valueOf(cart.getCity()));
        streetView.setText(String.valueOf(cart.getStreet()));
        appartmentView.setText(String.valueOf(cart.getAppartment()));
        commentView.setText(String.valueOf(cart.getComment()));
    }
}

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

public class OrderContactsFragment extends Fragment implements CartOrder {
    Navigator navigator;
    private TextView nextButton;
    View view;
    private CartOrderPresenter cartOrderPresenter;
    Cart cart;
    EditText firstNameView;
    EditText lastNameView;
    EditText phoneView;
    EditText emailView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.order_contacts, container, false);

        firstNameView = (EditText) view.findViewById(R.id.order_first_name);
        lastNameView = (EditText) view.findViewById(R.id.order_last_name);
        phoneView = (EditText) view.findViewById(R.id.order_phone);
        emailView = (EditText) view.findViewById(R.id.order_email);

        cartOrderPresenter = CartOrderPresenterImpl.getInstance();
        cartOrderPresenter.setContactsView(this);
        cartOrderPresenter.onCreateContactsView(savedInstanceState);

        nextButton = (TextView) view.findViewById(R.id.order_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigateToOrder(3);
            }
        });


        firstNameView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                cart.setFirstName(editable.toString());
            }
        });
        lastNameView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                cart.setLastName(editable.toString());
            }
        });
        phoneView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                cart.setPhone(editable.toString());
            }
        });
        emailView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                cart.setEmail(editable.toString());
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

        firstNameView.setText(String.valueOf(cart.getFirstName()));
        lastNameView.setText(String.valueOf(cart.getLastName()));
        phoneView.setText(String.valueOf(cart.getPhone()));
        emailView.setText(String.valueOf(cart.getEmail()));
    }
}

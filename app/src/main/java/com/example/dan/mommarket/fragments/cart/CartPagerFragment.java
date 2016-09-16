package com.example.dan.mommarket.fragments.cart;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.model.Cart;
import com.example.dan.mommarket.presenter.cart.CartPresenter;
import com.example.dan.mommarket.presenter.cart.CartPresenterImpl;
import com.example.dan.mommarket.views.CartPager;

/**
 * Created by dan on 10.09.16.
 */

public class CartPagerFragment extends Fragment implements CartPager {

    View view;
    TextView textTag;
    RecyclerView recyclerView;
    CartPresenter cartPresenter;
    Cart cart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart_pager, container, false);
        cartPresenter = CartPresenterImpl.getInstance();
        cartPresenter.setPagerView(this, this.getArguments().getInt("CartType"));
        cartPresenter.onCreatePagerView(this.getArguments());
        return view;
    }

    @Override
    public void showCart(Cart cart) {
        this.cart = cart;
        if (cart != null) {
            Resources resources = getResources();
            ((TextView) view.findViewById(R.id.cart_shop_name)).setText(cart.getName());
            ((TextView) view.findViewById(R.id.cart_shop_shop_count)).setText(resources.getQuantityString(R.plurals.from_shops, cart.getShopsCount(), cart.getShopsCount()));
            ((TextView) view.findViewById(R.id.cart_shop_sum)).setText(String.valueOf(cart.getSum()));
        }
    }
}

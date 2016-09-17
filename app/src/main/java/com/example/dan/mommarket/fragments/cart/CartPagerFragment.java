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

    private View view;
    private TextView textTag;
    private RecyclerView recyclerView;
    private CartPresenter cartPresenter;
    private Cart cart;
    private int cartType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart_pager, container, false);
        cartType = this.getArguments().getInt("CartType");
        cartPresenter = CartPresenterImpl.getInstance();
        cartPresenter.setPagerView(this, cartType);
        cartPresenter.onCreatePagerView(this.getArguments());
        return view;
    }

    @Override
    public void showCart(Cart cart) {
        this.cart = cart;
        if (cart != null) {
            Resources resources = getResources();
            ((TextView) view.findViewById(R.id.cart_shop_name)).setText(cart.getName());
//            ((TextView) view.findViewById(R.id.cart_shop_shop_count)).setText(resources.getQuantityString(R.plurals.from_shops, cart.getShopsCount(), cart.getShopsCount()));
            ((TextView) view.findViewById(R.id.cart_shop_shop_count)).setText(cart.getShopsCount() + " товаров");
            ((TextView) view.findViewById(R.id.cart_shop_sum)).setText(String.valueOf((int) (cart.getSum())) + " " + getActivity().getResources().getString(R.string.currency));

            switch (cartType) {
                case 0:
                    ((TextView) view.findViewById(R.id.cart_shop_name)).setTextColor(getResources().getColor(R.color.cart_pager_best_price));
                    break;
                case 1:
                    ((TextView) view.findViewById(R.id.cart_shop_name)).setTextColor(getResources().getColor(R.color.black_color));
                    break;
                case 2:
                    ((TextView) view.findViewById(R.id.cart_shop_name)).setTextColor(getResources().getColor(R.color.green_color));
                    break;
            }
        }
    }
}

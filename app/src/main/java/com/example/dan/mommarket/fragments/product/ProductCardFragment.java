package com.example.dan.mommarket.fragments.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.adapter.OfferListAdapter;
import com.example.dan.mommarket.model.Offer;
import com.example.dan.mommarket.model.Product;
import com.example.dan.mommarket.presenter.product.ProductPresenter;
import com.example.dan.mommarket.presenter.product.ProductPresenterImpl;
import com.example.dan.mommarket.views.ProductCard;

import java.util.List;

/**
 * Created by dan on 06.09.16.
 */

public class ProductCardFragment extends Fragment implements ProductCard {
    private RecyclerView recyclerView;
    private OfferListAdapter offerListAdapter;
    private Product product;
    private List<Offer> offers;
    private ProductPresenter productPresenter;

    @Override
    public void showProduct(Product product) {
        this.product = product;

    }

    @Override
    public void showOffers(List<Offer> offers) {
        this.offers = offers;
        offerListAdapter = new OfferListAdapter(offers);
        recyclerView.setAdapter(offerListAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_product_card, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.rv_product_card_shops);
        productPresenter = ProductPresenterImpl.getInstance();
        productPresenter.setView(this);
        productPresenter.onCreateView(savedInstanceState != null ? savedInstanceState : this.getArguments());
        return v;
    }
}

package com.example.dan.mommarket.fragments.product;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dan.mommarket.Navigator;
import com.example.dan.mommarket.R;
import com.example.dan.mommarket.adapter.OfferListAdapter;
import com.example.dan.mommarket.model.Offer;
import com.example.dan.mommarket.model.Product;
import com.example.dan.mommarket.presenter.product.ProductPresenter;
import com.example.dan.mommarket.presenter.product.ProductPresenterImpl;
import com.example.dan.mommarket.views.ProductCard;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dan on 06.09.16.
 */

public class ProductCardFragment extends Fragment implements ProductCard, OfferListAdapter.onAddToCartButtonClickListener {
    private RecyclerView recyclerView;
    private OfferListAdapter offerListAdapter;
    private Product product;
    private List<Offer> offers;
    private ProductPresenter productPresenter;
    private View view;
    private Navigator navigator;
    private TextView prevSizeButton;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navigator = (Navigator) getActivity();
    }

    @Override
    public void showProduct(Product product) {
        this.product = product;
        ((TextView)(view.findViewById(R.id.product_card_name))).setText(product.getName());
        ((TextView)(view.findViewById(R.id.product_card_feature))).setText(product.getCardFeatureValue());
        Picasso.with(getContext()).load(product.getFirstImage()).into((ImageView) view.findViewById(R.id.product_card_image));
    }

    @Override
    public void showOffers(List<Offer> offers) {
        this.offers = offers;
        offerListAdapter = new OfferListAdapter(this.offers, this,getContext());
        recyclerView.setAdapter(offerListAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product_card, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_product_card_shops);
        prevSizeButton = (TextView) view.findViewById(R.id.size30);
        initSizeButtons(view);
        productPresenter = ProductPresenterImpl.getInstance();
        productPresenter.setView(this);
        productPresenter.onCreateView(savedInstanceState != null ? savedInstanceState : this.getArguments());
        return view;
    }

    @Override
    public void onItemClick(final int item) {
        Snackbar snackbar = Snackbar.make(view, "Товар добавлен в корзину", Snackbar.LENGTH_LONG).
                setAction("Перейти", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        navigator.navigateToCart();
                    }
                });
        snackbar.setActionTextColor(Color.rgb(255, 222, 99));
        snackbar.show();
        productPresenter.addOffer(item);
    }

    public class SizeButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(final View v)
        {
            prevSizeButton.setBackgroundResource(R.drawable.order_gray_circle);
            v.setBackgroundResource(R.drawable.order_selected_circle);
            prevSizeButton = (TextView) v;
        }
    }

    private void initSizeButtons(View v) {
        TextView size30 = (TextView) view.findViewById(R.id.size30);
        size30.setOnClickListener(new SizeButtonClickListener());

        TextView size44 = (TextView) view.findViewById(R.id.size44);
        size44.setOnClickListener(new SizeButtonClickListener());

        TextView size50 = (TextView) view.findViewById(R.id.size50);
        size50.setOnClickListener(new SizeButtonClickListener());

        TextView size56 = (TextView) view.findViewById(R.id.size56);
        size56.setOnClickListener(new SizeButtonClickListener());

        TextView size62 = (TextView) view.findViewById(R.id.size62);
        size62.setOnClickListener(new SizeButtonClickListener());

        TextView size68 = (TextView) view.findViewById(R.id.size68);
        size68.setOnClickListener(new SizeButtonClickListener());

    }



}

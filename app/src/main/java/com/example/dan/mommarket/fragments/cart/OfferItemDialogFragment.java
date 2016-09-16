package com.example.dan.mommarket.fragments.cart;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dan.mommarket.Navigator;
import com.example.dan.mommarket.R;
import com.example.dan.mommarket.model.OfferItem;
import com.example.dan.mommarket.presenter.cart.CartPresenter;
import com.example.dan.mommarket.presenter.cart.CartPresenterImpl;
import com.example.dan.mommarket.views.OfferItemDialog;
import com.squareup.picasso.Picasso;

/**
 * Created by GEORGY on 13.09.2016.
 */

public class OfferItemDialogFragment extends DialogFragment implements OfferItemDialog, View.OnClickListener {

    View view;
    CartPresenter cartPresenter;
    Navigator navigator;
    private OfferItem offerItem;

    private TextView viewName;
    private TextView viewCount;
    private TextView viewPrice;
    private TextView viewFeature;
    private ImageView viewImage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.offer_item_dialog, container, false);

        viewName = (TextView) view.findViewById(R.id.offer_item_name);
        viewCount = (TextView) view.findViewById(R.id.offer_item_count);
        viewImage = (ImageView) view.findViewById(R.id.offer_item_image);
        viewPrice = (TextView) view.findViewById(R.id.offer_item_price);
        viewFeature = (TextView) view.findViewById(R.id.offer_item_feature);

        cartPresenter = CartPresenterImpl.getInstance();
        cartPresenter.setDialogView(this);
        cartPresenter.onCreateOfferItemView(savedInstanceState != null ? savedInstanceState : this.getArguments());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (offerItem.getCount() == 0) {
                    offerItem.deleteOfferItem();
                } else {
                    offerItem.updateCount();
                }
                cartPresenter.refreshFragment();
                getDialog().dismiss();
            }
        });

        view.findViewById(R.id.offer_item_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to disable clik on cart
            }
        });

        view.findViewById(R.id.offer_item_to_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigator.navigateToProductCard(offerItem.getProduct().getProductId());
                getDialog().dismiss();
            }
        });

        view.findViewById(R.id.offer_item_choose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (offerItem.getCount() == 0) {
                    offerItem.deleteOfferItem();
                } else {
                    offerItem.updateCount();
                }
                cartPresenter.refreshFragment();
                getDialog().dismiss();
            }
        });

        view.findViewById(R.id.offer_item_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offerItem.deleteOfferItem();
                cartPresenter.refreshFragment();
                getDialog().dismiss();
            }
        });

        view.findViewById(R.id.offer_item_plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offerItem.setCount(offerItem.getCount() + 1);
                viewCount.setText(String.valueOf(offerItem.getCount()));
                viewPrice.setText(String.valueOf((int) (offerItem.getOffer().getPrice() * offerItem.getCount())) + " " + getActivity().getResources().getString(R.string.currency));
                offerItem.updateCount();
            }
        });

        view.findViewById(R.id.offer_item_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (offerItem.getCount() > 0) {
                    offerItem.setCount(offerItem.getCount() - 1);
                    viewCount.setText(String.valueOf(offerItem.getCount()));
                    viewPrice.setText(String.valueOf((int) (offerItem.getOffer().getPrice() * offerItem.getCount())) + " " + getActivity().getResources().getString(R.string.currency));
                    offerItem.updateCount();
                }
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
    public void showOfferItem(OfferItem offerItem) {
        this.offerItem = offerItem;
        viewName.setText(offerItem.getProduct().getName());
        viewCount.setText(String.valueOf(offerItem.getCount()));
        Picasso.with(getContext()).load(offerItem.getProduct().getFirstImage()).into(viewImage);
        viewPrice.setText(String.valueOf((int) (offerItem.getOffer().getPrice() * offerItem.getCount())) + " " + getActivity().getResources().getString(R.string.currency));
        viewFeature.setText(offerItem.getProduct().getFeaturesString());
    }

    @Override
    public void onClick(View view) {
        navigator.navigateToCatalog();
    }
}

package com.example.dan.mommarket.fragments.cart;

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

public class OfferItemDialogFragment extends DialogFragment implements OfferItemDialog {

    View view;
    CartPresenter cartPresenter;
    Navigator navigator;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Mom_Dialog);
    }

    @Override
    public void onStart() {
        super.onStart();
      /*  Dialog dialog = getDialog();
        if (dialog != null) {
           // dialog.setContentView(root);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }*/
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.offer_item_dialog, container, false);
        cartPresenter = CartPresenterImpl.getInstance();
        cartPresenter.setDialogView(this);
        cartPresenter.onCreateOfferItemView(savedInstanceState != null ? savedInstanceState : this.getArguments());
        return view;
    }

    @Override
    public void showOfferItem(OfferItem offerItem) {

        ((TextView) view.findViewById(R.id.offer_item_name)).setText(offerItem.getProduct().getName());
        ((TextView) view.findViewById(R.id.offer_item_count)).setText(String.valueOf(offerItem.getCount()));
        Picasso.with(getContext()).load(offerItem.getProduct().getFirstImage()).into((ImageView) view.findViewById(R.id.offer_item_image));
        ((TextView) view.findViewById(R.id.offer_item_price)).setText(String.valueOf(offerItem.getOffer().getPrice() * offerItem.getCount()));
    }
}

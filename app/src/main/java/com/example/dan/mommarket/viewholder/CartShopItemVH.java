package com.example.dan.mommarket.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.adapter.CartShopOfferItemAdapter;
import com.example.dan.mommarket.database.OfferItemDataSource;

/**
 * Created by dan on 11.09.16.
 */

public class CartShopItemVH extends RecyclerView.ViewHolder {

    private TextView shopName;
    private RecyclerView recyclerView;

    public CartShopItemVH (View itemView, int shopId) {
        super(itemView);
        shopName = (TextView) itemView.findViewById(R.id.cart_shop_list_item_name);
        recyclerView = (RecyclerView) itemView.findViewById(R.id.cart_shop_list_item_rv);
        CartShopOfferItemAdapter adapter = new CartShopOfferItemAdapter(OfferItemDataSource.getOfferItemsByShopIdAndCart(shopId));
        recyclerView.setAdapter(adapter);
    }

    public TextView getShopName() {
        return shopName;
    }

    public void setShopName(TextView shopName) {
        this.shopName = shopName;
    }
}


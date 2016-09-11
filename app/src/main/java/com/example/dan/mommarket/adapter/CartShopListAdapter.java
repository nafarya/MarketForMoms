package com.example.dan.mommarket.adapter;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.model.Cart;
import com.example.dan.mommarket.model.Shop;

import java.util.List;

/**
 * Created by dan on 11.09.16.
 */

public class CartShopListAdapter extends RecyclerView.Adapter<CartShopListAdapter.ViewHolder> {
    List<Shop> shopList;

    public CartShopListAdapter(List<Shop> shopList) {
        this.shopList = shopList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_cart_shop_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.shopName.setText(shopList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView shopName;

        public ViewHolder(View itemView) {
            super(itemView);
            shopName = (TextView) itemView.findViewById(R.id.cart_shop_list_item_name);
        }
    }
}

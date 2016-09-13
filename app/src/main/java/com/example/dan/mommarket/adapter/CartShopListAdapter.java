package com.example.dan.mommarket.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.model.Shop;
import com.example.dan.mommarket.viewholder.CartShopItemVH;

import java.util.List;

/**
 * Created by dan on 11.09.16.
 */

public class CartShopListAdapter extends RecyclerView.Adapter<CartShopItemVH> {
    List<Shop> shopList;
    private int cartType;

    public CartShopListAdapter(List<Shop> shopList, int cartType) {
        this.shopList = shopList;
        this.cartType = cartType;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public CartShopItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_cart_shop_list_item, parent, false);
        return new CartShopItemVH(view, shopList.get(viewType).getId(), cartType);
    }

    @Override
    public void onBindViewHolder(CartShopItemVH holder, int position) {
        holder.getShopName().setText(shopList.get(position).getName());
        holder.getSum().setText(String.valueOf(shopList.get(position).getProductCartSum()));
        holder.getDeliveryPrice().setText(String.valueOf(shopList.get(position).getDeliveryPrice()));
        holder.getDeliveryTime().setText(shopList.get(position).getDeliveryTime());
        holder.getNumOfProduct().setText(String.valueOf(shopList.get(position).getProductCartCount()));
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }

}

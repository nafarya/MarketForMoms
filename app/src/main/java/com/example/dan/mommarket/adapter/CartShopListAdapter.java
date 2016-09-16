package com.example.dan.mommarket.adapter;

import android.content.Context;
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
    private Context context;

    public CartShopListAdapter(List<Shop> shopList, int cartType, Context context) {
        this.shopList = shopList;
        this.cartType = cartType;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public CartShopItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_cart_shop_list_item, parent, false);
        return new CartShopItemVH(view, shopList.get(viewType).getId(), cartType, context);
    }

    @Override
    public void onBindViewHolder(CartShopItemVH holder, int position) {
        Shop shop = shopList.get(position);
        String productCartCount = String.valueOf(shop.getProductCartCount());
        switch (shop.getProductCartCount()) {
            case 1:
                productCartCount += " товар";
                break;
            case 2:
                productCartCount += " товара";
                break;
            case 3:
                productCartCount += " товара";
                break;
            case 4:
                productCartCount += " товара";
                break;
            default:
                productCartCount += " товаров";
                break;
        }
        holder.getShopName().setText(shop.getName());
        holder.getSum().setText(String.valueOf(shop.getProductCartSum()));
        holder.getDeliveryPrice().setText(String.valueOf(shop.getDeliveryPrice()));
        holder.getDeliveryTime().setText(shop.getDeliveryTime());
        holder.getNumOfProduct().setText(productCartCount);
        holder.getRate().setRating(shop.getRate());
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }

}

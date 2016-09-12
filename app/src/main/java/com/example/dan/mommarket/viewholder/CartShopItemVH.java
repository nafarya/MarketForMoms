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
    private TextView numOfProduct;
    private TextView deliveryPrice;
    private TextView deliveryTime;
    private TextView sum;

    public TextView getSum() {
        return sum;
    }

    public void setSum(TextView sum) {
        this.sum = sum;
    }

    public TextView getNumOfProduct() {
        return numOfProduct;
    }

    public void setNumOfProduct(TextView numOfProduct) {
        this.numOfProduct = numOfProduct;
    }

    public TextView getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(TextView deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public TextView getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(TextView deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    private RecyclerView recyclerView;

    public CartShopItemVH (View itemView, int shopId, int cartType) {
        super(itemView);
        shopName = (TextView) itemView.findViewById(R.id.cart_shop_list_item_name);
        numOfProduct = (TextView) itemView.findViewById(R.id.cart_shop_list_item_num_of_products);
        deliveryPrice = (TextView) itemView.findViewById(R.id.cart_shop_list_item_delivery_price);
        deliveryTime = (TextView) itemView.findViewById(R.id.cart_shop_list_item_delivety_time);
        sum = (TextView) itemView.findViewById(R.id.cart_shop_list_item_sum);
        recyclerView = (RecyclerView) itemView.findViewById(R.id.cart_shop_list_item_rv);
        CartShopOfferItemAdapter adapter = new CartShopOfferItemAdapter(OfferItemDataSource.getOfferItemsByShopIdAndCart(shopId,cartType));
        recyclerView.setAdapter(adapter);
    }

    public TextView getShopName() {
        return shopName;
    }

    public void setShopName(TextView shopName) {
        this.shopName = shopName;
    }
}


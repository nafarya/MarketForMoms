package com.example.dan.mommarket.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.model.Shop;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by dan on 27.08.16.
 */

public class OrderShopListRVAdapter extends RecyclerView.Adapter<OrderShopListRVAdapter.ViewHolder> {

    private List<Shop> shopList;
    Context context;

    public OrderShopListRVAdapter(List<Shop> shopList, Context context) {
        this.shopList = shopList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_shop_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Resources resources = context.getResources();
        Shop shop = shopList.get(position);

        holder.name.setText(resources.getString(R.string.delivery_shop) + " " + shop.getName().toUpperCase());
        holder.deliveryPlace.setText(shop.getDeliveryPlace());

        String delivery = shop.getDeliveryPrice() != 0 ? String.valueOf(shop.getDeliveryPrice()) + " " + resources.getString(R.string.currency) : resources.getString(R.string.delivery_cost_free);

        Calendar deliveryDate = Calendar.getInstance();

        String deliveryDateString;

        switch ((int) Math.ceil(shop.getDeliveryTimeFloat())) {
            case 0:
                deliveryDateString = resources.getString(R.string.delivery_date_today);
                break;
            case 1:
                deliveryDateString = resources.getString(R.string.delivery_date_tommorow);
                break;
            default:
                deliveryDate.add(Calendar.DAY_OF_MONTH, (int) Math.ceil(shop.getDeliveryTimeFloat()));
                deliveryDateString = deliveryDate.get(Calendar.DAY_OF_MONTH) + " " + deliveryDate.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        }
        delivery += ", " + deliveryDateString;
        holder.delivery.setText(delivery);
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView deliveryPlace;
        private TextView delivery;
        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.order_delivery_shop_name);
            deliveryPlace = (TextView) itemView.findViewById(R.id.order_delivery_shop_delivery_place);
            delivery = (TextView) itemView.findViewById(R.id.order_delivery_shop_delivery);
        }
    }
}

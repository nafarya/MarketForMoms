package com.example.dan.mommarket.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.model.Shop;

import java.util.List;

/**
 * Created by dan on 08.09.16.
 */

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ViewHolder>  {

    private List<Shop> shopList;

    public ShopListAdapter(List<Shop> shopList) {
        this.shopList = shopList;
    }


    @Override
    public ShopListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(shopList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView date;
        private TextView fee;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.shop_list_shop_name);
            date = (TextView) itemView.findViewById(R.id.shop_list_date_id);
            fee = (TextView) itemView.findViewById(R.id.shop_list_fee_id);
        }
    }
}

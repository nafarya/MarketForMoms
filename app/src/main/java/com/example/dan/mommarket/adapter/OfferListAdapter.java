package com.example.dan.mommarket.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.model.Offer;

import java.util.List;

/**
 * Created by dan on 08.09.16.
 */

public class OfferListAdapter extends RecyclerView.Adapter<OfferListAdapter.ViewHolder> {

    private List<Offer> offerList;
    private onAddToCartButtonClickListener listener;

    public interface onAddToCartButtonClickListener {
        void onItemClick(int item);
    }

    public OfferListAdapter(List<Offer> offerList, onAddToCartButtonClickListener listener) {
        this.offerList = offerList;
        this.listener = listener;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public OfferListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType==0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_list_item_first, parent, false);
        }else if (viewType==1){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_list_item_second, parent, false);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_list_item, parent, false);
        }
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Offer offer = offerList.get(position);
        holder.name.setText(offer.getShop().getName());
        holder.price.setText(String.valueOf(offer.getPrice()));
        holder.fee.setText(String.valueOf(offer.getShop().getDeliveryPrice()));
        holder.date.setText(offer.getShop().getDeliveryTime());
    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name;
        private TextView date;
        private TextView fee;
        private TextView price;
        private onAddToCartButtonClickListener listener;

        public ViewHolder(View itemView, onAddToCartButtonClickListener listener) {
            super(itemView);
            this.listener = listener;
            name = (TextView) itemView.findViewById(R.id.shop_list_shop_name);
            date = (TextView) itemView.findViewById(R.id.shop_list_date);
            fee = (TextView) itemView.findViewById(R.id.shop_list_fee);
            price = (TextView) itemView.findViewById(R.id.shop_list_price);
            itemView.findViewById(R.id.shop_list_add_To_Cart_Button).setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }
}

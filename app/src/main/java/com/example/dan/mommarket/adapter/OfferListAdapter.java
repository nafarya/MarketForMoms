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
    public OfferListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_list_item, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Offer offer = offerList.get(position);
        holder.name.setText(offer.getShop().getName());
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
        private onAddToCartButtonClickListener listener;

        public ViewHolder(View itemView, onAddToCartButtonClickListener listener) {
            super(itemView);
            this.listener = listener;
            name = (TextView) itemView.findViewById(R.id.shop_list_shop_name);
            date = (TextView) itemView.findViewById(R.id.shop_list_date_id);
            fee = (TextView) itemView.findViewById(R.id.shop_list_fee_id);
            itemView.findViewById(R.id.shop_list_add_To_Cart_Button).setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }
}

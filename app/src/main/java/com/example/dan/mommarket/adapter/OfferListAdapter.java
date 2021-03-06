package com.example.dan.mommarket.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
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
    private Context context;

    public interface onAddToCartButtonClickListener {
        void onItemClick(int item);
    }

    public OfferListAdapter(List<Offer> offerList, onAddToCartButtonClickListener listener, Context context) {
        this.offerList = offerList;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public OfferListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_list_item, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Resources resources = context.getResources();
        if (position == 0) {
            holder.header_text.setText(R.string.best_offer);
        } else if (position == 1) {
            holder.header_text.setText(R.string.other_offers);
        } else {
            holder.header_text.setVisibility(View.GONE);
        }
        Offer offer = offerList.get(position);
        holder.name.setText(offer.getShop().getName());
        holder.price.setText(String.valueOf((int) offer.getPrice()) + " " + resources.getString(R.string.currency));
        holder.fee.setText(offer.getShop().getDeliveryPrice() != 0 ? String.valueOf(offer.getShop().getDeliveryPrice()) + " " + resources.getString(R.string.currency) : resources.getString(R.string.delivery_cost_free));
        holder.date.setText(offer.getShop().getDeliveryTime() + ",");
        holder.rate.setRating(offer.getShop().getRate());
    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView header_text;
        private TextView name;
        private TextView date;
        private TextView fee;
        private TextView price;
        private RatingBar rate;
        private onAddToCartButtonClickListener listener;

        public ViewHolder(View itemView, onAddToCartButtonClickListener listener) {
            super(itemView);
            this.listener = listener;
            header_text = (TextView) itemView.findViewById(R.id.shop_list_header_text);
            name = (TextView) itemView.findViewById(R.id.shop_list_shop_name);
            date = (TextView) itemView.findViewById(R.id.shop_list_date);
            fee = (TextView) itemView.findViewById(R.id.shop_list_fee);
            price = (TextView) itemView.findViewById(R.id.shop_list_price);
            rate = (RatingBar) itemView.findViewById(R.id.shop_list_shop_rate);
            itemView.findViewById(R.id.shop_list_add_To_Cart_Button).setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }
}

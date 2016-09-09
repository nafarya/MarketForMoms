package com.example.dan.mommarket.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dan on 27.08.16.
 */

public class ProductListRVAdapter extends RecyclerView.Adapter<ProductListRVAdapter.ViewHolder> {

    private List<Product> productList;
    private Context context;
    private OnProductListRvClickListener listener;

    public interface OnProductListRvClickListener{
        void onItemClick(int item);
    }

    public ProductListRVAdapter(List<Product> productList, Context context, OnProductListRvClickListener listener) {
        this.productList = productList;
        this.context = context;
        this.listener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_offer_card, parent, false);
        return new ViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.name.setText(product.getName());
        holder.price.setText(String.valueOf(product.getPrice()));
        holder.feature.setText(product.getCardFeatureValue());
        Picasso.with(context).load(product.getFirstImage()).into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name;
        private ImageView icon;
        private TextView price;
        private TextView feature;
        private OnProductListRvClickListener listener;

        public ViewHolder(View itemView, OnProductListRvClickListener listener) {
            super(itemView);
            this.listener = listener;
            name = (TextView) itemView.findViewById(R.id.product_card_name);
            price = (TextView) itemView.findViewById(R.id.product_card_min_price);
            icon = (ImageView) itemView.findViewById(R.id.product_card_image);
            feature = (TextView) itemView.findViewById(R.id.product_card_feature);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(productList.get(getAdapterPosition()).getProductId());
        }
    }
}

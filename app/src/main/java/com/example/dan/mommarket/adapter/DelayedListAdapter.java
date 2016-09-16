package com.example.dan.mommarket.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
;import com.example.dan.mommarket.R;
import com.example.dan.mommarket.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dan on 14.09.16.
 */

public class DelayedListAdapter extends RecyclerView.Adapter<DelayedListAdapter.ViewHolder> {
    private List<Product> productList;
    private Context context;
    private OnDeleteButtonClickListener listener;

    public DelayedListAdapter(List<Product> productList, Context context, OnDeleteButtonClickListener listener) {
        this.productList = productList;
        this.context = context;
        this.listener = listener;
    }

    public interface OnDeleteButtonClickListener {
        void onItemClick(int item);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_delayed_list_item, parent, false);
        return new ViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.name.setText(product.getName());
        holder.price.setText(String.valueOf(product.getPrice()));
        holder.deleteButton.setBackgroundResource(R.mipmap.ic_delete);
        Picasso.with(context).load(product.getFirstImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView name;
        private TextView price;
        private ImageButton deleteButton;
        private ImageView image;
        private OnDeleteButtonClickListener listener;

        public ViewHolder(View itemView, OnDeleteButtonClickListener listener) {
            super(itemView);
            this.listener = listener;
            name = (TextView) itemView.findViewById(R.id.delayed_list_item_name);
            price = (TextView) itemView.findViewById(R.id.delayed_list_item_price);
            deleteButton = (ImageButton) itemView.findViewById(R.id.delayed_list_item_button);
            image = (ImageView) itemView.findViewById(R.id.delayed_list_item_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.delayed_list_item_button) {
                listener.onItemClick(productList.get(getAdapterPosition()).getProductId());
            }
        }
    }
}

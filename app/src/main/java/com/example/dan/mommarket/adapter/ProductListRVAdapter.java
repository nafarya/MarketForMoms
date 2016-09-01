package com.example.dan.mommarket.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.dan.mommarket.R;
import com.example.dan.mommarket.model.Product;

import java.util.List;

/**
 * Created by dan on 27.08.16.
 */

public class ProductListRVAdapter extends RecyclerView.Adapter<ProductListRVAdapter.ViewHolder> {

    private List<Product> productList;

    public ProductListRVAdapter(List<Product> productList) {
        this.productList = productList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_product_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.description.setText(product.getDescription());
        holder.name.setText(product.getName());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView description;
        private ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.rv_product_list_name);
            icon = (ImageView) itemView.findViewById(R.id.rv_product_list_image);
            description = (TextView) itemView.findViewById(R.id.rv_product_list_description);
        }
    }
}

package com.example.dan.mommarket.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.model.ProductCategory;

import java.util.List;

/**
 * Created by dan on 27.08.16.
 */

public class CategoryChildListRVAdapter extends RecyclerView.Adapter<CategoryChildListRVAdapter.ViewHolder> {

    private List<ProductCategory> categoryChildList;

    public CategoryChildListRVAdapter(List<ProductCategory> categoryChildList) {
        this.categoryChildList = categoryChildList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_category_child_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProductCategory category = categoryChildList.get(position);
        holder.name.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return categoryChildList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;


        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.rv_category_child_list_item_name);
        }
    }
}

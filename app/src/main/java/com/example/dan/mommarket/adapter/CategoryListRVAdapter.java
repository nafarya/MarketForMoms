package com.example.dan.mommarket.adapter;

import android.content.Context;
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

public class CategoryListRVAdapter extends RecyclerView.Adapter<CategoryListRVAdapter.ViewHolder> {

    private List<ProductCategory> categoryList;
    private Context context;
    private OnCategoryListClickListener onCategoryListClickListener;

    public CategoryListRVAdapter(List<ProductCategory> categoryList, Context context, OnCategoryListClickListener listener) {
        this.categoryList = categoryList;
        this.context = context;
        this.onCategoryListClickListener=listener;
    }

    public interface OnCategoryListClickListener {
        void onItemClick(int item,int childCount);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_category_list_item, parent, false);
        return new ViewHolder(v,onCategoryListClickListener);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProductCategory category = categoryList.get(position);
        holder.description.setText(category.getDescription());
        holder.name.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private TextView description;
        private ImageView icon;
        private OnCategoryListClickListener listener;

        public ViewHolder(View itemView,
                          OnCategoryListClickListener listener) {
            super(itemView);
            this.listener = listener;
            name = (TextView) itemView.findViewById(R.id.rv_category_list_item_name);
            icon = (ImageView) itemView.findViewById(R.id.rv_category_list_item_image);
            description = (TextView) itemView.findViewById(R.id.rv_category_list_item_description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(categoryList.get(getAdapterPosition()).getId(),categoryList.get(getAdapterPosition()).getChildCount());
        }
    }
}

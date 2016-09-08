package com.example.dan.mommarket.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.model.ProductCategory;

import java.util.List;

/**
 * Created by dan on 27.08.16.
 */

public class CategoryChildListRVAdapter extends RecyclerView.Adapter<CategoryChildListRVAdapter.ViewHolder> {

    private List<ProductCategory> categoryChildList;
    private Context context;
    private OnCategoryChildListRVAdapterClickListener onCategoryListClickListener;

    public CategoryChildListRVAdapter(List<ProductCategory> categoryList, Context context, OnCategoryChildListRVAdapterClickListener listener) {
        this.categoryChildList = categoryList;
        this.context = context;
        this.onCategoryListClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_category_child_list_item, parent, false);
        return new ViewHolder(v, onCategoryListClickListener);
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

    public interface OnCategoryChildListRVAdapterClickListener {
        void onItemClick(int item, int childCount);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private OnCategoryChildListRVAdapterClickListener listener;

        public ViewHolder(View itemView,
                          OnCategoryChildListRVAdapterClickListener listener) {
            super(itemView);
            this.listener = listener;
            name = (TextView) itemView.findViewById(R.id.rv_category_child_list_item_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(categoryChildList.get(getAdapterPosition()).getId(), categoryChildList.get(getAdapterPosition()).getChildCount());
        }
    }
}

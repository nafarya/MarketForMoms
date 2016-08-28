package com.example.dan.mommarket.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.model.Advice;
import com.example.dan.mommarket.model.Product;

import java.util.List;

/**
 * Created by dan on 27.08.16.
 */

public class AdviceListRVAdapter extends RecyclerView.Adapter<AdviceListRVAdapter.ViewHolder> {

    private List<Advice> adviceList;

    public AdviceListRVAdapter(List<Advice> adviceList) {
        this.adviceList = adviceList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_advice_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Advice advice = adviceList.get(position);
        holder.description.setText(advice.getShortDescription());
        holder.name.setText(advice.getName());
    }

    @Override
    public int getItemCount() {
        return adviceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView description;
        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.rv_advice_list_item_name);
            image = (ImageView) itemView.findViewById(R.id.rv_advice_list_item_image);
            description = (TextView) itemView.findViewById(R.id.rv_advice_list_item_description);
        }
    }
}

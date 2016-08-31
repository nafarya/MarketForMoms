package com.example.dan.mommarket.adapter;import android.content.Context;import android.support.v7.widget.RecyclerView;import android.util.Log;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.ImageView;import android.widget.TextView;import com.example.dan.mommarket.R;import com.example.dan.mommarket.model.Advice;import com.squareup.picasso.Picasso;import java.util.List;/** * Created by dan on 27.08.16. */public class MainAdviceListRVAdapter extends RecyclerView.Adapter<MainAdviceListRVAdapter.ViewHolder> {    private List<Advice> adviceList;    private Context context;    private OnAdviceClickListener onAdviceClickListener;    public interface OnAdviceClickListener {        void onItemClick(int item);    }    public MainAdviceListRVAdapter(List<Advice> adviceList, Context context, OnAdviceClickListener listener) {        this.adviceList = adviceList;        this.onAdviceClickListener = listener;        this.context = context;    }    @Override    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_main_advice_list_item, parent, false);        return new ViewHolder(v, onAdviceClickListener);    }    @Override    public void onBindViewHolder(ViewHolder holder, int position) {        Advice advice = adviceList.get(position);        holder.description.setText(advice.getShortDescription());        holder.name.setText(advice.getName());        Picasso.with(context).load(advice.getImageURL()).into(holder.image);    }    @Override    public int getItemCount() {        return adviceList.size();    }    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {        private TextView name;        private TextView description;        private ImageView image;        private OnAdviceClickListener listener;        public ViewHolder(View itemView, OnAdviceClickListener listener) {            super(itemView);            this.listener = listener;            name = (TextView) itemView.findViewById(R.id.rv_advice_list_item_name);            image = (ImageView) itemView.findViewById(R.id.rv_advice_list_item_image);            description = (TextView) itemView.findViewById(R.id.rv_advice_list_item_description);            itemView.setOnClickListener(this);        }        @Override        public void onClick(View view) {            listener.onItemClick(getAdapterPosition());        }    }}
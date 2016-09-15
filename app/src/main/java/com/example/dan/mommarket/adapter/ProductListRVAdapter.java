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
import android.widget.Toast;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by dan on 27.08.16.
 */

public class ProductListRVAdapter extends RecyclerView.Adapter<ProductListRVAdapter.ViewHolder> {

    private List<Product> productList;
    private Context context;
    private OnProductListRvClickListener listener;
    private OnBookmarkClickListener bookMarklistener;
    private boolean bookmarkFlag = false;

    public interface OnProductListRvClickListener {
        void onProductClick(int item);
    }

    public interface OnBookmarkClickListener {
        void onBookMarkClick(int item);
    }

    public ProductListRVAdapter(List<Product> productList, Context context, OnProductListRvClickListener listener, OnBookmarkClickListener bookMarklistener) {
        this.productList = productList;
        this.context = context;
        this.listener = listener;
        this.bookMarklistener = bookMarklistener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_offer_card, parent, false);
        return new ViewHolder(v, listener, bookMarklistener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = productList.get(position);
        if (product.isBookmark()) {
            holder.bookmark.setBackgroundResource(R.drawable.ic_bookmark_black_24dp);
        } else {
            holder.bookmark.setBackgroundResource(R.drawable.ic_bookmark_border_black_24dp);
        }
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
        private ImageButton bookmark;
        private OnProductListRvClickListener listener;
        private OnBookmarkClickListener bookmarkListener;

        public ViewHolder(View itemView, OnProductListRvClickListener listener, OnBookmarkClickListener bookmarkClickListener) {
            super(itemView);
            this.listener = listener;
            this.bookmarkListener = bookmarkClickListener;
            bookmark = (ImageButton) itemView.findViewById(R.id.bookmark_button);
            name = (TextView) itemView.findViewById(R.id.product_card_name);
            price = (TextView) itemView.findViewById(R.id.product_card_min_price);
            icon = (ImageView) itemView.findViewById(R.id.product_card_image);
            feature = (TextView) itemView.findViewById(R.id.product_card_feature);
            itemView.setOnClickListener(this);
            bookmark.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.bookmark_button) {
                if (productList.get(getAdapterPosition()).isBookmark()) {
                    productList.get(getAdapterPosition()).setBookmark(false);
                    bookmark.setBackgroundResource(R.drawable.ic_bookmark_border_black_24dp);
                } else {
                    productList.get(getAdapterPosition()).setBookmark(true);
                    bookmark.setBackgroundResource(R.drawable.ic_bookmark_black_24dp);
                }
            } else {
                listener.onProductClick(productList.get(getAdapterPosition()).getProductId());
            }
        }
    }
}

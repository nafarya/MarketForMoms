package com.example.dan.mommarket.fragments.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dan.mommarket.Navigator;
import com.example.dan.mommarket.R;
import com.example.dan.mommarket.adapter.OfferListAdapter;
import com.example.dan.mommarket.adapter.ProductListRVAdapter;
import com.example.dan.mommarket.model.Product;
import com.example.dan.mommarket.presenter.product.ProductListPresenter;
import com.example.dan.mommarket.presenter.product.ProductListPresenterImpl;
import com.example.dan.mommarket.views.ProductList;

import java.util.List;

/**
 * Created by dan on 26.08.16.
 */

public class ProductListFragment extends Fragment implements ProductList, ProductListRVAdapter.OnProductListRvClickListener, ProductListRVAdapter.OnBookmarkClickListener{


    private final int spanCount = 2;
    ProductListPresenter productListPresenter;
    private View v;
    private ProductListRVAdapter productListRVAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager grid;
    private Navigator navigator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_product_list, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.rv_product_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        grid = new GridLayoutManager(getContext(), spanCount);
        recyclerView.setLayoutManager(grid);
        productListPresenter = ProductListPresenterImpl.getInstance();
        productListPresenter.setView(this);
        productListPresenter.onCreateView(savedInstanceState != null ? savedInstanceState : this.getArguments());
        return v;
    }

    @Override
    public void showProducts(List<Product> productsList) {
        productListRVAdapter = new ProductListRVAdapter(productsList, getContext(), this, this);
        recyclerView.setAdapter(productListRVAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navigator = (Navigator) getActivity();
    }

    @Override
    public void onProductClick(int item) {
        navigator.navigateToProductCard(item);
    }

    @Override
    public void onBookMarkClick(int item) {
        Toast.makeText(getContext(), "BOOKMARK", Toast.LENGTH_SHORT).show();
    }
}

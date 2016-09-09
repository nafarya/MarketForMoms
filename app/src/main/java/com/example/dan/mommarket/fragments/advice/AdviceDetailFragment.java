package com.example.dan.mommarket.fragments.advice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.mommarket.Navigator;
import com.example.dan.mommarket.R;
import com.example.dan.mommarket.adapter.ProductListRVAdapter;
import com.example.dan.mommarket.model.Product;
import com.example.dan.mommarket.presenter.advice.AdviceDetailPresenter;
import com.example.dan.mommarket.presenter.advice.AdviceDetailPresenterImpl;
import com.example.dan.mommarket.views.AdviceDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dan on 30.08.16.
 */

public class AdviceDetailFragment extends Fragment implements AdviceDetail, ProductListRVAdapter.OnProductListRvClickListener {

    AdviceDetailPresenter adviceDetailPresenter;
    private RecyclerView productsRecyclerView1;
    private RecyclerView productsRecyclerView2;
    private RecyclerView productsRecyclerView3;
    private ProductListRVAdapter adapter;
    private GridLayoutManager grid;
    private GridLayoutManager grid1;
    private GridLayoutManager grid2;
    private GridLayoutManager grid3;
    private int spanCount = 2;
    private int productsToShow = 2;
    Navigator navigator;

    @Override
    public void showAdvices() {

    }

    @Override
    public void showProducts(List<Product> productList) {
        List<Product> productListToShow = new ArrayList<>();
        for (int i = 0; i < productsToShow; i++) {
            productListToShow.add(productList.get(i));
        }
        adapter = new ProductListRVAdapter(productListToShow, getContext(), this);
        initProductsRV(productsRecyclerView1, adapter);
        initProductsRV(productsRecyclerView2, adapter);
        initProductsRV(productsRecyclerView3, adapter);
    }

    void initProductsRV(RecyclerView rv, ProductListRVAdapter adapter) {
        grid = new GridLayoutManager(getContext(), spanCount);
        rv.setLayoutManager(grid);
        rv.setNestedScrollingEnabled(false);
        rv.setAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.advice_detail, container, false);
        productsRecyclerView1 = (RecyclerView) v.findViewById(R.id.advice_detail_productsrv1);
        productsRecyclerView2 = (RecyclerView) v.findViewById(R.id.advice_detail_productsrv2);
        productsRecyclerView3 = (RecyclerView) v.findViewById(R.id.advice_detail_productsrv3);

        adviceDetailPresenter = AdviceDetailPresenterImpl.getInstance();
        adviceDetailPresenter.setView(this);
        adviceDetailPresenter.onCreateView(savedInstanceState);
        return  v;
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
}

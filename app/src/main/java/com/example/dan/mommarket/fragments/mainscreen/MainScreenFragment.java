package com.example.dan.mommarket.fragments.mainscreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.dan.mommarket.Navigator;
import com.example.dan.mommarket.R;
import com.example.dan.mommarket.adapter.ProductListRVAdapter;
import com.example.dan.mommarket.adapter.AdviceListRVAdapter;
import com.example.dan.mommarket.model.Advice;
import com.example.dan.mommarket.model.Product;
import com.example.dan.mommarket.presenter.mainscreen.MainScreenPresenter;
import com.example.dan.mommarket.presenter.mainscreen.MainScreenPresenterImpl;
import com.example.dan.mommarket.views.MainScreen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dan on 02.09.16.
 */

public class MainScreenFragment extends Fragment implements MainScreen, AdviceListRVAdapter.OnAdviceClickListener, ProductListRVAdapter.OnProductListRvClickListener, ProductListRVAdapter.OnBookmarkClickListener{
    private RecyclerView adviceRecyclerView;
    private RecyclerView productRecyclerView;
    private MainScreenPresenter mainScreenPresenter;
    private ProductListRVAdapter productListRVAdapter;
    private GridLayoutManager grid;
    private int spanCount = 2;
    private AdviceListRVAdapter adviceListRVAdapter;
    private Navigator navigator;

    @Override
    public void showAdvices(List<Advice> adviceList, int numOfAdvices) {
        List<Advice> adviceListToShow = new ArrayList<>();
        for (int i = 0; i < numOfAdvices; i++) {
            adviceListToShow.add(adviceList.get(i));
        }
        adviceListRVAdapter = new AdviceListRVAdapter(adviceListToShow, getContext(), this);
        adviceRecyclerView.setAdapter(adviceListRVAdapter);
        adviceRecyclerView.setNestedScrollingEnabled(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_screen, container, false);
        adviceRecyclerView = (RecyclerView) v.findViewById(R.id.main_screen_advice_rv);
        productRecyclerView = (RecyclerView) v.findViewById(R.id.main_screen_rv_products_id);
        mainScreenPresenter = new MainScreenPresenterImpl();
        mainScreenPresenter.setView(this);
        mainScreenPresenter.onCreateView(savedInstanceState);
        initUIElements(v);
        return v;
    }

    private void initUIElements(View v) {
        Button button = (Button) v.findViewById(R.id.from_main_screen_to_catalog_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigator.navigateToCatalog();
            }
        });
    }

    @Override
    public void showProducts(List<Product> productList) {
        grid = new GridLayoutManager(getContext(), spanCount);
        productRecyclerView.setLayoutManager(grid);
        productRecyclerView.setNestedScrollingEnabled(false);
        productListRVAdapter = new ProductListRVAdapter(productList, getContext(), this, this);
//        productListRVAdapter.setNavigator(navigator);
        productRecyclerView.setAdapter(productListRVAdapter);
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
    public void onItemClick(int item) {
        navigator.navigateToAdviceDetail(item);
    }

    @Override
    public void onBookMarkClick(int item) {
        Toast.makeText(getContext(), "BOOKMARK", Toast.LENGTH_SHORT).show();
    }
}

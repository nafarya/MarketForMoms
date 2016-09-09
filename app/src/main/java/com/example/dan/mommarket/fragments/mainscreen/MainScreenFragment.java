package com.example.dan.mommarket.fragments.mainscreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dan.mommarket.Navigator;
import com.example.dan.mommarket.R;
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

public class MainScreenFragment extends Fragment implements MainScreen, AdviceListRVAdapter.OnAdviceClickListener {
    private RecyclerView adviceRecyclerView;
    private MainScreenPresenter mainScreenPresenter;
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

        initSubProductCardElements(v.findViewById(R.id.product_offer_1));
        initSubProductCardElements(v.findViewById(R.id.product_offer_2));
        initSubProductCardElements(v.findViewById(R.id.product_offer_3));


    }

    private void initSubProductCardElements(View v) {
        v.findViewById(R.id.product_card_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigator.navigateToProductCard(220101);
            }
        });

        v.findViewById(R.id.product_card_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigator.navigateToProductCard(220102);
            }
        });

    }

    @Override
    public void showProducts(List<Product> productList) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navigator = (Navigator) getActivity();
    }

    @Override
    public void onItemClick(int item) {
        navigator.navigateToAdviceDetail(item);
    }


}

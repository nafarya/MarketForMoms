package com.example.dan.mommarket.fragments.delayed;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.adapter.DelayedListAdapter;
import com.example.dan.mommarket.model.Product;
import com.example.dan.mommarket.presenter.delayed.DelayedPresenter;
import com.example.dan.mommarket.presenter.delayed.DelayedPresenterImpl;
import com.example.dan.mommarket.views.Delayed;

import java.util.List;

/**
 * Created by dan on 14.09.16.
 */

public class DelayedListFragment extends Fragment implements Delayed, DelayedListAdapter.OnDeleteButtonClickListener {

    private View view;
    private List<Product> productList;
    private RecyclerView recyclerView;
    private DelayedListAdapter adapter;
    private DelayedPresenter delayedPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_delayed_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.delayed_list_rv);
        delayedPresenter = DelayedPresenterImpl.getInstance();
        delayedPresenter.setView(this);
        delayedPresenter.onCreateView(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void showDelayedProducts(List<Product> productList) {
        this.productList = productList;
        adapter = new DelayedListAdapter(productList, getContext(), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int item) {

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}

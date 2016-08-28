package com.example.dan.mommarket.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.adapter.CatalogRVAdapter;
import com.example.dan.mommarket.model.Product;
import com.example.dan.mommarket.presenter.PresenterCatalog;
import com.example.dan.mommarket.presenter.PresenterCatalogImpl;

import java.util.List;

/**
 * Created by dan on 26.08.16.
 */

public class ViewCatalogImpl extends Fragment implements ViewCatalog {


    PresenterCatalog presenterCatalog;
    private View v;
    private CatalogRVAdapter catalogRVAdapter;
    private RecyclerView recyclerView;
    private Context context;

    public void setContext (Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_catalog, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.rv_catalog);
        presenterCatalog = new PresenterCatalogImpl(context);
        presenterCatalog.setView(this);
        presenterCatalog.onCreateView(savedInstanceState);
        return v;
    }

    @Override
    public void showProducts(List<Product> productsList) {
        catalogRVAdapter = new CatalogRVAdapter(productsList);
        recyclerView.setAdapter(catalogRVAdapter);
    }
}

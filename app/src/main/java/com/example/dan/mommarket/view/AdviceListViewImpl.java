package com.example.dan.mommarket.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.adapter.AdviceListRVAdapter;
import com.example.dan.mommarket.model.Advice;
import com.example.dan.mommarket.presenter.AdviceListPresenter;
import com.example.dan.mommarket.presenter.AdviceListPresenterImpl;

import java.util.List;

/**
 * Created by dan on 26.08.16.
 */

public class AdviceListViewImpl extends Fragment implements AdviceListView {


    AdviceListPresenter adviceListPresenter;
    private View v;
    private AdviceListRVAdapter adviceListRVAdapter;
    private RecyclerView recyclerView;
//    private Context context;
/*
    public void setContext (Context context){
        this.context = context;
    }
*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_catalog, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.rv_catalog);
//        catalogPresenter = new CatalogPresenterImpl(context);
        adviceListPresenter = AdviceListPresenterImpl.getInstance();
        adviceListPresenter.setView(this);
        adviceListPresenter.onCreateView(savedInstanceState);
        return v;
    }

    @Override
    public void showAdvices(List<Advice> adviceList) {
        adviceListRVAdapter = new AdviceListRVAdapter(adviceList);
        recyclerView.setAdapter(adviceListRVAdapter);
    }
}

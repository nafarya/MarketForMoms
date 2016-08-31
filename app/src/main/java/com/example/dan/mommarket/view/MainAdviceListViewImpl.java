package com.example.dan.mommarket.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dan.mommarket.Navigator;
import com.example.dan.mommarket.R;
import com.example.dan.mommarket.adapter.MainAdviceListRVAdapter;
import com.example.dan.mommarket.model.Advice;
import com.example.dan.mommarket.presenter.MainAdviceListPresenter;
import com.example.dan.mommarket.presenter.MainAdviceListPresenterImpl;

import java.util.List;

/**
 * Created by dan on 26.08.16.
 */

public class MainAdviceListViewImpl extends Fragment implements MainAdviceListView, MainAdviceListRVAdapter.OnAdviceClickListener {


    MainAdviceListPresenter mainAdviceListPresenter;
    private View v;
    private MainAdviceListRVAdapter mainAdviceListRVAdapter;
    private RecyclerView recyclerView;
    private Navigator navigator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_main_advice_list, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.rv_advice_list);
        mainAdviceListPresenter = MainAdviceListPresenterImpl.getInstance();
        mainAdviceListPresenter.setView(this);
        mainAdviceListPresenter.onCreateView(savedInstanceState);
        return v;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(getActivity(), "HELL YEAH BITCHES " + item.getItemId(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(getActivity(), "HELL YEAH BITCHES", Toast.LENGTH_SHORT).show();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navigator = (Navigator) getActivity();
    }

    @Override
    public void showAdvices(List<Advice> adviceList) {
        mainAdviceListRVAdapter = new MainAdviceListRVAdapter(adviceList, getContext(), this);
        recyclerView.setAdapter(mainAdviceListRVAdapter);
    }

    @Override
    public void onItemClick(int item) {
        navigator.navigateToAdviseList();
    }
}

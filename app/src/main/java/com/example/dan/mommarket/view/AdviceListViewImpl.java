package com.example.dan.mommarket.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.mommarket.Navigator;
import com.example.dan.mommarket.R;
import com.example.dan.mommarket.presenter.AdviceListPresenter;
import com.example.dan.mommarket.presenter.AdviceListPresenterImpl;

/**
 * Created by dan on 30.08.16.
 */

public class AdviceListViewImpl extends Fragment implements AdviceListView {

    AdviceListPresenter adviceListPresenter;

    @Override
    public void showAdvices() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.advice_detail, container, false);
        adviceListPresenter = AdviceListPresenterImpl.getInstance();
        adviceListPresenter.setView(this);
        adviceListPresenter.onCreateView(savedInstanceState);
        return  v;
    }
}

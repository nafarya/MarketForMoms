package com.example.dan.mommarket.fragments.advice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.model.Product;
import com.example.dan.mommarket.presenter.advice.AdviceDetailPresenter;
import com.example.dan.mommarket.presenter.advice.AdviceDetailPresenterImpl;
import com.example.dan.mommarket.views.AdviceDetail;

import java.util.List;

/**
 * Created by dan on 30.08.16.
 */

public class AdviceDetailFragment extends Fragment implements AdviceDetail {

    AdviceDetailPresenter adviceDetailPresenter;

    @Override
    public void showAdvices() {

    }

    @Override
    public void showProducts(List<Product> productList) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.advice_detail, container, false);
        adviceDetailPresenter = AdviceDetailPresenterImpl.getInstance();
        adviceDetailPresenter.setView(this);
        adviceDetailPresenter.onCreateView(savedInstanceState);
        return  v;
    }
}

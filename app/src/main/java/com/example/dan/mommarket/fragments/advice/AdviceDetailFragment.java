package com.example.dan.mommarket.fragments.advice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.model.Advice;
import com.example.dan.mommarket.presenter.advice.AdviceDetailPresenter;
import com.example.dan.mommarket.presenter.advice.AdviceDetailPresenterImpl;
import com.example.dan.mommarket.views.AdviceDetail;

/**
 * Created by dan on 30.08.16.
 */

public class AdviceDetailFragment extends Fragment implements AdviceDetail {

    AdviceDetailPresenter adviceDetailPresenter;
    View view;

    @Override
    public void showAdvice(Advice advice) {
        ( (TextView) view.findViewById(R.id.advice_detail_name)).setText(advice.getName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.advice_detail, container, false);
        adviceDetailPresenter = AdviceDetailPresenterImpl.getInstance();
        adviceDetailPresenter.setView(this);
        adviceDetailPresenter.onCreateView(savedInstanceState != null ? savedInstanceState : this.getArguments());
        return  view;
    }
}

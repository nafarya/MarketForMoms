package com.example.dan.mommarket.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.mommarket.R;
import com.example.dan.mommarket.adapter.CategoryChildListRVAdapter;
import com.example.dan.mommarket.adapter.CategoryListRVAdapter;
import com.example.dan.mommarket.model.ProductCategory;
import com.example.dan.mommarket.presenter.CategoryChildListPresenter;
import com.example.dan.mommarket.presenter.CategoryChildListPresenterImpl;
import com.example.dan.mommarket.presenter.CategoryListPresenter;
import com.example.dan.mommarket.presenter.CategoryListPresenterImpl;

import java.util.List;

/**
 * Created by dan on 26.08.16.
 */

public class CategoryChildListViewImpl extends Fragment implements CategoryChildListView {


    CategoryChildListPresenter categoryChildListPresenter;
    private View v;
    private CategoryChildListRVAdapter categoryChildRVAdapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_category_child_list, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.rv_category_child_list);
//        categoryChildListPresenter = new CategoryListPresenterImpl(context);
        categoryChildListPresenter = CategoryChildListPresenterImpl.getInstance();
        categoryChildListPresenter.setView(this);
        categoryChildListPresenter.onCreateView(savedInstanceState!=null ? savedInstanceState : this.getArguments());
        return v;
    }

    @Override
    public void showProducts(List<ProductCategory> categoryList) {
        categoryChildRVAdapter = new CategoryChildListRVAdapter(categoryList);
        recyclerView.setAdapter(categoryChildRVAdapter);
    }
}

package com.example.dan.mommarket.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.mommarket.Navigator;
import com.example.dan.mommarket.R;
import com.example.dan.mommarket.adapter.CategoryListRVAdapter;
import com.example.dan.mommarket.model.ProductCategory;
import com.example.dan.mommarket.presenter.CategoryListPresenter;
import com.example.dan.mommarket.presenter.CategoryListPresenterImpl;

import java.util.List;

/**
 * Created by dan on 26.08.16.
 */

public class CategoryListViewImpl extends Fragment implements CategoryListView, CategoryListRVAdapter.OnCategoryListClickListener  {


    CategoryListPresenter categoryListPresenter;
    private View v;
    private CategoryListRVAdapter categoryRVAdapter;
    private RecyclerView recyclerView;
    Navigator navigator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_category_main, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.rv_category_list);
        categoryListPresenter = CategoryListPresenterImpl.getInstance();
        categoryListPresenter.setView(this);
        categoryListPresenter.onCreateView(savedInstanceState);
        return v;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navigator = (Navigator) getActivity();
    }
    @Override
    public void showProducts(List<ProductCategory> categoryList) {
        categoryRVAdapter = new CategoryListRVAdapter(categoryList, getContext(), this);
        recyclerView.setAdapter(categoryRVAdapter);
    }

    @Override
    public void onItemClick(int item,int childCount) {
        navigator.navigateToCategoryChildList(item,childCount);
    }
}

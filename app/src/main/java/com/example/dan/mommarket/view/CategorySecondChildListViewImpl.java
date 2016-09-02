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
import com.example.dan.mommarket.adapter.CategoryChildListRVAdapter;
import com.example.dan.mommarket.model.ProductCategory;
import com.example.dan.mommarket.presenter.CategoryChildListPresenter;
import com.example.dan.mommarket.presenter.CategoryChildListPresenterImpl;
import com.example.dan.mommarket.presenter.CategorySecondChildListPresenter;
import com.example.dan.mommarket.presenter.CategorySecondChildListPresenterImpl;

import java.util.List;

/**
 * Created by dan on 26.08.16.
 */

public class CategorySecondChildListViewImpl extends Fragment implements CategorySecondChildListView, CategoryChildListRVAdapter.OnCategoryChildListRVAdapterClickListener {


    CategorySecondChildListPresenter categorySecondChildListPresenter;
    private View v;
    private CategoryChildListRVAdapter categoryChildRVAdapter;
    private RecyclerView recyclerView;
    private Navigator navigator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_category_child_list, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.rv_category_child_list);
//        categoryChildListPresenter = new CategoryListPresenterImpl(context);
        categorySecondChildListPresenter = CategorySecondChildListPresenterImpl.getInstance();
        categorySecondChildListPresenter.setView(this);
        categorySecondChildListPresenter.onCreateView(savedInstanceState != null ? savedInstanceState : this.getArguments());
        return v;
    }

    @Override
    public void showProducts(List<ProductCategory> categoryList) {
        categoryChildRVAdapter = new CategoryChildListRVAdapter(categoryList, getContext(), this);
        recyclerView.setAdapter(categoryChildRVAdapter);
        navigator = (Navigator) getActivity();
    }

    @Override
    public void onItemClick(int item, int childCount) {
        navigator.navigateToCategorySecondChildList(item, childCount);
    }
}

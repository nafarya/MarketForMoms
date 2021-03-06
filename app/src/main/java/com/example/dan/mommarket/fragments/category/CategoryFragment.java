package com.example.dan.mommarket.fragments.category;

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
import com.example.dan.mommarket.presenter.category.CategoryPresenter;
import com.example.dan.mommarket.presenter.category.CategoryPresenterImpl;
import com.example.dan.mommarket.views.Category;

import java.util.List;

/**
 * Created by dan on 26.08.16.
 */

public class CategoryFragment extends Fragment implements Category, CategoryListRVAdapter.OnCategoryListClickListener {


    CategoryPresenter categoryPresenter;
    private View v;
    private CategoryListRVAdapter categoryChildRVAdapter;
    private RecyclerView recyclerView;
    private Navigator navigator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_category_child_list, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.rv_category_child_list);
        categoryPresenter = CategoryPresenterImpl.getInstance();
        categoryPresenter.setView(this);
        categoryPresenter.onCreateView(savedInstanceState != null ? savedInstanceState : this.getArguments());

        return v;
    }

    @Override
    public void showProducts(List<ProductCategory> categoryList) {
        categoryChildRVAdapter = new CategoryListRVAdapter(categoryList, getContext(), this);
        recyclerView.setAdapter(categoryChildRVAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navigator = (Navigator) getActivity();
    }

    @Override
    public void onItemClick(int item, int childCount) {
        navigator.navigateToSubCategory(item, childCount);
    }
}

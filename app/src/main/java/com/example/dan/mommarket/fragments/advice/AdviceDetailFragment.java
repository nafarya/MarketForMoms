package com.example.dan.mommarket.fragments.advice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dan.mommarket.Navigator;
import com.example.dan.mommarket.R;
import com.example.dan.mommarket.adapter.ProductListRVAdapter;
import com.example.dan.mommarket.model.Advice;
import com.example.dan.mommarket.model.Product;
import com.example.dan.mommarket.presenter.advice.AdviceDetailPresenter;
import com.example.dan.mommarket.presenter.advice.AdviceDetailPresenterImpl;
import com.example.dan.mommarket.views.AdviceDetail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dan on 30.08.16.
 */

public class AdviceDetailFragment extends Fragment implements AdviceDetail, ProductListRVAdapter.OnProductListRvClickListener, ProductListRVAdapter.OnBookmarkClickListener {

    AdviceDetailPresenter adviceDetailPresenter;
    private RecyclerView productsRecyclerView1;
    private RecyclerView productsRecyclerView2;
    private RecyclerView productsRecyclerView3;
    private ProductListRVAdapter adapter;
    private GridLayoutManager grid;
    private int spanCount = 2;
    private int productsToShow = 2;
    private Button goToCatalog;
    Navigator navigator;
    Advice advice;
    View view;

    @Override
    public void showAdvice(Advice advice) {
        this.advice = advice;
        Picasso.with(getContext()).load(advice.getImage0()).into((ImageView) view.findViewById(R.id.advice_image));
        ((TextView) view.findViewById(R.id.advice_detail_name)).setText(advice.getName());
        Picasso.with(getContext()).load(advice.getAuthorImage()).into((ImageView) view.findViewById(R.id.author_photo));
        ((TextView) view.findViewById(R.id.author_name)).setText(advice.getAuthorName());
        ((TextView) view.findViewById(R.id.author_text)).setText(advice.getAuthorText());
        ((TextView) view.findViewById(R.id.advice_text0)).setText(Html.fromHtml(advice.getText0()));
        ((TextView) view.findViewById(R.id.advice_header1)).setText(advice.getHeader1());
        ((TextView) view.findViewById(R.id.advice_text1)).setText(Html.fromHtml(advice.getText1()));
        Picasso.with(getContext()).load(advice.getImage1()).into((ImageView) view.findViewById(R.id.advice_image1));
        ((TextView) view.findViewById(R.id.advice_header2)).setText(advice.getHeader2());
        ((TextView) view.findViewById(R.id.advice_text2)).setText(Html.fromHtml(advice.getText2()));
        ((TextView) view.findViewById(R.id.product_category_button1).findViewById(R.id.polzunki_button_id)).setText(advice.getCategory1().getName());
     //   ((TextView) view.findViewById(R.id.product_category_button1).findViewById(R.id.polzunki_product_count)).setText(String.valueOf(advice.getCategory1().getProductCount()));

        ((TextView) view.findViewById(R.id.product_category_button2).findViewById(R.id.polzunki_button_id)).setText(advice.getCategory2().getName());
     //   ((TextView) view.findViewById(R.id.product_category_button2).findViewById(R.id.polzunki_product_count)).setText(String.valueOf(advice.getCategory2().getProductCount()));

        ((TextView) view.findViewById(R.id.product_category_button3).findViewById(R.id.polzunki_button_id)).setText(advice.getCategory3().getName());
     //   ((TextView) view.findViewById(R.id.product_category_button3).findViewById(R.id.polzunki_product_count)).setText(String.valueOf(advice.getCategory3().getProductCount()));
    }

    @Override
    public void showProducts(List<Product> productList1, List<Product> productList2, List<Product> productList3) {
        List<Product> productListToShow = new ArrayList<>();
        for (int i = 0; i < productsToShow; i++) {
            productListToShow.add(productList1.get(i));
        }
        adapter = new ProductListRVAdapter(productListToShow, getContext(), this, this);
        initProductsRV(productsRecyclerView1, adapter);

        productListToShow = new ArrayList<>();
        for (int i = 0; i < productsToShow; i++) {
            productListToShow.add(productList2.get(i));
        }
        adapter = new ProductListRVAdapter(productListToShow, getContext(), this, this);
        initProductsRV(productsRecyclerView2, adapter);

        productListToShow = new ArrayList<>();
        for (int i = 0; i < productsToShow; i++) {
            productListToShow.add(productList3.get(i));
        }
        adapter = new ProductListRVAdapter(productListToShow, getContext(), this, this);
        initProductsRV(productsRecyclerView3, adapter);
    }

    void initProductsRV(RecyclerView rv, ProductListRVAdapter adapter) {
        grid = new GridLayoutManager(getContext(), spanCount);
        rv.setLayoutManager(grid);
        rv.setNestedScrollingEnabled(false);
        rv.setAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.advice_detail, container, false);
        productsRecyclerView1 = (RecyclerView) view.findViewById(R.id.advice_detail_productsrv1);
        productsRecyclerView2 = (RecyclerView) view.findViewById(R.id.advice_detail_productsrv2);
        productsRecyclerView3 = (RecyclerView) view.findViewById(R.id.advice_detail_productsrv3);

        adviceDetailPresenter = AdviceDetailPresenterImpl.getInstance();
        adviceDetailPresenter.setView(this);
        adviceDetailPresenter.onCreateView(savedInstanceState != null ? savedInstanceState : this.getArguments());

        goToCatalog = (Button) view.findViewById(R.id.advice_detail_go_to_category_button);
        goToCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigator.navigateToCatalog();
            }
        });

        view.findViewById(R.id.product_category_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigator.navigateToProductList(advice.getCategory1().getId());
            }
        });

        view.findViewById(R.id.product_category_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigator.navigateToProductList(advice.getCategory2().getId());
            }
        });

        view.findViewById(R.id.product_category_button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigator.navigateToProductList(advice.getCategory3().getId());
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navigator = (Navigator) getActivity();
    }

    @Override
    public void onProductClick(int item) {
        navigator.navigateToProductCard(item);
    }

    @Override
    public void onBookMarkClick(int item) {
        Toast.makeText(getContext(), "BOOKMARK", Toast.LENGTH_SHORT).show();
    }
}

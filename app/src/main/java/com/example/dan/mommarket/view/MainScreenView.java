package com.example.dan.mommarket.view;

import com.example.dan.mommarket.model.Advice;
import com.example.dan.mommarket.model.Product;

import java.util.List;

/**
 * Created by dan on 02.09.16.
 */

public interface MainScreenView {
    void showAdvices(List<Advice> adviceList, int numOfAdvices);
    void showProducts(List<Product> productList);
}

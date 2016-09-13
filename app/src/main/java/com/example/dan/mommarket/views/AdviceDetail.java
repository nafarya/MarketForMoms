package com.example.dan.mommarket.views;


import com.example.dan.mommarket.model.Advice;
import com.example.dan.mommarket.model.Product;

import java.util.List;

/**
 * Created by dan on 30.08.16.
 */

public interface AdviceDetail {
    void showProducts(List<Product> productList1, List<Product> productList2, List<Product> productList3);

    void showAdvice(Advice advice);
}

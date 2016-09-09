package com.example.dan.mommarket.views;


import com.example.dan.mommarket.model.Product;

import java.util.List;
import com.example.dan.mommarket.model.Advice;

/**
 * Created by dan on 30.08.16.
 */

public interface AdviceDetail {
    void showProducts(List<Product> productList);
    void showAdvice(Advice advice);
}

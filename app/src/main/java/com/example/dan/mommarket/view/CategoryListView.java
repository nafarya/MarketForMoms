package com.example.dan.mommarket.view;


import com.example.dan.mommarket.model.Product;
import com.example.dan.mommarket.model.ProductCategory;

import java.util.List;

/**
 * Created by dan on 26.08.16.
 */

public interface CategoryListView {
    void showProducts(List<ProductCategory> categoryList);
}
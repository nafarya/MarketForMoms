package com.example.dan.mommarket.views;

import com.example.dan.mommarket.model.Offer;
import com.example.dan.mommarket.model.Product;

import java.util.List;

/**
 * Created by dan on 06.09.16.
 */

public interface ProductCard {
    void showProduct(Product product);
    void showOffers(List<Offer> offers);
}

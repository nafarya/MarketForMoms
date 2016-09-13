package com.example.dan.mommarket;

/**
 * Created by dan on 30.08.16.
 */

public interface Navigator {

    void navigateToAdviceDetail(int adviceId);

    void navigateToCategory(int item, int childCount);

    void navigateToSubCategory(int item, int childCount);

    void navigateToProductList(int categoryId);

    void navigateToCatalog();

    void navigateToProductCard(int productId);

    void navigateToOrder(int step);

    void navigateToCart();

    void navigateToMainScreen();

    void navigateToAdviceList();

}

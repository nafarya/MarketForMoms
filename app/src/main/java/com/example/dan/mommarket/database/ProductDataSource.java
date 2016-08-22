package com.example.dan.mommarket.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dan.mommarket.model.Product;
import com.example.dan.mommarket.model.ProductCategory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dan on 21.08.16.
 */

public class ProductDataSource {
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    private String[] allColumns = {SQLiteHelper.Product.PRODUCT_ID, SQLiteHelper.Product.PRODUCT_NAME,
        SQLiteHelper.Product.PRODUCT_CATEGORY};

    public ProductDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void close() {
        dbHelper.close();
    }

    public List<Product> getAllProducts() {
        database = dbHelper.getReadableDatabase();
        List<Product> productList = new ArrayList<>();
        Cursor productCursor = database.query(SQLiteHelper.Product.TABLE, allColumns, null,
                null, null, null, null);

        productCursor.moveToFirst();
        while(!productCursor.isAfterLast()) {
            Product product = new Product();
            productList.add(productCursorToProduct(productCursor));
            productCursor.moveToNext();
        }
        productCursor.close();
        return productList;
    }

    private Product productCursorToProduct(Cursor productCursor) {
        Product product = new Product();
        product.setProductId(productCursor.getInt(0));
        product.setName(productCursor.getString(1));

        ProductCategory productCategory = new ProductCategory(productCursor.getInt(2));
        product.setProductCategory(productCategory);
        return product;
    }
}

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
/*
    private String[] allColumns = {Contract.Product.PRODUCT_ID, Contract.Product.PRODUCT_NAME,
            Contract.Product.PRODUCT_CATEGORY_ID};
*/
    public ProductDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void close() {
        dbHelper.close();
    }

    public List<Product> getAllProducts() {
        database = dbHelper.getReadableDatabase();
        List<Product> productList = new ArrayList<>();
        Cursor productCursor = database.rawQuery("select "+
                " p."+Contract.Product.PRODUCT_ID+
                " ,p."+Contract.Product.PRODUCT_NAME+
                " ,avg(o."+Contract.Offer.OFFER_PRICE+") "+Contract.Offer.OFFER_PRICE+
                " ,p."+Contract.Product.PRODUCT_DESCRIPTION+
                " ,c."+Contract.ProductCategory.PRODUCT_CATEGORY_ID+" CATEGORY_ID"+
                " ,c."+Contract.ProductCategory.PRODUCT_CATEGORY_NAME+" CATEGORY_NAME"+
                " from "+Contract.Product.TABLE+" p" +
                " left join "+Contract.ProductCategory.TABLE+" c on c."+Contract.ProductCategory.PRODUCT_CATEGORY_ID+"= p."+Contract.Product.PRODUCT_CATEGORY_ID+" " +
                " left join "+Contract.Offer.TABLE+" o on o."+Contract.Offer.OFFER_PRODUCT_ID+"=p."+Contract.Product.PRODUCT_ID +
                " group by p."+Contract.Product.PRODUCT_ID + ";",null);
                //database.query(SQLiteHelper.Product.TABLE, allColumns, null,null, null, null, null);

        productCursor.moveToFirst();
        while(!productCursor.isAfterLast()) {
            //Product product = new Product();
            productList.add(productCursorToProduct(productCursor));
            productCursor.moveToNext();
        }
        productCursor.close();
        return productList;
    }

    private Product productCursorToProduct(Cursor productCursor) {
        Product product = new Product(productCursor.getInt(0),
                productCursor.getString(1),
                productCursor.getFloat(2),
                productCursor.getString(3)
                );
        /*
        product.setProductId(productCursor.getInt(0));
        product.setName(productCursor.getString(1));
        */
        ProductCategory productCategory = new ProductCategory(productCursor.getInt(4),productCursor.getString(5));
        product.setProductCategory(productCategory);
        return product;
    }
}

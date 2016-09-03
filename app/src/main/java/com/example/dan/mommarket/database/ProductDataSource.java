package com.example.dan.mommarket.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dan.mommarket.model.Feature;
import com.example.dan.mommarket.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dan on 21.08.16.
 */

public class ProductDataSource {
    private static SQLiteDatabase database;
    private static SQLiteHelper dbHelper;

    private static ProductDataSource instance;

    public static synchronized ProductDataSource getInstance() {
        if (instance == null) {
            instance = new ProductDataSource();
        }
        return instance;
    }

    public static void setDatabase(Context context){
        dbHelper = new SQLiteHelper(context);
        return ;
    }
    public static List<Product> getAllProducts() {
        database = dbHelper.getReadableDatabase();
        List<Product> productList = new ArrayList<>();
        Cursor productCursor = database.rawQuery("select " +
                " p." + Contract.ProductDB.PRODUCT_ID +
                " ,p." + Contract.ProductDB.PRODUCT_NAME +
                " ,avg(o." + Contract.OfferDB.OFFER_PRICE + ") " + Contract.OfferDB.OFFER_PRICE +
                " ,p." + Contract.ProductDB.PRODUCT_DESCRIPTION +
                " ,c." + Contract.ProductCategoryDB.PRODUCT_CATEGORY_ID + " CATEGORY_ID" +
                " ,c." + Contract.ProductCategoryDB.PRODUCT_CATEGORY_NAME + " CATEGORY_NAME" +
                " ,max(i." + Contract.ImageDB.URL + ") URL" +
                " from " + Contract.ProductDB.TABLE + " p" +
                " left join " + Contract.ProductCategoryDB.TABLE + " c on c." + Contract.ProductCategoryDB.PRODUCT_CATEGORY_ID + "= p." + Contract.ProductDB.PRODUCT_CATEGORY_ID + " " +
                " left join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.OFFER_PRODUCT_ID + "=p." + Contract.ProductDB.PRODUCT_ID +
                " left join " + Contract.ImageDB.TABLE + " i on i." + Contract.ImageDB.ID + "=p." + Contract.ProductDB.PRODUCT_ID +
                " group by p." + Contract.ProductDB.PRODUCT_ID + ";", null);

        productCursor.moveToFirst();
        while (!productCursor.isAfterLast()) {
            productList.add(productCursorToProduct(productCursor));
            productCursor.moveToNext();
        }
        productCursor.close();
        return productList;
    }

    public static List<Product> getListProducts(int listId) {
        database = dbHelper.getReadableDatabase();
        List<Product> productList = new ArrayList<>();
        Cursor productCursor = database.rawQuery("select " +
                " p." + Contract.ProductDB.PRODUCT_ID +
                " ,p." + Contract.ProductDB.PRODUCT_NAME +
                " ,o." + Contract.OfferDB.OFFER_PRICE +
                " ,p." + Contract.ProductDB.PRODUCT_DESCRIPTION +
                " ,c." + Contract.ProductCategoryDB.PRODUCT_CATEGORY_ID + " CATEGORY_ID" +
                " ,c." + Contract.ProductCategoryDB.PRODUCT_CATEGORY_NAME + " CATEGORY_NAME" +
                " ,max(i." + Contract.ImageDB.URL + ") URL" +
                " from " + Contract.ProductDB.TABLE + " p" +
                " left join " + Contract.ProductCategoryDB.TABLE + " c on c." + Contract.ProductCategoryDB.PRODUCT_CATEGORY_ID + "= p." + Contract.ProductDB.PRODUCT_CATEGORY_ID + " " +
                " left join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.OFFER_PRODUCT_ID + "=p." + Contract.ProductDB.PRODUCT_ID +
                " left join " + Contract.ListOfferDB.TABLE + " lo on lo." + Contract.ListOfferDB.LIST_OFFER_OFFER_ID + "=o." + Contract.OfferDB.OFFER_ID +
                " left join " + Contract.ImageDB.TABLE + " i on i." + Contract.ImageDB.ID + "=p." + Contract.ProductDB.PRODUCT_ID +
                " Where lo." + Contract.ListOfferDB.LIST_OFFER_LIST_ID + "=?"+
                " group by p." + Contract.ProductDB.PRODUCT_ID + ";", new String[]{Integer.toString(listId)});
        productCursor.moveToFirst();
        while (!productCursor.isAfterLast()) {
            productList.add(productCursorToProduct(productCursor));
            productCursor.moveToNext();
        }
        productCursor.close();
        return productList;
    }

    public static List<Product> getListProductsByCategoryId(int categoryId) {
        database = dbHelper.getReadableDatabase();
        List<Product> productList = new ArrayList<>();
        Cursor productCursor = database.rawQuery("select " +
                " p." + Contract.ProductDB.PRODUCT_ID +
                " ,p." + Contract.ProductDB.PRODUCT_NAME +
                " ,avg(o." + Contract.OfferDB.OFFER_PRICE +")"+
                " ,p." + Contract.ProductDB.PRODUCT_DESCRIPTION +
                " ,c." + Contract.ProductCategoryDB.PRODUCT_CATEGORY_ID + " CATEGORY_ID" +
                " ,c." + Contract.ProductCategoryDB.PRODUCT_CATEGORY_NAME + " CATEGORY_NAME" +
                " ,max(i." + Contract.ImageDB.URL + ") URL" +
                " from " + Contract.ProductDB.TABLE + " p" +
                " left join " + Contract.ProductCategoryDB.TABLE + " c on c." + Contract.ProductCategoryDB.PRODUCT_CATEGORY_ID + "= p." + Contract.ProductDB.PRODUCT_CATEGORY_ID + " " +
                " left join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.OFFER_PRODUCT_ID + "=p." + Contract.ProductDB.PRODUCT_ID +
                " left join " + Contract.ImageDB.TABLE + " i on i." + Contract.ImageDB.ID + "=p." + Contract.ProductDB.PRODUCT_ID +
                " Where p." + Contract.ProductDB.PRODUCT_CATEGORY_ID + "=?"+
                " group by p." + Contract.ProductDB.PRODUCT_ID + ";", new String[]{Integer.toString(categoryId)});
        productCursor.moveToFirst();
        while (!productCursor.isAfterLast()) {
            productList.add(productCursorToProduct(productCursor));
            productCursor.moveToNext();
        }
        productCursor.close();
        return productList;
    }

    public static List<Product> getProductsByFeatures(List<Feature> features) {
        database = dbHelper.getReadableDatabase();
        String[] whereClauseArray = new String[features.size() * 2];
        String whereClause = "";
        int i = 0;
        for (Feature x : features) {
            if (whereClause != "") whereClause += " OR ";
            whereClause += "pf." + Contract.ProductFeatureDB.PRODUCT_FEATURE_FEATURE_ID + "=? AND pf." + Contract.ProductFeatureDB.PRODUCT_FEATURE_VALUE + "=?";
            whereClauseArray[i++] = Integer.toString(x.getId());
            whereClauseArray[i++] = x.getValue();
        }
        List<Product> productList = new ArrayList<>();
        Cursor productCursor = database.rawQuery("select " +
                " p." + Contract.ProductDB.PRODUCT_ID +
                " ,p." + Contract.ProductDB.PRODUCT_NAME +
                " ,o." + Contract.OfferDB.OFFER_PRICE +
                " ,p." + Contract.ProductDB.PRODUCT_DESCRIPTION +
                " ,c." + Contract.ProductCategoryDB.PRODUCT_CATEGORY_ID + " CATEGORY_ID" +
                " ,c." + Contract.ProductCategoryDB.PRODUCT_CATEGORY_NAME + " CATEGORY_NAME" +
                " ,max(i." + Contract.ImageDB.URL + ") URL" +
                " from " + Contract.ProductDB.TABLE + " p" +
                " left join " + Contract.ProductCategoryDB.TABLE + " c on c." + Contract.ProductCategoryDB.PRODUCT_CATEGORY_ID + "= p." + Contract.ProductDB.PRODUCT_CATEGORY_ID + " " +
                " left join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.OFFER_PRODUCT_ID + "=p." + Contract.ProductDB.PRODUCT_ID +
                " left join " + Contract.ListOfferDB.TABLE + " lo on lo." + Contract.ListOfferDB.LIST_OFFER_OFFER_ID + "=o." + Contract.OfferDB.OFFER_ID +
                " left join " + Contract.ProductFeatureDB.TABLE + " pf on pf." + Contract.ProductFeatureDB.PRODUCT_FEATURE_PRODUCT_ID + "=p." + Contract.ProductDB.PRODUCT_ID +
                " left join " + Contract.ImageDB.TABLE + " i on i." + Contract.ImageDB.ID + "=i." + Contract.ProductDB.PRODUCT_ID +
                " Where " + whereClause +
                " group by p." + Contract.ProductDB.PRODUCT_ID + " having count(p." + Contract.ProductDB.PRODUCT_ID + ")=" + features.size() + ";", whereClauseArray);
        productCursor.moveToFirst();
        while (!productCursor.isAfterLast()) {
            productList.add(productCursorToProduct(productCursor));
            productCursor.moveToNext();
        }
        productCursor.close();
        return productList;
    }

    private static  Product productCursorToProduct(Cursor productCursor) {
        Product product = new Product(productCursor.getInt(0),
                productCursor.getString(1),
                productCursor.getFloat(2),
                productCursor.getString(3),
                productCursor.getInt(4),
                productCursor.getString(5),
                productCursor.getString(6)
        );
        return product;
    }
}

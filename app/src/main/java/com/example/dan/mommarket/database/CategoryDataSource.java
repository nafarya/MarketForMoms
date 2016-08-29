package com.example.dan.mommarket.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dan.mommarket.model.Product;
import com.example.dan.mommarket.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dan on 21.08.16.
 */

public class CategoryDataSource {
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
    /*
    public CategoryDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void close() {
        dbHelper.close();
    }
*/
    public static List<ProductCategory> getAllCategories() {
        database = dbHelper.getReadableDatabase();
        List<ProductCategory> categorytList = new ArrayList<>();
        Cursor categoryCursor = database.rawQuery("select " +
                        " c." + Contract.ProductCategory.PRODUCT_CATEGORY_ID +
                        " ,c." + Contract.ProductCategory.PRODUCT_CATEGORY_NAME +
                        " ,pc." + Contract.ProductCategory.PRODUCT_CATEGORY_ID +
                        " ,pc." + Contract.ProductCategory.PRODUCT_CATEGORY_NAME +
                        " from " + Contract.ProductCategory.TABLE + " c" +
                        " left join " + Contract.ProductCategory.TABLE + " pc on pc." + Contract.ProductCategory.PRODUCT_CATEGORY_ID + "= c." + Contract.ProductCategory.PRODUCT_CATEGORY_PARENT_CATEGORY_ID + " " + ";"
                , null);

        categoryCursor.moveToFirst();
        while (!categoryCursor.isAfterLast()) {
            categorytList.add(new ProductCategory(
                    categoryCursor.getInt(0),
                    categoryCursor.getString(1),
                    categoryCursor.getInt(2)));
            categoryCursor.moveToNext();
        }
        categoryCursor.close();

        return categorytList;
    }

    public static List<ProductCategory> getChildCategories(int parentId) {
        database = dbHelper.getReadableDatabase();
        List<ProductCategory> categoryList = new ArrayList<>();
        Cursor categoryCursor = database.rawQuery("select " +
                        " c." + Contract.ProductCategory.PRODUCT_CATEGORY_ID +
                        " ,c." + Contract.ProductCategory.PRODUCT_CATEGORY_NAME +
                        " ,pc." + Contract.ProductCategory.PRODUCT_CATEGORY_ID +
                        " ,pc." + Contract.ProductCategory.PRODUCT_CATEGORY_NAME +
                        " from " + Contract.ProductCategory.TABLE + " c" +
                        " left join " + Contract.ProductCategory.TABLE + " pc on pc." + Contract.ProductCategory.PRODUCT_CATEGORY_ID + "= c." + Contract.ProductCategory.PRODUCT_CATEGORY_PARENT_CATEGORY_ID +
                        " where c." + Contract.ProductCategory.PRODUCT_CATEGORY_PARENT_CATEGORY_ID + " =?;"
                , new String[]{Integer.toString(parentId)});

        categoryCursor.moveToFirst();
        while (!categoryCursor.isAfterLast()) {
            categoryList.add(new ProductCategory(
                    categoryCursor.getInt(0),
                    categoryCursor.getString(1),
                    categoryCursor.getInt(2)));
            categoryCursor.moveToNext();
        }
        categoryCursor.close();
        return categoryList;
    }
}

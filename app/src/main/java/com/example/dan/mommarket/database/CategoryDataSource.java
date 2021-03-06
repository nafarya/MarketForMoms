package com.example.dan.mommarket.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dan.mommarket.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dan on 21.08.16.
 */

public class CategoryDataSource {
    private static SQLiteDatabase database;
    private static SQLiteHelper dbHelper;

    private static CategoryDataSource instance;

    public static synchronized CategoryDataSource getInstance() {
        if (instance == null) {
            instance = new CategoryDataSource();
        }
        return instance;
    }

    public static void setDatabase(Context context){
        dbHelper = new SQLiteHelper(context);
        return ;
    }

    public static List<ProductCategory> getAllCategories() {
        database = dbHelper.getReadableDatabase();
        List<ProductCategory> categorytList = new ArrayList<>();
        Cursor categoryCursor = database.rawQuery("select " +
                        " c." + Contract.ProductCategoryDB.ID +
                        " ,c." + Contract.ProductCategoryDB.NAME +
                        " ,c." + Contract.ProductCategoryDB.DESCRIPTION +
                        " ,pc." + Contract.ProductCategoryDB.ID +
                        " ,i." + Contract.ImageDB.URL +
                        " ,pc." + Contract.ProductCategoryDB.NAME +
                        " from " + Contract.ProductCategoryDB.TABLE + " c" +
                        " left join " + Contract.ImageDB.TABLE + " i on i." + Contract.ImageDB.ID + "= c." +Contract.ProductCategoryDB.IMAGE_ID +
                        " left join " + Contract.ProductCategoryDB.TABLE + " pc on pc." + Contract.ProductCategoryDB.ID + "= c." + Contract.ProductCategoryDB.PARENT_CATEGORY_ID + " " + ";"
                , null);

        categoryCursor.moveToFirst();
        while (!categoryCursor.isAfterLast()) {
            categorytList.add(new ProductCategory(
                    categoryCursor.getInt(0),
                    categoryCursor.getString(1),
                    categoryCursor.getString(2),
                    categoryCursor.getInt(3),
                    categoryCursor.getString(4)));
            categoryCursor.moveToNext();
        }
        categoryCursor.close();

        return categorytList;
    }

    public static List<ProductCategory> getChildCategories(int parentId) {
        database = dbHelper.getReadableDatabase();
        List<ProductCategory> categoryList = new ArrayList<>();
        Cursor categoryCursor = database.rawQuery("select " +
                        " c." + Contract.ProductCategoryDB.ID +
                        " ,c." + Contract.ProductCategoryDB.NAME +
                        " ,c." + Contract.ProductCategoryDB.DESCRIPTION +
                        " ,pc." + Contract.ProductCategoryDB.ID +
                        " ,i." + Contract.ImageDB.URL +
                        " ,ifnull(a.cn,0) cn"  +
                        " ,pc." + Contract.ProductCategoryDB.NAME +
                        " from " + Contract.ProductCategoryDB.TABLE + " c" +
                        " left join " + Contract.ImageDB.TABLE + " i on i." + Contract.ImageDB.ID + "= c." +Contract.ProductCategoryDB.IMAGE_ID +
                        " left join " + Contract.ProductCategoryDB.TABLE + " pc on pc." + Contract.ProductCategoryDB.ID + "= c." + Contract.ProductCategoryDB.PARENT_CATEGORY_ID +
                        " left join ( select cc." + Contract.ProductCategoryDB.PARENT_CATEGORY_ID + " id,"+
                                            " count ( cc." + Contract.ProductCategoryDB.PARENT_CATEGORY_ID +") cn" +
                                            " from " + Contract.ProductCategoryDB.TABLE + " cc"+
                                            " group by cc."+Contract.ProductCategoryDB.PARENT_CATEGORY_ID +") a"+
                                            " on  c." + Contract.ProductCategoryDB.ID + "= a.id" +
                " where c." + Contract.ProductCategoryDB.PARENT_CATEGORY_ID + " =?;"
                , new String[]{Integer.toString(parentId)});

        categoryCursor.moveToFirst();
        while (!categoryCursor.isAfterLast()) {
            categoryList.add(new ProductCategory(
                    categoryCursor.getInt(0),
                    categoryCursor.getString(1),
                    categoryCursor.getString(2),
                    categoryCursor.getInt(3),
                    categoryCursor.getString(4),
                    categoryCursor.getInt(5)));
            categoryCursor.moveToNext();
        }
        categoryCursor.close();
        return categoryList;
    }
}

package com.example.dan.mommarket.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dan on 19.08.16.
 */

public class SQLiteHelper extends SQLiteOpenHelper implements Contract{

    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "mommarket.db";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CheckList.CHECKLIST_TABLE_CREATE);
        sqLiteDatabase.execSQL(Child.CHILD_TABLE_CREATE);
        sqLiteDatabase.execSQL(Features.FEATURES_TABLE_CREATE);
        sqLiteDatabase.execSQL(ItemReference.ITEM_REFERENCE_TABLE_CREATE);
        sqLiteDatabase.execSQL(Offer.OFFER_TABLE_CREATE);
        sqLiteDatabase.execSQL(Product.PRODUCT_TABLE_CREATE);
        sqLiteDatabase.execSQL(ProductCategory.PRODUCT_CATEGORY_TABLE_CREATE);
        sqLiteDatabase.execSQL(Shop.SHOP_TABLE_CREATE);
        sqLiteDatabase.execSQL(User.USER_TABLE_CREATE);
        sqLiteDatabase.execSQL(Image.IMAGE_TABLE_CREATE);
        sqLiteDatabase.execSQL(ListOffer.LIST_OFFER_TABLE_CREATE);
        sqLiteDatabase.execSQL(ProductFeature.PRODUCT_FEATURE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CheckList.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Child.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Features.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ItemReference.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Offer.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Product.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ProductCategory.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Shop.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + User.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Image.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ListOffer.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ProductFeature.TABLE);
        onCreate(sqLiteDatabase);
    }
}
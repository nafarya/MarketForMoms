package com.example.dan.mommarket.database;

import java.util.ListResourceBundle;

/**
 * Created by dan on 19.08.16.
 */

public interface Contract {


    final class Child {
        public static final String TABLE = "CHILD";
        public static final String CHILD_ID = "_ID";
        public static final String CHILD_NAME = "NAME";
        public static final String CHILD_AGE = "AGE";
        public static final String CHILD_WEIGHT = "WEIGHT";
        public static final String CHILD_SEX = "SEX";
        public static final String CHILD_HEIGHT = "HEIGHT";
        public static final String CHILD_LAST_UPDATE = "LAST_UPDATE";

        public static final String CHILD_TABLE_CREATE = "create table " + TABLE + " (" +
                CHILD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CHILD_NAME + " TEXT NOT NULL, " +
                CHILD_AGE + " INTEGER, " +
                CHILD_WEIGHT + " REAL, " +
                CHILD_SEX + " TEXT, " +
                CHILD_HEIGHT + " REAL, " +
                CHILD_LAST_UPDATE + " TEXT);";
    }

    final class CheckList {
        public static final String TABLE = "CHECK_LIST";
        public static final String CHECK_LIST_ID = "_ID";
        public static final String CHECK_LIST_NAME = "NAME";
        public static final String CHECK_LIST_TYPE = "TYPE";
        public static final String CHECK_LIST_START_DATE = "START_DATE";

        public static final String CHECKLIST_TABLE_CREATE = "create table " + TABLE + " (" +
                CHECK_LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CHECK_LIST_NAME + " TEXT NOT NULL, " +
                CHECK_LIST_TYPE + " TEXT, " +
                CHECK_LIST_START_DATE + " TEXT );";
    }

    final class Feature {
        public static final String TABLE = "FEATURE";
        public static final String FEATURE_ID = "_ID";
        public static final String FEATURE_NAME = "NAME";
        public static final String FEATURE_TYPE = "TYPE";
        public static final String FEATURE_MEASURE = "MEASURE";
        public static final String FEATURE_CATEGORY_ID = "CATEGORY_ID";

        public static final String FEATURE_TABLE_CREATE = "create table " + TABLE + " (" +
                FEATURE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FEATURE_NAME + " TEXT NOT NULL, " +
                FEATURE_MEASURE + " TEXT NOT NULL, " +
                FEATURE_CATEGORY_ID + " INTEGER NOT NULL, " +
                FEATURE_TYPE + " TEXT );";

    }

    final class ItemReference {
        public static final String TABLE = "ITEM_REFERENCE";
        public static final String ITEM_REFERENCE_ID = "_ID";
        public static final String ITEM_REFERENCE_RATE = "RATE";
        public static final String ITEM_REFERENCE_DESCRIPTION = "DESCRIPTION";

        public static final String ITEM_REFERENCE_TABLE_CREATE = "create table " + TABLE + " (" +
                ITEM_REFERENCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ITEM_REFERENCE_RATE + " REAL, " +
                ITEM_REFERENCE_DESCRIPTION + " TEXT NOT NULL );";
    }

    final class Offer {
        public static final String TABLE = "OFFER";
        public static final String OFFER_ID = "_ID";
        public static final String OFFER_PRICE = "PRICE";
        public static final String OFFER_PRODUCT_ID = "PRODUCT_ID";
        public static final String OFFER_SHOP_ID = "SHOP_ID";
        public static final String OFFER_ACTIVE_FLG = "ACTIVE_FLG";

        public static final String OFFER_TABLE_CREATE = "create table " + TABLE + " (" +
                OFFER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                OFFER_PRICE + " REAL, " +
                OFFER_PRODUCT_ID + " INTEGER NOT NULL, " +
                OFFER_SHOP_ID + " INTEGER NOT NULL, " +
                OFFER_ACTIVE_FLG + " INTEGER );";
    }

    final class Product {
        public static final String TABLE = "PRODUCT";
        public static final String PRODUCT_ID = "_ID";
        public static final String PRODUCT_NAME = "NAME";
        public static final String PRODUCT_DESCRIPTION = "DESCRIPTION";
        public static final String PRODUCT_CATEGORY_ID = "CATEGORY_ID";

        public static final String PRODUCT_TABLE_CREATE = "create table " + TABLE + " (" +
                PRODUCT_ID + " INTEGER, " +
                PRODUCT_NAME + " TEXT NOT NULL, " +
                PRODUCT_DESCRIPTION + " TEXT, " +
                PRODUCT_CATEGORY_ID + " INTEGER );";

    }

    final class ProductCategory {
        public static final String TABLE = "PRODUCT_CATEGORY";
        public static final String PRODUCT_CATEGORY_ID = "_ID";
        public static final String PRODUCT_CATEGORY_NAME = "NAME";
        public static final String PRODUCT_CATEGORY_PARENT_CATEGORY_ID = "PARENT_CATEGORY_ID";

        public static final String PRODUCT_CATEGORY_TABLE_CREATE = "create table " + TABLE + " (" +
                PRODUCT_CATEGORY_ID + " INTEGER, " +
                PRODUCT_CATEGORY_NAME + " TEXT NOT NULL, " +
                PRODUCT_CATEGORY_PARENT_CATEGORY_ID + " INTEGER );";
    }

    final class Shop {
        public static final String TABLE = "SHOP";
        public static final String SHOP_ID = "_ID";
        public static final String SHOP_NAME = "NAME";
        public static final String SHOP_RATE = "RATE";
        public static final String SHOP_DELIVERY_TIME = "DELIVERY_TIME";
        public static final String SHOP_DELIVERY_PRICE = "DELIVERY_PRICE";
        public static final String SHOP_REFERENCE_COUNT = "REFERENCE_COUNT";

        public static final String SHOP_TABLE_CREATE = "create table " + TABLE + " (" +
                SHOP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SHOP_NAME + " TEXT NOT NULL, " +
                SHOP_DELIVERY_TIME + " TEXT, " +
                SHOP_DELIVERY_PRICE + " INTEGER, " +
                SHOP_REFERENCE_COUNT + " INTEGER, " +
                SHOP_RATE + " REAL );";
    }

    final class User {
        public static final String TABLE = "USER";
        public static final String USER_ID = "_ID";
        public static final String USER_NAME = "NAME";
        public static final String USER_LOGIN = "LOGIN";

        public static final String USER_TABLE_CREATE = "create table " + TABLE + " (" +
                USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_NAME + " TEXT NOT NULL, " +
                USER_LOGIN + " TEXT );";
    }

    final class Image {
        public static final String TABLE = "IMAGE";
        public static final String IMAGE_ID = "_ID";
        public static final String IMAGE_PRODUCT_ID = "PRODUCT_ID";
        public static final String IMAGE_URL = "URL";

        public static final String IMAGE_TABLE_CREATE = "create table " + TABLE + " (" +
                IMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                IMAGE_PRODUCT_ID + " INTEGER NOT NULL, " +
                IMAGE_URL + " TEXT );";
    }

    final class ListOffer {
        public static final String TABLE = "LIST_OFFER";
        public static final String LIST_OFFER_ID = "_ID";
        public static final String LIST_OFFER_LIST_ID = "LIST_ID";
        public static final String LIST_OFFER_OFFER_ID = "OFFER_ID";
        public static final String LIST_OFFER_PRICE= "PRICE";

        public static final String LIST_OFFER_TABLE_CREATE = "create table " + TABLE + " (" +
                LIST_OFFER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LIST_OFFER_LIST_ID + " INTEGER NOT NULL, " +
                LIST_OFFER_OFFER_ID + " INTEGER NOT NULL, " +
                LIST_OFFER_PRICE + " REAL );";
    }

    final class ProductFeature {
        public static final String TABLE = "PRODUCT_FEATURE";
        public static final String PRODUCT_FEATURE_ID = "_ID";
        public static final String PRODUCT_FEATURE_PRODUCT_ID = "PRODUCT_ID";
        public static final String PRODUCT_FEATURE_FEATURE_ID = "FEATURE_ID";
        public static final String PRODUCT_FEATURE_VALUE= "VALUE";

        public static final String PRODUCT_FEATURE_TABLE_CREATE = "create table " + TABLE + " (" +
                PRODUCT_FEATURE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PRODUCT_FEATURE_FEATURE_ID + " INTEGER NOT NULL, " +
                PRODUCT_FEATURE_PRODUCT_ID + " INTEGER NOT NULL, " +
                PRODUCT_FEATURE_VALUE + " TEXT );";

    }

}

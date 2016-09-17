package com.example.dan.mommarket.database;

/**
 * Created by dan on 19.08.16.
 */

public interface Contract {


    final class ChildDB {
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

    final class UserDB {
        public static final String TABLE = "USER";
        public static final String USER_ID = "_ID";
        public static final String USER_NAME = "NAME";
        public static final String USER_LOGIN = "LOGIN";

        public static final String USER_TABLE_CREATE = "create table " + TABLE + " (" +
                USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_NAME + " TEXT NOT NULL, " +
                USER_LOGIN + " TEXT );";
    }

    final class CheckListDB {
        public static final String TABLE = "CHECK_LIST";
        public static final String ID = "_ID";
        public static final String NAME = "NAME";
        public static final String TYPE = "TYPE";
        public static final String CITY = "CITY";
        public static final String STREET = "STREET";
        public static final String APPARTMERNT = "APPARTMERNT";
        public static final String COMMENT = "COMMENT";
        public static final String FIRST_NAME = "FIRST_NAME";
        public static final String LAST_NAME = "LAST_NAME";
        public static final String PHONE = "PHONE";
        public static final String EMAIL = "EMAIL";
        public static final String CASH = "CASH";
        public static final String START_DATE = "START_DATE";

        public static final String TABLE_CREATE = "create table " + TABLE + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT NOT NULL, " +
                TYPE + " TEXT, " +
                CITY + " TEXT, " +
                STREET + " TEXT, " +
                APPARTMERNT + " TEXT, " +
                COMMENT + " TEXT, " +
                FIRST_NAME + " TEXT, " +
                LAST_NAME + " TEXT, " +
                EMAIL + " TEXT, " +
                PHONE + " TEXT, " +
                CASH + " INTEGER, " +
                START_DATE + " TEXT );";
    }

    final class FeatureDB {
        public static final String TABLE = "FEATURE";
        public static final String ID = "_ID";
        public static final String NAME = "NAME";
        public static final String TYPE = "TYPE";
        public static final String MEASURE = "MEASURE";
        public static final String CATEGORY_ID = "CATEGORY_ID";

        public static final String TABLE_CREATE = "create table " + TABLE + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT NOT NULL, " +
                MEASURE + " TEXT NOT NULL, " +
                CATEGORY_ID + " INTEGER NOT NULL, " +
                TYPE + " TEXT );";

    }

    final class ItemReferenceDB {
        public static final String TABLE = "ITEM_REFERENCE";
        public static final String ID = "_ID";
        public static final String RATE = "RATE";
        public static final String DESCRIPTION = "DESCRIPTION";

        public static final String TABLE_CREATE = "create table " + TABLE + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RATE + " REAL, " +
                DESCRIPTION + " TEXT NOT NULL );";
    }

    final class OfferDB {
        public static final String TABLE = "OFFER";
        public static final String ID = "_ID";
        public static final String PRICE = "PRICE";
        public static final String PRODUCT_ID = "PRODUCT_ID";
        public static final String SHOP_ID = "SHOP_ID";
        public static final String ACTIVE_FLG = "ACTIVE_FLG";

        public static final String TABLE_CREATE = "create table " + TABLE + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PRICE + " REAL, " +
                PRODUCT_ID + " INTEGER NOT NULL, " +
                SHOP_ID + " INTEGER NOT NULL, " +
                ACTIVE_FLG + " INTEGER );";
    }

    final class ProductDB {
        public static final String TABLE = "PRODUCT";
        public static final String ID = "_ID";
        public static final String NAME = "NAME";
        public static final String DESCRIPTION = "DESCRIPTION";
        public static final String CATEGORY_ID = "CATEGORY_ID";

        public static final String TABLE_CREATE = "create table " + TABLE + " (" +
                ID + " INTEGER, " +
                NAME + " TEXT NOT NULL, " +
                DESCRIPTION + " TEXT, " +
                CATEGORY_ID + " INTEGER );";
    }

    final class ProductCategoryDB {
        public static final String TABLE = "PRODUCT_CATEGORY";
        public static final String ID = "_ID";
        public static final String NAME = "NAME";
        public static final String IMAGE_ID = "IMAGE_ID";
        public static final String DESCRIPTION = "DESCRIPTION";
        public static final String FILTER_FEATURE_ID = "FILTER_FEATURE_ID";
        public static final String CARD_FEATURE_ID = "CARD_FEATURE_ID";
        public static final String TAB_FEATURE_ID = "TAB_FEATURE_ID";
        public static final String PARENT_CATEGORY_ID = "PARENT_CATEGORY_ID";

        public static final String TABLE_CREATE = "create table " + TABLE + " (" +
                ID + " INTEGER, " +
                NAME + " TEXT NOT NULL, " +
                DESCRIPTION + " TEXT, " +
                IMAGE_ID + " INTEGER, " +
                CARD_FEATURE_ID + " INTEGER, " +
                FILTER_FEATURE_ID + " INTEGER, " +
                TAB_FEATURE_ID + " INTEGER, " +
                PARENT_CATEGORY_ID + " INTEGER );";
    }

    final class ShopDB {
        public static final String TABLE = "SHOP";
        public static final String ID = "_ID";
        public static final String NAME = "NAME";
        public static final String RATE = "RATE";
        public static final String DELIVERY_TIME = "DELIVERY_TIME";
        public static final String DELIVERY_TIME_FLOAT = "DELIVERY_TIME_FLOAT";
        public static final String DELIVERY_PRICE = "DELIVERY_PRICE";
        public static final String REFERENCE_COUNT = "REFERENCE_COUNT";

        public static final String TABLE_CREATE = "create table " + TABLE + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT NOT NULL, " +
                DELIVERY_TIME + " TEXT, " +
                DELIVERY_TIME_FLOAT + " REAL, " +
                DELIVERY_PRICE + " INTEGER, " +
                REFERENCE_COUNT + " INTEGER, " +
                RATE + " REAL );";
    }

    final class ImageDB {
        public static final String TABLE = "IMAGE";
        public static final String ID = "_ID";
        public static final String ITEM_ID = "ITEM_ID";
        public static final String URL = "URL";

        public static final String TABLE_CREATE = "create table " + TABLE + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ITEM_ID + " INTEGER NOT NULL, " +
                URL + " TEXT );";
    }

    final class OfferItemDB {
        public static final String TABLE = "OFFER_ITEM";
        public static final String ID = "_ID";
        public static final String LIST_ID = "LIST_ID";
        public static final String OFFER_ID = "OFFER_ID";
        public static final String PRODUCT_ID = "PRODUCT_ID";
        public static final String PRICE = "PRICE";
        public static final String COUNT = "ITEM_COUNT";

        public static final String TABLE_CREATE = "create table " + TABLE + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LIST_ID + " INTEGER NOT NULL, " +
                OFFER_ID + " INTEGER , " +
                PRODUCT_ID + " INTEGER , " +
                COUNT + " INTEGER, " +
                PRICE + " REAL );";
    }

    final class ProductFeatureDB {
        public static final String TABLE = "PRODUCT_FEATURE";
        public static final String ID = "_ID";
        public static final String PRODUCT_ID = "PRODUCT_ID";
        public static final String FEATURE_ID = "FEATURE_ID";
        public static final String VALUE = "VALUE";

        public static final String TABLE_CREATE = "create table " + TABLE + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FEATURE_ID + " INTEGER NOT NULL, " +
                PRODUCT_ID + " INTEGER NOT NULL, " +
                VALUE + " TEXT );";

    }

    final class AdviceDB {
        public static final String TABLE = "ADVICE";
        public static final String ID = "_ID";
        public static final String NAME = "NAME";
        public static final String SHORT_DESC = "SHORT_DESC";
        public static final String DESCRIPTION = "DESCRIPTION";
        public static final String IMAGE = "IMAGE";
        public static final String IMAGE_0 = "IMAGE_0";
        public static final String AUTHOR_NAME = "AUTHOR_NAME";
        public static final String AUTHOR_TEXT = "AUTHOR_TEXT";
        public static final String AUTHOR_IMAGE = "AUTHOR_IMAGE";
        public static final String TEXT_0 = "TEXT_0";
        public static final String HEADER_1 = "HEADER_1";
        public static final String TEXT_1 = "TEXT_1";
        public static final String IMAGE_1 = "IMAGE_1";
        public static final String HEADER_2 = "HEADER_2";
        public static final String TEXT_2 = "TEXT_2";
        public static final String CATEGORY_1 = "CATEGORY_1";
        public static final String CATEGORY_2 = "CATEGORY_2";
        public static final String CATEGORY_3 = "CATEGORY_3";
        public static final String CATEGORY_1_DESC = "CATEGORY_1_DESC";
        public static final String CATEGORY_2_DESC = "CATEGORY_2_DESC";
        public static final String CATEGORY_3_DESC = "CATEGORY_3_DESC";


        public static final String TABLE_CREATE = "create table " + TABLE + " (" +
                ID + " INTEGER, " +
                NAME + " TEXT NOT NULL, " +
                SHORT_DESC + " TEXT, " +
                DESCRIPTION + " TEXT, " +
                IMAGE + " TEXT, " +
                IMAGE_0 + " TEXT, " +
                AUTHOR_NAME + " TEXT, " +
                AUTHOR_TEXT + " TEXT, " +
                AUTHOR_IMAGE + " TEXT, " +
                TEXT_0 + " TEXT, " +
                HEADER_1 + " TEXT, " +
                TEXT_1 + " TEXT, " +
                IMAGE_1 + " TEXT, " +
                HEADER_2 + " TEXT, " +
                TEXT_2 + " TEXT, " +
                CATEGORY_1 + " INTEGER, " +
                CATEGORY_2 + " INTEGER, " +
                CATEGORY_3 + " INTEGER, " +
                CATEGORY_1_DESC + " TEXT, " +
                CATEGORY_2_DESC + " TEXT, " +
                CATEGORY_3_DESC + " TEXT " + ");";
    }
}

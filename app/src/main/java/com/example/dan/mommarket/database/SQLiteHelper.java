package com.example.dan.mommarket.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by dan on 19.08.16.
 */

public class SQLiteHelper extends SQLiteOpenHelper implements Contract {

    static final String DATABASE_NAME = "mommarket.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    /**
     * This reads a file from the given Resource-Id and calls every line of it as a SQL-Statement
     *
     * @param context
     * @param resourceId e.g. R.raw.food_db
     * @return
     */
    public void insertFakeData(SQLiteDatabase sqLiteDatabase, Context context, int resourceId) {

        try {
            // Open the resource
            InputStream insertsStream = context.getResources().openRawResource(resourceId);
            BufferedReader insertReader = new BufferedReader(new InputStreamReader(insertsStream));

            String insertStmt = "";
            while (insertReader.ready()) {
                insertStmt += insertReader.readLine();
            }
            sqLiteDatabase.execSQL(insertStmt);
            insertReader.close();
        }catch (IOException e){}
        return;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CheckListDB.TABLE_CREATE);
        sqLiteDatabase.execSQL(ChildDB.CHILD_TABLE_CREATE);
        sqLiteDatabase.execSQL(FeatureDB.TABLE_CREATE);
        sqLiteDatabase.execSQL(ItemReferenceDB.TABLE_CREATE);
        sqLiteDatabase.execSQL(OfferDB.TABLE_CREATE);
        sqLiteDatabase.execSQL(ProductDB.TABLE_CREATE);
        sqLiteDatabase.execSQL(ProductCategoryDB.TABLE_CREATE);
        sqLiteDatabase.execSQL(ShopDB.TABLE_CREATE);
        sqLiteDatabase.execSQL(UserDB.USER_TABLE_CREATE);
        sqLiteDatabase.execSQL(ImageDB.TABLE_CREATE);
        sqLiteDatabase.execSQL(ListOfferDB.TABLE_CREATE);
        sqLiteDatabase.execSQL(ProductFeatureDB.TABLE_CREATE);
        sqLiteDatabase.execSQL(AdviceDB.TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CheckListDB.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ChildDB.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FeatureDB.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ItemReferenceDB.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + OfferDB.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ProductDB.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ProductCategoryDB.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ShopDB.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + UserDB.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ImageDB.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ListOfferDB.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ProductFeatureDB.TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AdviceDB.TABLE);
        onCreate(sqLiteDatabase);
    }
}
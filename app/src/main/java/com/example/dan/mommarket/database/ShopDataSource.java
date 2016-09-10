package com.example.dan.mommarket.database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dan.mommarket.model.Cart;
import com.example.dan.mommarket.model.Shop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dan on 21.08.16.
 */

public class ShopDataSource {
    private static SQLiteDatabase database;
    private static SQLiteHelper dbHelper;

    private static ShopDataSource instance;

    public static synchronized ShopDataSource getInstance() {
        if (instance == null) {
            instance = new ShopDataSource();
        }
        return instance;
    }

    public static void setDatabase(Context context) {
        dbHelper = new SQLiteHelper(context);
        return;
    }

    public static List<Shop> getShopCartList() {
        database = dbHelper.getReadableDatabase();
        List<Shop> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select " +
                        " cl." + Contract.CheckListDB.ID +
                        " ,cl." + Contract.CheckListDB.NAME +
                        " ,count( distinct o." + Contract.OfferDB.SHOP_ID + " )" +
                        " ,sum(  o." + Contract.OfferDB.PRICE + ")" +
                        " from " + Contract.CheckListDB.TABLE + " cl" +
                        " left join " + Contract.ListOfferDB.TABLE + " lo on lo." + Contract.ListOfferDB.LIST_ID + " = cl." + Contract.CheckListDB.ID +
                        " left join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.ID + " = lo." + Contract.ListOfferDB.OFFER_ID +
                        //  " left join " + Contract.ShopDB.TABLE + " s on s." + Contract.ShopDB.ID + " = o." + Contract.OfferDB.SHOP_ID +
                        " where cl." + Contract.CheckListDB.ID + " = ?" +
                        " group by cl." + Contract.CheckListDB.ID + " ASC;"
                , new String[]{"0"});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new Shop(
                    new Shop(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getInt(2),
                            cursor.getString(3),
                            cursor.getInt(4),
                            cursor.getInt(5),
                            cursor.getInt(6),
                            cursor.getInt(7),
                            cursor.getInt(8))));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

}

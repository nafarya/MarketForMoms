package com.example.dan.mommarket.database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dan.mommarket.model.Cart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dan on 21.08.16.
 */

public class CartDataSource {
    private static SQLiteDatabase database;
    private static SQLiteHelper dbHelper;

    private static CartDataSource instance;

    public static synchronized CartDataSource getInstance() {
        if (instance == null) {
            instance = new CartDataSource();
        }
        return instance;
    }

    public static void setDatabase(Context context) {
        dbHelper = new SQLiteHelper(context);
        return;
    }

    public static List<Cart> getCartList() {
        database = dbHelper.getReadableDatabase();
        List<Cart> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select " +
                        " cl." + Contract.CheckListDB.ID +
                        " ,cl." + Contract.CheckListDB.NAME +
                        " ,count( distinct o." + Contract.OfferDB.SHOP_ID + " )" +
                        " ,sum(  o." + Contract.OfferDB.PRICE + ")" +
                        " from " + Contract.CheckListDB.TABLE + " cl" +
                        " left join " + Contract.OfferItemDB.TABLE + " lo on lo." + Contract.OfferItemDB.LIST_ID + " = cl." + Contract.CheckListDB.ID +
                        " left join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.ID + " = lo." + Contract.OfferItemDB.OFFER_ID +
                        //  " left join " + Contract.ShopDB.TABLE + " s on s." + Contract.ShopDB.ID + " = o." + Contract.OfferDB.SHOP_ID +
                        " where cl." + Contract.CheckListDB.ID + " = ?" +
                        " group by cl." + Contract.CheckListDB.ID + ";"
                , new String[]{"0"});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new Cart(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public static Cart getCart() {
        database = dbHelper.getReadableDatabase();
        Cart cart = null;
        Cursor cursor = database.rawQuery("select " +
                        " cl." + Contract.CheckListDB.ID +
                        " ,cl." + Contract.CheckListDB.NAME +
                        " ,count( distinct o." + Contract.OfferDB.SHOP_ID + " )" +
                        " ,sum(  o." + Contract.OfferDB.PRICE + ") " +
                        " from " + Contract.CheckListDB.TABLE + " cl" +
                        " left join " + Contract.OfferItemDB.TABLE + " lo on lo." + Contract.OfferItemDB.LIST_ID + " = cl." + Contract.CheckListDB.ID +
                        " left join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.ID + " = lo." + Contract.OfferItemDB.OFFER_ID +

                        //  " left join " + Contract.ShopDB.TABLE + " s on s." + Contract.ShopDB.ID + " = o." + Contract.OfferDB.SHOP_ID +
                        " where cl." + Contract.CheckListDB.ID + " = ?" +
                        " group by cl." + Contract.CheckListDB.ID + " ASC;"
                , new String[]{"0"});

        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            cart = new Cart(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3));
        }
        cursor.close();
        return cart;
    }

    public static Cart getBestCart() {
        database = dbHelper.getReadableDatabase();
        Cart cart = null;
        Cursor cursor = database.rawQuery("select " +
                        " cl." + Contract.CheckListDB.ID +
                        " ,cl." + Contract.CheckListDB.NAME +
                        " ,count( distinct o." + Contract.OfferDB.SHOP_ID + " )" +
                        " ,sum(  o." + Contract.OfferDB.PRICE + ")" +
                        " from " + Contract.CheckListDB.TABLE + " cl" +
                        " left join " + Contract.OfferItemDB.TABLE + " lo on lo." + Contract.OfferItemDB.LIST_ID + " = cl." + Contract.CheckListDB.ID +
                        " left join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.ID + " = lo." + Contract.OfferItemDB.OFFER_ID +
                        " where cl." + Contract.CheckListDB.ID + " = ?" +
                        " group by cl." + Contract.CheckListDB.ID + " ASC;"
                , new String[]{"0"});

        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            cart = new Cart(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3));
        }
        cursor.close();
        return cart;
    }

    public static Cart getQuickDeliveryCart() {
        database = dbHelper.getReadableDatabase();
        Cart cart = null;
        Cursor cursor = database.rawQuery("select " +
                        " cl." + Contract.CheckListDB.ID +
                        " ,cl." + Contract.CheckListDB.NAME +
                        " ,count( distinct o." + Contract.OfferDB.SHOP_ID + " )" +
                        " ,sum(  o." + Contract.OfferDB.PRICE + ")" +
                        " from " + Contract.CheckListDB.TABLE + " cl" +
                        " left join " + Contract.OfferItemDB.TABLE + " lo on lo." + Contract.OfferItemDB.LIST_ID + " = cl." + Contract.CheckListDB.ID +
                        " left join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.ID + " = lo." + Contract.OfferItemDB.OFFER_ID +
                        //  " left join " + Contract.ShopDB.TABLE + " s on s." + Contract.ShopDB.ID + " = o." + Contract.OfferDB.SHOP_ID +
                        " where cl." + Contract.CheckListDB.ID + " = ?" +
                        " group by cl." + Contract.CheckListDB.ID + " ASC;"
                , new String[]{"0"});

        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            cart = new Cart(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3));
        }
        cursor.close();
        return cart;
    }
}

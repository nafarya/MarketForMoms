package com.example.dan.mommarket.database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

    public static List<Shop> getShopCartList(int cartType) {
        List<Shop> shopList = null;
        switch (cartType) {
            case 0:
                shopList = getBestShopList();
                break;
            case 1:
                shopList = getShopListByCheckList(0);
                break;
            case 2:
                shopList = getQuickDeliveryShopList();
                break;
        }

        return shopList;
    }

    public static List<Shop> getShopListByCheckList(int checkListId) {
        database = dbHelper.getReadableDatabase();
        List<Shop> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select " +
                        " s." + Contract.ShopDB.ID +
                        " ,s." + Contract.ShopDB.NAME +
                        " ,s." + Contract.ShopDB.DELIVERY_PRICE +
                        " ,s." + Contract.ShopDB.DELIVERY_TIME +
                        " ,s." + Contract.ShopDB.REFERENCE_COUNT +
                        " ,s." + Contract.ShopDB.RATE +
                        " ,lo." + Contract.OfferItemDB.LIST_ID +
                        " ,count( lo." + Contract.OfferItemDB.ID + " ) + s." + Contract.ShopDB.DELIVERY_PRICE +
                        " ,sum( o." + Contract.OfferDB.PRICE + ")" +
                        " from " + Contract.ShopDB.TABLE + " s" +
                        " join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.SHOP_ID + " = s." + Contract.ShopDB.ID +
                        " join " + Contract.OfferItemDB.TABLE + " lo on lo." + Contract.OfferItemDB.OFFER_ID + " = o." + Contract.OfferDB.ID +
                        " where lo." + Contract.OfferItemDB.LIST_ID + " = ?" +
                        " group by s." + Contract.ShopDB.ID + ";"
                , new String[]{String.valueOf(checkListId)});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new Shop(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6),
                    cursor.getInt(7),
                    cursor.getInt(8)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public static List<Shop> getBestShopList() {
        database = dbHelper.getReadableDatabase();
        List<Shop> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select " +
                        " sBest." + Contract.ShopDB.ID +
                        " ,sBest." + Contract.ShopDB.NAME +
                        " ,sBest." + Contract.ShopDB.DELIVERY_PRICE +
                        " ,sBest." + Contract.ShopDB.DELIVERY_TIME +
                        " ,sBest." + Contract.ShopDB.REFERENCE_COUNT +
                        " ,sBest." + Contract.ShopDB.RATE +
                        " ,lo." + Contract.OfferItemDB.LIST_ID +
                        " ,count( lo." + Contract.OfferItemDB.ID + " )" +
                        " ,sum( oBest." + Contract.OfferDB.PRICE + ")+ sBest." + Contract.ShopDB.DELIVERY_PRICE +
                        " from " + Contract.ShopDB.TABLE + " s" +
                        " join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.SHOP_ID + " = s." + Contract.ShopDB.ID +
                        " join " + Contract.OfferItemDB.TABLE + " lo on lo." + Contract.OfferItemDB.OFFER_ID + " = o." + Contract.OfferDB.ID +
                        " join " + Contract.OfferDB.TABLE + " oBest on oBest." + Contract.OfferDB.PRODUCT_ID + " = o." + Contract.OfferDB.PRODUCT_ID +
                        " join " + Contract.ShopDB.TABLE + " sBest on sBest." + Contract.ShopDB.ID + " = oBest." + Contract.OfferDB.SHOP_ID +
                        " where lo." + Contract.OfferItemDB.LIST_ID + " = ? AND" +
                        " oBest." + Contract.OfferDB.ID + " IN (SELECT ob." + Contract.OfferDB.ID +
                        " FROM " + Contract.OfferDB.TABLE + " ob " +
                        " WHERE ob." + Contract.OfferDB.PRODUCT_ID + " = oBest." + Contract.OfferDB.PRODUCT_ID +
                        " ORDER BY " + Contract.OfferDB.PRICE + " ASC " +
                        " LIMIT 1)" +
                        " group by oBest." + Contract.OfferDB.SHOP_ID + ";"
                , new String[]{"0"});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new Shop(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6),
                    cursor.getInt(7),
                    cursor.getInt(8)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public static List<Shop> getQuickDeliveryShopList() {
        database = dbHelper.getReadableDatabase();
        List<Shop> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select " +
                        " sBest." + Contract.ShopDB.ID +
                        " ,sBest." + Contract.ShopDB.NAME +
                        " ,sBest." + Contract.ShopDB.DELIVERY_PRICE +
                        " ,sBest." + Contract.ShopDB.DELIVERY_TIME +
                        " ,sBest." + Contract.ShopDB.REFERENCE_COUNT +
                        " ,sBest." + Contract.ShopDB.RATE +
                        " ,lo." + Contract.OfferItemDB.LIST_ID +
                        " ,count( lo." + Contract.OfferItemDB.ID + " )" +
                        " ,sum( oBest." + Contract.OfferDB.PRICE + ")+ sBest." + Contract.ShopDB.DELIVERY_PRICE +
                        " from " + Contract.ShopDB.TABLE + " s" +
                        " join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.SHOP_ID + " = s." + Contract.ShopDB.ID +
                        " join " + Contract.OfferItemDB.TABLE + " lo on lo." + Contract.OfferItemDB.OFFER_ID + " = o." + Contract.OfferDB.ID +
                        " join " + Contract.OfferDB.TABLE + " oBest on oBest." + Contract.OfferDB.PRODUCT_ID + " = o." + Contract.OfferDB.PRODUCT_ID +
                        " join " + Contract.ShopDB.TABLE + " sBest on sBest." + Contract.ShopDB.ID + " = oBest." + Contract.OfferDB.SHOP_ID +
                        " where lo." + Contract.OfferItemDB.LIST_ID + " = ? AND" +
                        " oBest." + Contract.OfferDB.SHOP_ID + " IN (SELECT sb." + Contract.ShopDB.ID +
                        " FROM " + Contract.ShopDB.TABLE + " sb " +
                        " join " + Contract.OfferDB.TABLE + " ob on ob." + Contract.OfferDB.SHOP_ID + " = sb." + Contract.ShopDB.ID +
                        " WHERE ob." + Contract.OfferDB.PRODUCT_ID + " = oBest." + Contract.OfferDB.PRODUCT_ID +
                        " ORDER BY sb." + Contract.ShopDB.DELIVERY_TIME_FLOAT + " ASC " +
                        " LIMIT 1)" +
                        " group by oBest." + Contract.OfferDB.SHOP_ID + ";"
                , new String[]{"0"});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new Shop(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6),
                    cursor.getInt(7),
                    cursor.getInt(8)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}

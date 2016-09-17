package com.example.dan.mommarket.database;


import android.content.ContentValues;
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

    public static Cart getCart(int position) {
        Cart cart = null;
        switch (position) {
            case 0:
                cart = getBestCart();
                break;
            case 1:
                cart = getCart();
                break;
            case 2:
                cart = getQuickDeliveryCart();
                break;
        }

        return cart;
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
                        " left join " + Contract.OfferItemDB.TABLE + " oi on oi." + Contract.OfferItemDB.LIST_ID + " = cl." + Contract.CheckListDB.ID +
                        " left join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.ID + " = oi." + Contract.OfferItemDB.OFFER_ID +
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
        Cursor cursor = database.rawQuery("Select " +
                        " cart.ID" +
                        " ,cart.NAME" +
                        " ,count(cart.SHOP_ID)" +
                        " ,sum(cart.SUM) + sum( s." + Contract.ShopDB.DELIVERY_PRICE + ")" +
                        " from (select " +
                        " cl." + Contract.CheckListDB.ID + " id " +
                        " ,cl." + Contract.CheckListDB.NAME + " NAME" +
                        " ,o." + Contract.OfferDB.SHOP_ID + " SHOP_ID " +
                        " ,sum(  o." + Contract.OfferDB.PRICE + " * oi." + Contract.OfferItemDB.COUNT + " ) SUM" +
                        " from " + Contract.CheckListDB.TABLE + " cl" +
                        " left join " + Contract.OfferItemDB.TABLE + " oi on oi." + Contract.OfferItemDB.LIST_ID + " = cl." + Contract.CheckListDB.ID +
                        " left join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.ID + " = oi." + Contract.OfferItemDB.OFFER_ID +
                        " where cl." + Contract.CheckListDB.ID + " = ?" +
                        " group by o." + Contract.OfferDB.SHOP_ID + ") cart" +
                        " left join " + Contract.ShopDB.TABLE + " s on s." + Contract.ShopDB.ID + " = cart.SHOP_ID" +
                        " group by cart.ID" + ";"
                , new String[]{"0"});

        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            cart = new Cart(
                    cursor.getInt(0),
                    "ВАШ ВЫБОР",
                    cursor.getInt(2),
                    cursor.getInt(3));
        }
        cursor.close();
        return cart;
    }

    public static Cart getBestCart() {
        database = dbHelper.getReadableDatabase();
        Cart cart = null;
        Cursor cursor = database.rawQuery("Select " +
                        " cart.ID" +
                        " ,cart.NAME" +
                        " ,count(cart.SHOP_ID)" +
                        " ,sum(cart.SUM) + sum(s." + Contract.ShopDB.DELIVERY_PRICE + ")" +
                        " from (select " +
                        " cl." + Contract.CheckListDB.ID + " id " +
                        " ,cl." + Contract.CheckListDB.NAME + " NAME" +
                        " ,oBest." + Contract.OfferDB.SHOP_ID + " SHOP_ID " +
                        " ,sum(oBest." + Contract.OfferDB.PRICE + " * oi." + Contract.OfferItemDB.COUNT + ") SUM" +
                        " from " + Contract.CheckListDB.TABLE + " cl" +
                        " left join " + Contract.OfferItemDB.TABLE + " oi on oi." + Contract.OfferItemDB.LIST_ID + " = cl." + Contract.CheckListDB.ID +
                        " left join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.ID + " = oi." + Contract.OfferItemDB.OFFER_ID +
                        " left join " + Contract.OfferDB.TABLE + " oBest on oBest." + Contract.OfferDB.PRODUCT_ID + " = o." + Contract.OfferDB.PRODUCT_ID +
                        " where cl." + Contract.CheckListDB.ID + " = ? AND" +
                        " oBest." + Contract.OfferDB.ID + " IN (SELECT ob." + Contract.OfferDB.ID +
                        " FROM " + Contract.OfferDB.TABLE + " ob " +
                        " WHERE ob." + Contract.OfferDB.PRODUCT_ID + " = oBest." + Contract.OfferDB.PRODUCT_ID +
                        " ORDER BY " + Contract.OfferDB.PRICE + " ASC " +
                        " LIMIT 1)" +
                        " group by oBest." + Contract.OfferDB.SHOP_ID + ") cart" +
                        " left join " + Contract.ShopDB.TABLE + " s on s." + Contract.ShopDB.ID + " = cart.SHOP_ID" +
                        " group by cart.ID" + ";"
                , new String[]{"0"});

        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            cart = new Cart(
                    cursor.getInt(0),
                    "ЛУЧШАЯ ЦЕНА",
                    cursor.getInt(2),
                    cursor.getInt(3));
        }
        cursor.close();
        return cart;
    }

    public static Cart getQuickDeliveryCart() {
        database = dbHelper.getReadableDatabase();
        Cart cart = null;
        Cursor cursor = database.rawQuery("Select " +
                        " cart.ID" +
                        " ,cart.NAME" +
                        " ,count(cart.SHOP_ID)" +
                        " ,sum(cart.SUM) + sum(s." + Contract.ShopDB.DELIVERY_PRICE + ")" +
                        " from (select " +
                        " cl." + Contract.CheckListDB.ID + " id " +
                        " ,cl." + Contract.CheckListDB.NAME + " NAME" +
                        " ,oBest." + Contract.OfferDB.SHOP_ID + " SHOP_ID " +
                        " ,sum(oBest." + Contract.OfferDB.PRICE + " * oi." + Contract.OfferItemDB.COUNT + ") SUM" +
                        " from " + Contract.CheckListDB.TABLE + " cl" +
                        " join " + Contract.OfferItemDB.TABLE + " oi on oi." + Contract.OfferItemDB.LIST_ID + " = cl." + Contract.CheckListDB.ID +
                        " join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.ID + " = oi." + Contract.OfferItemDB.OFFER_ID +
                        " join " + Contract.OfferDB.TABLE + " oBest on oBest." + Contract.OfferDB.PRODUCT_ID + " = o." + Contract.OfferDB.PRODUCT_ID +
                        " where cl." + Contract.CheckListDB.ID + " = ? AND" +
                        " oBest." + Contract.OfferDB.SHOP_ID + " IN (SELECT sb." + Contract.ShopDB.ID +
                        " FROM " + Contract.ShopDB.TABLE + " sb " +
                        " join " + Contract.OfferDB.TABLE + " ob on ob." + Contract.OfferDB.SHOP_ID + " = sb." + Contract.ShopDB.ID +
                        " WHERE ob." + Contract.OfferDB.PRODUCT_ID + " = oBest." + Contract.OfferDB.PRODUCT_ID +
                        " ORDER BY sb." + Contract.ShopDB.DELIVERY_TIME_FLOAT + " ASC " +
                        " LIMIT 1)" +
                        " group by oBest." + Contract.OfferDB.SHOP_ID + ") cart" +
                        " left join " + Contract.ShopDB.TABLE + " s on s." + Contract.ShopDB.ID + " = cart.SHOP_ID" +
                        " group by cart.ID" + ";"
                , new String[]{"0"});

        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            cart = new Cart(
                    cursor.getInt(0),
                    "БЫСТРАЯ ДОСТАВКА",
                    cursor.getInt(2),
                    cursor.getInt(3));
        }
        cursor.close();
        return cart;
    }

    public static Cart getOrder(int orderId) {
        database = dbHelper.getReadableDatabase();
        Cart cart = null;
        Cursor cursor = database.rawQuery("Select " +
                        " cart.ID" +
                        " ,cart.NAME" +
                        " ,count(cart.SHOP_ID)" +
                        " ,sum(cart.SUM) + sum( s." + Contract.ShopDB.DELIVERY_PRICE + ")" +
                        " ,cart.CITY" +
                        " ,cart.STREET" +
                        " ,cart.APPARTMERNT" +
                        " ,cart.COMMENT" +
                        " ,cart.FIRST_NAME" +
                        " ,cart.LAST_NAME" +
                        " ,cart.PHONE" +
                        " ,cart.EMAIL" +
                        " ,cart.CASH" +
                        " from (select " +
                        " cl." + Contract.CheckListDB.ID + " id " +
                        " ,cl." + Contract.CheckListDB.NAME + " NAME" +
                        " ,cl." + Contract.CheckListDB.CITY + " CITY" +
                        " ,cl." + Contract.CheckListDB.STREET + " STREET" +
                        " ,cl." + Contract.CheckListDB.APPARTMERNT + " APPARTMERNT" +
                        " ,cl." + Contract.CheckListDB.COMMENT + " COMMENT" +
                        " ,cl." + Contract.CheckListDB.FIRST_NAME + " FIRST_NAME" +
                        " ,cl." + Contract.CheckListDB.LAST_NAME + " LAST_NAME" +
                        " ,cl." + Contract.CheckListDB.PHONE + " PHONE" +
                        " ,cl." + Contract.CheckListDB.EMAIL + " EMAIL" +
                        " ,cl." + Contract.CheckListDB.CASH + " CASH" +
                        " ,o." + Contract.OfferDB.SHOP_ID + " SHOP_ID " +
                        " ,sum(  o." + Contract.OfferDB.PRICE + " * oi." + Contract.OfferItemDB.COUNT + " ) SUM" +
                        " from " + Contract.CheckListDB.TABLE + " cl" +
                        " left join " + Contract.OfferItemDB.TABLE + " oi on oi." + Contract.OfferItemDB.LIST_ID + " = cl." + Contract.CheckListDB.ID +
                        " left join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.ID + " = oi." + Contract.OfferItemDB.OFFER_ID +
                        " where cl." + Contract.CheckListDB.ID + " = ?" +
                        " group by o." + Contract.OfferDB.SHOP_ID + ") cart" +
                        " left join " + Contract.ShopDB.TABLE + " s on s." + Contract.ShopDB.ID + " = cart.SHOP_ID" +
                        " group by cart.ID" + ";"
                , new String[]{String.valueOf(orderId)});

        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            cart = new Cart(
                    cursor.getInt(0),
                    "ЗАКАЗ",
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getString(4) != null ? cursor.getString(4) : "",
                    cursor.getString(5) != null ? cursor.getString(5) : "",
                    cursor.getString(6) != null ? cursor.getString(6) : "",
                    cursor.getString(7) != null ? cursor.getString(7) : "",
                    cursor.getString(8) != null ? cursor.getString(8) : "",
                    cursor.getString(9) != null ? cursor.getString(9) : "",
                    cursor.getString(10) != null ? cursor.getString(10) : "",
                    cursor.getString(11) != null ? cursor.getString(11) : "",
                    cursor.getInt(12));
        }
        cursor.close();
        return cart;
    }

    public static void updateCart(Cart cart) {
        database = dbHelper.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Contract.CheckListDB.CITY, cart.getCity());
        cv.put(Contract.CheckListDB.STREET, cart.getStreet());
        cv.put(Contract.CheckListDB.APPARTMERNT, cart.getAppartment());
        cv.put(Contract.CheckListDB.COMMENT, cart.getComment());
        cv.put(Contract.CheckListDB.FIRST_NAME, cart.getFirstName());
        cv.put(Contract.CheckListDB.LAST_NAME, cart.getLastName());
        cv.put(Contract.CheckListDB.PHONE, cart.getPhone());
        cv.put(Contract.CheckListDB.EMAIL, cart.getEmail());
        cv.put(Contract.CheckListDB.CASH, cart.isCash() ? "1" : "0");
        database.update(Contract.CheckListDB.TABLE,
                cv,
                Contract.CheckListDB.ID + " = ?",
                new String[]{String.valueOf(cart.getId())});
    }

}

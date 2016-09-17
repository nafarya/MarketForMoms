package com.example.dan.mommarket.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dan.mommarket.model.OfferItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dan on 21.08.16.
 */

public class OfferItemDataSource {
    private static SQLiteDatabase database;
    private static SQLiteHelper dbHelper;

    private static OfferItemDataSource instance;

    public static synchronized OfferItemDataSource getInstance() {
        if (instance == null) {
            instance = new OfferItemDataSource();
        }
        return instance;
    }

    public static void setDatabase(Context context) {
        dbHelper = new SQLiteHelper(context);
        return;
    }


    public static OfferItem getOfferItemById(int offerItemId) {
        database = dbHelper.getReadableDatabase();
        OfferItem item = null;
        Cursor cursor = database.rawQuery("select " +
                        "  oi." + Contract.OfferItemDB.ID +
                        " ,oi." + Contract.OfferItemDB.COUNT +
                        " ,o." + Contract.OfferDB.ID +
                        " ,o." + Contract.OfferDB.SHOP_ID +
                        " ,o." + Contract.OfferDB.PRICE +
                        " ,p." + Contract.ProductDB.ID +
                        " ,p." + Contract.ProductDB.NAME +
                        " ,min(i." + Contract.ImageDB.URL + ")" +
                        " from " + Contract.OfferItemDB.TABLE + " oi" +
                        " left join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.ID + " = oi." + Contract.OfferItemDB.OFFER_ID +
                        " left join " + Contract.ProductDB.TABLE + " p on p." + Contract.ProductDB.ID + " = o." + Contract.OfferDB.PRODUCT_ID +
                        " left join " + Contract.ImageDB.TABLE + " i on i." + Contract.ImageDB.ITEM_ID + " = p." + Contract.ProductDB.ID +
                        " where oi." + Contract.OfferItemDB.ID + " = ?" +
                        " group by oi." + Contract.OfferItemDB.ID + " ;"
                , new String[]{String.valueOf(offerItemId)});

        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            item = new OfferItem(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getFloat(4),
                    cursor.getInt(5),
                    cursor.getString(6),
                    cursor.getString(7));
        }
        cursor.close();
        return item;
    }

    public static List<OfferItem> getOfferItemsByShopIdAndCart(int shopId, int cartType) {
        List<OfferItem> list = null;
        switch (cartType) {
            case 0:
                list = getBestOfferItemsByShopIdAndCart(shopId);
                break;
            case 1:
                list = getOfferItemsByShopIdAndChecklistId(shopId, 0);
                break;
            case 2:
                list = getQuickDeliveryOfferItemsByShopIdAndCart(shopId);
                break;
        }
        return list;
    }

    public static List<OfferItem> getOfferItemsByShopIdAndChecklistId(int shopId, int checklistId) {
        database = dbHelper.getReadableDatabase();
        List<OfferItem> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select " +
                        "  oi." + Contract.OfferItemDB.ID +
                        " ,oi." + Contract.OfferItemDB.COUNT +
                        " ,o." + Contract.OfferDB.ID +
                        " ,o." + Contract.OfferDB.SHOP_ID +
                        " ,o." + Contract.OfferDB.PRICE +
                        " ,p." + Contract.ProductDB.ID +
                        " ,p." + Contract.ProductDB.NAME +
                        " ,min(i." + Contract.ImageDB.URL + ")" +
                        " from " + Contract.OfferItemDB.TABLE + " oi" +
                        " left join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.ID + " = oi." + Contract.OfferItemDB.OFFER_ID +
                        " left join " + Contract.ProductDB.TABLE + " p on p." + Contract.ProductDB.ID + " = o." + Contract.OfferDB.PRODUCT_ID +
                        " left join " + Contract.ImageDB.TABLE + " i on i." + Contract.ImageDB.ITEM_ID + " = p." + Contract.ProductDB.ID +
                        " where oi." + Contract.OfferItemDB.LIST_ID + " = ?" +
                        " AND o." + Contract.OfferDB.SHOP_ID + " = ?" +
                        " group by oi." + Contract.OfferItemDB.ID + " ;"
                , new String[]{String.valueOf(checklistId), String.valueOf(shopId)});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new OfferItem(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getFloat(4),
                    cursor.getInt(5),
                    cursor.getString(6),
                    cursor.getString(7)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public static List<OfferItem> getBestOfferItemsByShopIdAndCart(int shopId) {
        database = dbHelper.getReadableDatabase();
        List<OfferItem> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select " +
                        "  oi." + Contract.OfferItemDB.ID +
                        " ,oi." + Contract.OfferItemDB.COUNT +
                        " ,oBest." + Contract.OfferDB.ID +
                        " ,oBest." + Contract.OfferDB.SHOP_ID +
                        " ,oBest." + Contract.OfferDB.PRICE +
                        " ,p." + Contract.ProductDB.ID +
                        " ,p." + Contract.ProductDB.NAME +
                        " ,min(i." + Contract.ImageDB.URL + ")" +
                        " from " + Contract.OfferItemDB.TABLE + " oi" +
                        " join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.ID + " = oi." + Contract.OfferItemDB.OFFER_ID +
                        " join " + Contract.ProductDB.TABLE + " p on p." + Contract.ProductDB.ID + " = o." + Contract.OfferDB.PRODUCT_ID +
                        " left join " + Contract.ImageDB.TABLE + " i on i." + Contract.ImageDB.ITEM_ID + " = p." + Contract.ProductDB.ID +
                        " join " + Contract.OfferDB.TABLE + " oBest on oBest." + Contract.OfferDB.PRODUCT_ID + " = o." + Contract.OfferDB.PRODUCT_ID +
                        " join " + Contract.ShopDB.TABLE + " sBest on sBest." + Contract.ShopDB.ID + " = oBest." + Contract.OfferDB.SHOP_ID +
                        " where oi." + Contract.OfferItemDB.LIST_ID + " = ?" +
                        " AND oBest." + Contract.OfferDB.SHOP_ID + " = ?" +
                        " AND oBest." + Contract.OfferDB.ID + " IN (SELECT ob." + Contract.OfferDB.ID +
                        " FROM " + Contract.OfferDB.TABLE + " ob " +
                        " WHERE ob." + Contract.OfferDB.PRODUCT_ID + " = oBest." + Contract.OfferDB.PRODUCT_ID +
                        " ORDER BY " + Contract.OfferDB.PRICE + " ASC " +
                        " LIMIT 1)" +
                        " group by oi." + Contract.OfferItemDB.ID + " ;"
                , new String[]{"0", String.valueOf(shopId)});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new OfferItem(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getFloat(4),
                    cursor.getInt(5),
                    cursor.getString(6),
                    cursor.getString(7)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public static List<OfferItem> getQuickDeliveryOfferItemsByShopIdAndCart(int shopId) {
        database = dbHelper.getReadableDatabase();
        List<OfferItem> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select " +
                        "  oi." + Contract.OfferItemDB.ID +
                        " ,oi." + Contract.OfferItemDB.COUNT +
                        " ,oBest." + Contract.OfferDB.ID +
                        " ,oBest." + Contract.OfferDB.SHOP_ID +
                        " ,oBest." + Contract.OfferDB.PRICE +
                        " ,p." + Contract.ProductDB.ID +
                        " ,p." + Contract.ProductDB.NAME +
                        " ,min(i." + Contract.ImageDB.URL + ")" +
                        " from " + Contract.OfferItemDB.TABLE + " oi" +
                        " join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.ID + " = oi." + Contract.OfferItemDB.OFFER_ID +
                        " join " + Contract.ProductDB.TABLE + " p on p." + Contract.ProductDB.ID + " = o." + Contract.OfferDB.PRODUCT_ID +
                        " left join " + Contract.ImageDB.TABLE + " i on i." + Contract.ImageDB.ITEM_ID + " = p." + Contract.ProductDB.ID +
                        " join " + Contract.OfferDB.TABLE + " oBest on oBest." + Contract.OfferDB.PRODUCT_ID + " = o." + Contract.OfferDB.PRODUCT_ID +
                        " join " + Contract.ShopDB.TABLE + " sBest on sBest." + Contract.ShopDB.ID + " = oBest." + Contract.OfferDB.SHOP_ID +
                        " where oi." + Contract.OfferItemDB.LIST_ID + " = ?" +
                        " AND oBest." + Contract.OfferDB.SHOP_ID + " = ?" +
                        " AND oBest." + Contract.OfferDB.SHOP_ID + " IN (SELECT sb." + Contract.ShopDB.ID +
                        " FROM " + Contract.ShopDB.TABLE + " sb " +
                        " join " + Contract.OfferDB.TABLE + " ob on ob." + Contract.OfferDB.SHOP_ID + " = sb." + Contract.ShopDB.ID +
                        " WHERE ob." + Contract.OfferDB.PRODUCT_ID + " = oBest." + Contract.OfferDB.PRODUCT_ID +
                        " ORDER BY sb." + Contract.ShopDB.DELIVERY_TIME_FLOAT + " ASC " +
                        " LIMIT 1)" +
                        " group by oi." + Contract.OfferItemDB.ID + " ;"
                , new String[]{"0", String.valueOf(shopId)});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new OfferItem(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getFloat(4),
                    cursor.getInt(5),
                    cursor.getString(6),
                    cursor.getString(7)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public static void updateOfferItem(int offerItemId, float price, int count) {
        database = dbHelper.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Contract.OfferItemDB.PRICE, price);
        cv.put(Contract.OfferItemDB.COUNT, count);
        database.update(Contract.OfferItemDB.TABLE,
                cv,
                Contract.OfferItemDB.ID + " = ?",
                new String[]{String.valueOf(offerItemId)});
    }

    public static void deleteOfferItem(int offerItemId) {
        database = dbHelper.getReadableDatabase();
        database.delete(Contract.OfferItemDB.TABLE,
                Contract.OfferItemDB.ID + " = ? ",
                new String[]{String.valueOf(offerItemId)});
    }


    public static void updateOrder(int listId, int cartType) {

        database.delete(Contract.OfferItemDB.TABLE,
                Contract.OfferItemDB.LIST_ID + " = ? ",
                new String[]{String.valueOf(2)});
        switch (cartType) {
            case 0:
                updateOfferFromBestOffer(listId);
                break;
            case 1:
                updateOfferFromCart(listId);
                break;
            case 2:
                updateOfferFromQuickDelivery(listId);
                break;
        }
    }

    public static void updateOfferFromBestOffer(int listId) {
        database = dbHelper.getReadableDatabase();
        database.execSQL("insert into " + Contract.OfferItemDB.TABLE +
                        " (" + Contract.OfferItemDB.LIST_ID + "," +
                        Contract.OfferItemDB.OFFER_ID + "," +
                        Contract.OfferItemDB.COUNT + ")" +
                        " select " +
                        " ? LIST_ID" +
                        " ,oBest." + Contract.OfferDB.ID +
                        " ,oi." + Contract.OfferItemDB.COUNT +
                        " from " + Contract.OfferItemDB.TABLE + " oi" +
                        " join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.ID + " = oi." + Contract.OfferItemDB.OFFER_ID +
                        " join " + Contract.ProductDB.TABLE + " p on p." + Contract.ProductDB.ID + " = o." + Contract.OfferDB.PRODUCT_ID +
                        " join " + Contract.OfferDB.TABLE + " oBest on oBest." + Contract.OfferDB.PRODUCT_ID + " = o." + Contract.OfferDB.PRODUCT_ID +
                        " join " + Contract.ShopDB.TABLE + " sBest on sBest." + Contract.ShopDB.ID + " = oBest." + Contract.OfferDB.SHOP_ID +
                        " where oi." + Contract.OfferItemDB.LIST_ID + " = ?" +
                        " AND oBest." + Contract.OfferDB.ID + " IN (SELECT ob." + Contract.OfferDB.ID +
                        " FROM " + Contract.OfferDB.TABLE + " ob " +
                        " WHERE ob." + Contract.OfferDB.PRODUCT_ID + " = oBest." + Contract.OfferDB.PRODUCT_ID +
                        " ORDER BY " + Contract.OfferDB.PRICE + " ASC " +
                        " LIMIT 1)" +
                        " group by oi." + Contract.OfferItemDB.ID + " ;"
                , new String[]{String.valueOf(listId),"0"});
    }

    public static void updateOfferFromCart(int listId) {
        database = dbHelper.getReadableDatabase();

        database.execSQL("insert into " + Contract.OfferItemDB.TABLE +
                        " (" + Contract.OfferItemDB.LIST_ID + "," +
                        Contract.OfferItemDB.OFFER_ID + "," +
                        Contract.OfferItemDB.COUNT + ")" +
                        " select " +
                        " ? LIST_ID" +
                        " ,oi." + Contract.OfferItemDB.OFFER_ID +
                        " ,oi." + Contract.OfferItemDB.COUNT +
                        " from " + Contract.OfferItemDB.TABLE + " oi" +
                        " where oi." + Contract.OfferItemDB.LIST_ID + " = ?" + " ;"
                , new String[]{String.valueOf(listId), "0"});
    }


    public static void updateOfferFromQuickDelivery(int listId) {
        database = dbHelper.getReadableDatabase();
        database.execSQL("insert into " + Contract.OfferItemDB.TABLE +
                        " (" + Contract.OfferItemDB.LIST_ID + "," +
                        Contract.OfferItemDB.OFFER_ID + "," +
                        Contract.OfferItemDB.COUNT + ")" +
                        " select " +
                        " ? LIST_ID" +
                        " ,oBest." + Contract.OfferDB.ID +
                        " ,oi." + Contract.OfferItemDB.COUNT +
                        " from " + Contract.OfferItemDB.TABLE + " oi" +
                        " join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.ID + " = oi." + Contract.OfferItemDB.OFFER_ID +
                        " join " + Contract.ProductDB.TABLE + " p on p." + Contract.ProductDB.ID + " = o." + Contract.OfferDB.PRODUCT_ID +
                        " join " + Contract.OfferDB.TABLE + " oBest on oBest." + Contract.OfferDB.PRODUCT_ID + " = o." + Contract.OfferDB.PRODUCT_ID +
                        " join " + Contract.ShopDB.TABLE + " sBest on sBest." + Contract.ShopDB.ID + " = oBest." + Contract.OfferDB.SHOP_ID +
                        " where oi." + Contract.OfferItemDB.LIST_ID + " = ?" +
                        " AND oBest." + Contract.OfferDB.SHOP_ID + " IN (SELECT sb." + Contract.ShopDB.ID +
                        " FROM " + Contract.ShopDB.TABLE + " sb " +
                        " join " + Contract.OfferDB.TABLE + " ob on ob." + Contract.OfferDB.SHOP_ID + " = sb." + Contract.ShopDB.ID +
                        " WHERE ob." + Contract.OfferDB.PRODUCT_ID + " = oBest." + Contract.OfferDB.PRODUCT_ID +
                        " ORDER BY sb." + Contract.ShopDB.DELIVERY_TIME_FLOAT + " ASC " +
                        " LIMIT 1)" +
                        " group by oi." + Contract.OfferItemDB.ID + " ;"а
                , new String[]{String.valueOf(listId),"0"});
    }


}

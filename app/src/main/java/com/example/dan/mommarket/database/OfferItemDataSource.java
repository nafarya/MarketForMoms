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

    public static List<OfferItem> getOfferItemsByShopIdAndCart(int shopId) {
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
                        " ,min(i." + Contract.ImageDB.URL +")"+
                        " from " + Contract.OfferItemDB.TABLE + " oi" +
                        " left join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.ID + " = oi." + Contract.OfferItemDB.OFFER_ID +
                        " left join " + Contract.ProductDB.TABLE + " p on p." + Contract.ProductDB.ID + " = o." + Contract.OfferDB.PRODUCT_ID +
                        " left join " + Contract.ImageDB.TABLE + " i on i." + Contract.ImageDB.ITEM_ID + " = p." + Contract.ProductDB.ID +
                        " where oi." + Contract.OfferItemDB.LIST_ID + " = ?" +
                        " AND o." + Contract.OfferDB.SHOP_ID + " = ?" +
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
}

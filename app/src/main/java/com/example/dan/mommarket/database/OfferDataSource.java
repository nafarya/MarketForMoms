package com.example.dan.mommarket.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dan.mommarket.model.Offer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dan on 21.08.16.
 */

public class OfferDataSource {
    private static SQLiteDatabase database;
    private static SQLiteHelper dbHelper;

    private static OfferDataSource instance;

    public static synchronized OfferDataSource getInstance() {
        if (instance == null) {
            instance = new OfferDataSource();
        }
        return instance;
    }

    public static void setDatabase(Context context) {
        dbHelper = new SQLiteHelper(context);
        return;
    }

    public static List<Offer> getOffersByProductId(int productId) {
        database = dbHelper.getReadableDatabase();
        List<Offer> offerList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select " +
                        " o." + Contract.OfferDB.ID +
                        " ,o." + Contract.OfferDB.PRICE +
                        " ,s." + Contract.ShopDB.ID +
                        " ,s." + Contract.ShopDB.NAME +
                        " ,s." + Contract.ShopDB.DELIVERY_PRICE +
                        " ,s." + Contract.ShopDB.DELIVERY_TIME +
                        " ,s." + Contract.ShopDB.REFERENCE_COUNT +
                        " ,s." + Contract.ShopDB.RATE +
                        " from " + Contract.OfferDB.TABLE + " o" +
                        " left join " + Contract.ShopDB.TABLE + " s on s." + Contract.ShopDB.ID + "= o." + Contract.OfferDB.SHOP_ID +
                        " where o." + Contract.OfferDB.PRODUCT_ID + " = ?" +
                        " order by o." + Contract.OfferDB.PRICE + " ASC;"
                , new String[]{Integer.toString(productId)});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            offerList.add(new Offer(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getInt(6),
                    cursor.getInt(7)));
            cursor.moveToNext();
        }
        cursor.close();
        return offerList;
    }

    public static Offer getMinimalOfferByProductId(int productId) {
        database = dbHelper.getReadableDatabase();
        Offer offer = null;
        Cursor cursor = database.rawQuery("select " +
                        " o." + Contract.OfferDB.ID +
                        " ,o." + Contract.OfferDB.PRICE +
                        " ,s." + Contract.ShopDB.ID +
                        " ,s." + Contract.ShopDB.NAME +
                        " ,s." + Contract.ShopDB.DELIVERY_PRICE +
                        " ,s." + Contract.ShopDB.DELIVERY_TIME +
                        " ,s." + Contract.ShopDB.REFERENCE_COUNT +
                        " ,s." + Contract.ShopDB.RATE +
                        " from " + Contract.OfferDB.TABLE + " o" +
                        " left join " + Contract.ShopDB.TABLE + " s on s." + Contract.ShopDB.ID + "= o." + Contract.OfferDB.SHOP_ID +
                        " where o." + Contract.OfferDB.PRODUCT_ID + " = ?" +
                        " order by o." + Contract.OfferDB.PRICE + " ASC;"
                , new String[]{Integer.toString(productId)});

        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            offer = new Offer(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getInt(6),
                    cursor.getInt(7));
            cursor.moveToNext();
        }
        cursor.close();

        return offer;
    }

    public static List<Offer> getOffersByShopIdAndCart(int shopId) {
        database = dbHelper.getReadableDatabase();
        List<Offer> offerList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select " +
                        " o." + Contract.OfferDB.ID +
                        " ,o." + Contract.OfferDB.PRICE +
                        " ,s." + Contract.ShopDB.ID +
                        " ,s." + Contract.ShopDB.NAME +
                        " ,s." + Contract.ShopDB.DELIVERY_PRICE +
                        " ,s." + Contract.ShopDB.DELIVERY_TIME +
                        " ,s." + Contract.ShopDB.REFERENCE_COUNT +
                        " ,s." + Contract.ShopDB.RATE +
                        " from " + Contract.OfferDB.TABLE + " o" +
                        " left join " + Contract.ShopDB.TABLE + " s on s." + Contract.ShopDB.ID + "= o." + Contract.OfferDB.SHOP_ID +
                        " where o." + Contract.OfferDB.PRODUCT_ID + " = ?" +
                        " order by o." + Contract.OfferDB.PRICE + " ASC;"
                , new String[]{Integer.toString(shopId)});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            offerList.add(new Offer(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getInt(6),
                    cursor.getInt(7)));
            cursor.moveToNext();
        }
        cursor.close();
        return offerList;
    }

    public static void addOfferToList(int offerId, int listId, float price) {
        database = dbHelper.getReadableDatabase();
        ContentValues cv = new ContentValues();
        Cursor cursor = database.query(Contract.OfferItemDB.TABLE,
                new String[]{Contract.OfferItemDB.ID, Contract.OfferItemDB.COUNT},
                Contract.OfferItemDB.LIST_ID + " = ? AND " + Contract.OfferItemDB.OFFER_ID + " = ?",
                new String[]{String.valueOf(listId), String.valueOf(offerId)},
                null,
                null,
                null);
        cursor.moveToFirst();
        if (cursor.isAfterLast()) {
            //нет записей
            cv.put(Contract.OfferItemDB.OFFER_ID, offerId);
            cv.put(Contract.OfferItemDB.LIST_ID, listId);
            cv.put(Contract.OfferItemDB.PRICE, price);
            cv.put(Contract.OfferItemDB.COUNT, "1");
            database.insert(Contract.OfferItemDB.TABLE, null, cv);
        } else {
            //есть записи
            cv.put(Contract.OfferItemDB.PRICE, price);
            cv.put(Contract.OfferItemDB.COUNT, String.valueOf(cursor.getInt(1)+1));
            database.update(Contract.OfferItemDB.TABLE,
                    cv,
                    Contract.OfferItemDB.ID + " = ?" ,
                    new String[]{String.valueOf(cursor.getInt(0))});
        }
    }

    public static void deleteOfferFromList(int offerId, int listId,boolean all) {
        database = dbHelper.getReadableDatabase();
        if (all){
            database.delete(Contract.OfferItemDB.TABLE,
                    Contract.OfferItemDB.LIST_ID + " = ? AND " + Contract.OfferItemDB.OFFER_ID + " = ?",
                    new String[]{String.valueOf(listId), String.valueOf(offerId)});
        }else{
            ContentValues cv = new ContentValues();
            Cursor cursor = database.query(Contract.OfferItemDB.TABLE,
                    new String[]{Contract.OfferItemDB.ID, Contract.OfferItemDB.COUNT},
                    Contract.OfferItemDB.LIST_ID + " = ? AND " + Contract.OfferItemDB.OFFER_ID + " = ?",
                    new String[]{String.valueOf(listId), String.valueOf(offerId)},
                    null,
                    null,
                    null);
            cursor.moveToFirst();
            if (!cursor.isAfterLast()) {
                if (cursor.getInt(1)==1){
                    database.delete(Contract.OfferItemDB.TABLE,
                            Contract.OfferItemDB.LIST_ID + " = ? AND " + Contract.OfferItemDB.OFFER_ID + " = ?",
                            new String[]{String.valueOf(listId), String.valueOf(offerId)});
                }else {
                    cv.put(Contract.OfferItemDB.COUNT, String.valueOf(cursor.getInt(1)-1));
                    database.update(Contract.OfferItemDB.TABLE,
                            cv,
                            Contract.OfferItemDB.ID + " = ?" ,
                            new String[]{String.valueOf(cursor.getInt(0))});
                }
            }
        }
    }


}

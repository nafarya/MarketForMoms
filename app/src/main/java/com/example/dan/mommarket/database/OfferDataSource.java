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
        Cursor offerCursor = database.rawQuery("select " +
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

        offerCursor.moveToFirst();
        while (!offerCursor.isAfterLast()) {
            offerList.add(new Offer(
                    offerCursor.getInt(0),
                    offerCursor.getInt(1),
                    offerCursor.getInt(2),
                    offerCursor.getString(3),
                    offerCursor.getInt(4),
                    offerCursor.getString(5),
                    offerCursor.getInt(6),
                    offerCursor.getInt(7)));
            offerCursor.moveToNext();
        }
        offerCursor.close();
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
        Cursor offerCursor = database.rawQuery("select " +
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

        offerCursor.moveToFirst();
        while (!offerCursor.isAfterLast()) {
            offerList.add(new Offer(
                    offerCursor.getInt(0),
                    offerCursor.getInt(1),
                    offerCursor.getInt(2),
                    offerCursor.getString(3),
                    offerCursor.getInt(4),
                    offerCursor.getString(5),
                    offerCursor.getInt(6),
                    offerCursor.getInt(7)));
            offerCursor.moveToNext();
        }
        offerCursor.close();
        return offerList;
    }

    public static void addOfferToList(int offerId,int listId,float price){
        database = dbHelper.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Contract.ListOfferDB.OFFER_ID, offerId);
        cv.put(Contract.ListOfferDB.LIST_ID, listId);
        cv.put(Contract.ListOfferDB.PRICE, price);
        cv.put(Contract.ListOfferDB.COUNT, "1");
        database.insert(Contract.ListOfferDB.TABLE, null, cv);
    }

    public static void updateOfferToList(int offerId,int listId,float price,int count){
        database = dbHelper.getReadableDatabase();
        ContentValues cv = new ContentValues();
     //   cv.put(Contract.ListOfferDB.OFFER_ID, offerId);
     //   cv.put(Contract.ListOfferDB.LIST_ID, listId);
        cv.put(Contract.ListOfferDB.PRICE, price);
        cv.put(Contract.ListOfferDB.COUNT, count);
        database.update(Contract.ListOfferDB.TABLE, cv, Contract.ListOfferDB.LIST_ID +" = ? AND "+Contract.ListOfferDB.OFFER_ID +" = ?",new String[]{String.valueOf(listId),String.valueOf(offerId)});
    }
}

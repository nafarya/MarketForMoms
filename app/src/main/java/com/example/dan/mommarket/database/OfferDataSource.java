package com.example.dan.mommarket.database;

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
        Cursor categoryCursor = database.rawQuery("select " +
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

        categoryCursor.moveToFirst();
        while (!categoryCursor.isAfterLast()) {
            offerList.add(new Offer(
                    categoryCursor.getInt(0),
                    categoryCursor.getInt(1),
                    categoryCursor.getInt(2),
                    categoryCursor.getString(3),
                    categoryCursor.getInt(4),
                    categoryCursor.getString(5),
                    categoryCursor.getInt(6),
                    categoryCursor.getInt(7)));
            categoryCursor.moveToNext();
        }
        categoryCursor.close();
        return offerList;
    }

    public static Offer getMinimalOfferByProductId(int productId) {
        database = dbHelper.getReadableDatabase();
        Offer offer = null;
        Cursor categoryCursor = database.rawQuery("select " +
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

        categoryCursor.moveToFirst();
        if (!categoryCursor.isAfterLast()) {
            offer = new Offer(
                    categoryCursor.getInt(0),
                    categoryCursor.getInt(1),
                    categoryCursor.getInt(2),
                    categoryCursor.getString(3),
                    categoryCursor.getInt(4),
                    categoryCursor.getString(5),
                    categoryCursor.getInt(6),
                    categoryCursor.getInt(7));
            categoryCursor.moveToNext();
        }
        categoryCursor.close();

        return offer;
    }
}

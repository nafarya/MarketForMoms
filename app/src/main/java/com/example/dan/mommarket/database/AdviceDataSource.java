package com.example.dan.mommarket.database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dan.mommarket.model.Advice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dan on 21.08.16.
 */

public class AdviceDataSource {
    private static SQLiteDatabase database;
    private static SQLiteHelper dbHelper;

    private static AdviceDataSource instance;

    public static synchronized AdviceDataSource getInstance() {
        if (instance == null) {
            instance = new AdviceDataSource();
        }
        return instance;
    }

    public static void setDatabase(Context context) {
        dbHelper = new SQLiteHelper(context);
        return;
    }

    public static List<Advice> getAllAdvices() {
        database = dbHelper.getReadableDatabase();
        List<Advice> adviceList = new ArrayList<>();
        Cursor adviceCursor = database.rawQuery("select " +
                        " a." + Contract.AdviceDB.ID +
                        " ,a." + Contract.AdviceDB.NAME +
                        " ,a." + Contract.AdviceDB.SHORT_DESC +
                        " ,a." + Contract.AdviceDB.DESCRIPTION +
                        " ,a." + Contract.AdviceDB.IMAGE +
                        " from " + Contract.AdviceDB.TABLE + " a" + ";"
                , null);

        adviceCursor.moveToFirst();
        while (!adviceCursor.isAfterLast()) {
            adviceList.add(new Advice(
                    adviceCursor.getInt(0),
                    adviceCursor.getString(1),
                    adviceCursor.getString(2),
                    adviceCursor.getString(3),
                    adviceCursor.getString(4)
            ));
            adviceCursor.moveToNext();
        }
        adviceCursor.close();
        return adviceList;
    }

    public static Advice getAdviceById(int adviceId) {
        database = dbHelper.getReadableDatabase();
        Advice advice = null;
        Cursor adviceCursor = database.rawQuery("select " +
                        " a." + Contract.AdviceDB.ID +
                        " ,a." + Contract.AdviceDB.NAME +
                        " ,a." + Contract.AdviceDB.SHORT_DESC +
                        " ,a." + Contract.AdviceDB.DESCRIPTION +
                        " ,a." + Contract.AdviceDB.IMAGE +
                        " ,a." + Contract.AdviceDB.IMAGE_0 +
                        " ,a." + Contract.AdviceDB.AUTHOR_NAME +
                        " ,a." + Contract.AdviceDB.AUTHOR_TEXT +
                        " ,a." + Contract.AdviceDB.AUTHOR_IMAGE +
                        " ,a." + Contract.AdviceDB.TEXT_0 +
                        " ,a." + Contract.AdviceDB.HEADER_1 +
                        " ,a." + Contract.AdviceDB.TEXT_1 +
                        " ,a." + Contract.AdviceDB.IMAGE_1 +
                        " ,a." + Contract.AdviceDB.HEADER_2 +
                        " ,a." + Contract.AdviceDB.TEXT_2 +
                        " ,c1." + Contract.ProductCategoryDB.ID +
                        " ,c1." + Contract.ProductCategoryDB.NAME +
                        " ,c2." + Contract.ProductCategoryDB.ID +
                        " ,c2." + Contract.ProductCategoryDB.NAME +
                        " ,c3." + Contract.ProductCategoryDB.ID +
                        " ,c3." + Contract.ProductCategoryDB.NAME +
                        " from " + Contract.AdviceDB.TABLE + " a" +
                        " left join " + Contract.ProductCategoryDB.TABLE + " c1 on c1." + Contract.ProductCategoryDB.ID + "= a." + Contract.AdviceDB.CATEGORY_1 +
                        " left join " + Contract.ProductCategoryDB.TABLE + " c2 on c2." + Contract.ProductCategoryDB.ID + "= a." + Contract.AdviceDB.CATEGORY_2 +
                        " left join " + Contract.ProductCategoryDB.TABLE + " c3 on c3." + Contract.ProductCategoryDB.ID + "= a." + Contract.AdviceDB.CATEGORY_3 +
                        " where a." + Contract.AdviceDB.ID + " = ? ;"
                , new String[]{Integer.toString(adviceId)});
        adviceCursor.moveToFirst();
        if (!adviceCursor.isAfterLast()) {
            advice = new Advice(
                    adviceCursor.getInt(0),
                    adviceCursor.getString(1),
                    adviceCursor.getString(2),
                    adviceCursor.getString(3),
                    adviceCursor.getString(4),
                    adviceCursor.getString(5),
                    adviceCursor.getString(6),
                    adviceCursor.getString(7),
                    adviceCursor.getString(8),
                    adviceCursor.getString(9),
                    adviceCursor.getString(10),
                    adviceCursor.getString(11),
                    adviceCursor.getString(12),
                    adviceCursor.getString(13),
                    adviceCursor.getString(14),
                    adviceCursor.getInt(15),
                    adviceCursor.getString(16),
                    adviceCursor.getInt(17),
                    adviceCursor.getString(18),
                    adviceCursor.getInt(19),
                    adviceCursor.getString(20)
            );
            adviceCursor.moveToNext();
        }
        adviceCursor.close();
        return advice;
    }
}

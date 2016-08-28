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

public class AdviceDataSouce {
    private static SQLiteDatabase database;
    private static SQLiteHelper dbHelper;

    private static AdviceDataSouce instance;

    public static synchronized AdviceDataSouce getInstance() {
        if (instance == null) {
            instance = new AdviceDataSouce();
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
                        " ,i." + Contract.ImageDB.URL +
                        " from " + Contract.AdviceDB.TABLE + " a" +
                        " left join " + Contract.ImageDB.TABLE + " i on i." + Contract.ImageDB.ID + "= a." + Contract.AdviceDB.IMAGE_ID + ";"
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
}

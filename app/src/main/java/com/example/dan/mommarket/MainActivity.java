package com.example.dan.mommarket;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.dan.mommarket.database.Contract;
import com.example.dan.mommarket.database.SQLiteHelper;
import com.example.dan.mommarket.model.Child;

public class MainActivity extends AppCompatActivity {
    SQLiteHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new SQLiteHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.insertFakeData(db,this,R.raw.fakemarket);

    }
}

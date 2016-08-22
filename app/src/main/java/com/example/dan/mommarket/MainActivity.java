package com.example.dan.mommarket;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.dan.mommarket.database.ProductDataSource;
import com.example.dan.mommarket.database.SQLiteHelper;
import com.example.dan.mommarket.model.Product;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    SQLiteHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.deleteDatabase("mommarket.db");

        dbHelper = new SQLiteHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        insertDataToDB(db);
        ProductDataSource productDataSource = new ProductDataSource(this);
        List<Product> productList = productDataSource.getAllProducts();
        Log.i("asdf",""+productList.size());
    }

    void insertDataToDB(SQLiteDatabase db) {
        dbHelper.insertFakeData(db,this,R.raw.feature);
        dbHelper.insertFakeData(db,this,R.raw.offer);
        dbHelper.insertFakeData(db,this,R.raw.product);
        dbHelper.insertFakeData(db,this,R.raw.product_category);
        dbHelper.insertFakeData(db,this,R.raw.product_feature);
        dbHelper.insertFakeData(db,this,R.raw.shop);
    }
}

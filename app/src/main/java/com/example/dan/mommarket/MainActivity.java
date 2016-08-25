package com.example.dan.mommarket;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.dan.mommarket.database.CategoryDataSource;
import com.example.dan.mommarket.database.ProductDataSource;
import com.example.dan.mommarket.database.SQLiteHelper;
import com.example.dan.mommarket.model.Feature;
import com.example.dan.mommarket.model.Product;
import com.example.dan.mommarket.model.ProductCategory;

import java.util.ArrayList;
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
        List<Feature> features = new ArrayList<>();
        features.add(new Feature(3, "aaa", "1"));
        features.add(new Feature(4, "aaa", "1"));
        List<Product> productList = productDataSource.getProductsByFeatures(features);
        Log.i("asdf", "" + productList.size());
        CategoryDataSource categoryDataSource = new CategoryDataSource(this);
        List<ProductCategory> categoryList = categoryDataSource.getChildCategories(1);
        Log.i("asdf", "" + categoryList.size());
    }

    void insertDataToDB(SQLiteDatabase db) {
        dbHelper.insertFakeData(db, this, R.raw.check_list);
        dbHelper.insertFakeData(db, this, R.raw.feature);
        dbHelper.insertFakeData(db, this, R.raw.list_offer);
        dbHelper.insertFakeData(db, this, R.raw.offer);
        dbHelper.insertFakeData(db, this, R.raw.product);
        dbHelper.insertFakeData(db, this, R.raw.product_category);
        dbHelper.insertFakeData(db, this, R.raw.product_feature);
        dbHelper.insertFakeData(db, this, R.raw.shop);
    }
}

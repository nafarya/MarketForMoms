package com.example.dan.mommarket;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dan.mommarket.database.CategoryDataSource;
import com.example.dan.mommarket.database.ProductDataSource;
import com.example.dan.mommarket.database.SQLiteHelper;
import com.example.dan.mommarket.model.Feature;
import com.example.dan.mommarket.model.Product;
import com.example.dan.mommarket.model.ProductCategory;
import com.example.dan.mommarket.presenter.PresenterCatalog;
import com.example.dan.mommarket.presenter.PresenterCatalogImpl;
import com.example.dan.mommarket.view.ViewCatalog;
import com.example.dan.mommarket.view.ViewCatalogImpl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.deleteDatabase("mommarket.db");

        dbHelper = new SQLiteHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        insertDataToDB(db);
        ProductDataSource.setDatabase(this);
//        ProductDataSource productDataSource = new ProductDataSource(this);
//        List<Feature> features = new ArrayList<>();
//        features.add(new Feature(3, "aaa", "1"));
//        features.add(new Feature(4, "aaa", "1"));
//        List<Product> productList = productDataSource.getProductsByFeatures(features);
//        Log.i("asdf", "" + productList.size());
//        CategoryDataSource categoryDataSource = new CategoryDataSource(this);
//        List<ProductCategory> categoryList = categoryDataSource.getChildCategories(1);
//        Log.i("asdf", "" + categoryList.size());

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

     //   PresenterCatalog presenterCatalog = new PresenterCatalogImpl(this);
        ViewCatalogImpl viewCatalog = new ViewCatalogImpl();
        viewCatalog.setContext(this);
     //   presenterCatalog.setView(viewCatalog);
        ProductDataSource.getInstance().setDatabase(this);
        CategoryDataSource.getInstance().setDatabase(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, viewCatalog).commit();
     //   presenterCatalog.updateCatalog();


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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        if (getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        else
            super.onBackPressed();///change
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //replaceFragment(Fragment);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

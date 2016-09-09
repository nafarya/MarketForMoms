package com.example.dan.mommarket;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.dan.mommarket.database.AdviceDataSource;
import com.example.dan.mommarket.database.CategoryDataSource;
import com.example.dan.mommarket.database.OfferDataSource;
import com.example.dan.mommarket.database.ProductDataSource;
import com.example.dan.mommarket.database.SQLiteHelper;
import com.example.dan.mommarket.fragments.advice.AdviceDetailFragment;
import com.example.dan.mommarket.fragments.advice.AdviceListFragment;
import com.example.dan.mommarket.fragments.category.CategoryFragment;
import com.example.dan.mommarket.fragments.category.CategoryRootFragment;
import com.example.dan.mommarket.fragments.category.SubCategoryFragment;
import com.example.dan.mommarket.fragments.mainscreen.MainScreenFragment;
import com.example.dan.mommarket.fragments.order.OrderContactsFragment;
import com.example.dan.mommarket.fragments.order.OrderDeliveryFragment;
import com.example.dan.mommarket.fragments.order.OrderPaymentsFragment;
import com.example.dan.mommarket.fragments.product.ProductCardFragment;
import com.example.dan.mommarket.fragments.product.ProductListFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Navigator {

    SQLiteHelper dbHelper;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.deleteDatabase("mommarket.db");

        dbHelper = new SQLiteHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        insertDataToDB(db);
        ProductDataSource.setDatabase(this);

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
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ProductDataSource.getInstance().setDatabase(this);
        CategoryDataSource.getInstance().setDatabase(this);
        AdviceDataSource.getInstance().setDatabase(this);
        OfferDataSource.getInstance().setDatabase(this);


        MainScreenFragment mainScreenView = new MainScreenFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mainScreenView).commit();

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
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
        dbHelper.insertFakeData(db, this, R.raw.advice);
        dbHelper.insertFakeData(db, this, R.raw.image);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (getSupportFragmentManager().getBackStackEntryCount() > 1)
            getSupportFragmentManager().popBackStack();
        else {
            Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == android.R.id.home) {
            Toast.makeText(this, "asdsad", Toast.LENGTH_LONG).show();
            getSupportFragmentManager().popBackStack();
            ActionBar actionbar = getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(false);
            actionbar.setHomeButtonEnabled(false);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main_screen) {
            MainScreenFragment mainScreenFragment = new MainScreenFragment();
            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.fragment_container, mainScreenFragment)
                    .commit();

        } else if (id == R.id.nav_catalog) {
            CategoryRootFragment categoryRootFragment = new CategoryRootFragment();
            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.fragment_container, categoryRootFragment).
                    commit();

        } else if (id == R.id.nav_advices) {
            AdviceListFragment adviceListFragment = new AdviceListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, adviceListFragment)
                    .commit();

        } else if (id == R.id.nav_basket) {

        } else if (id == R.id.nav_checklist) {

        } else if (id == R.id.nav_my_orders) {
            this.navigateToOrder(1);
        } else if (id == R.id.nav_view) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void navigateToAdviceDetail(int adviceId) {
        AdviceDetailFragment adviceDetailFragment = new AdviceDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("AdviceId", adviceId);
        adviceDetailFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, adviceDetailFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void navigateToCategory(int categoryId, int childCount) {
        if (childCount != 0) {
            CategoryFragment categoryChildListView = new CategoryFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("ParentCategory", categoryId);
            categoryChildListView.setArguments(bundle);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, categoryChildListView)
                    .commit();

        } else {
            navigateToProductList(categoryId);
        }
    }

    @Override
    public void navigateToSubCategory(int categoryId, int childCount) {
        if (childCount != 0) {
            SubCategoryFragment categorySecondChildListView = new SubCategoryFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("ParentCategory", categoryId);
            categorySecondChildListView.setArguments(bundle);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, categorySecondChildListView)
                    .addToBackStack(null)
                    .commit();
        } else {
            navigateToProductList(categoryId);
        }
    }

    @Override
    public void navigateToProductList(int categoryId) {
        ProductListFragment ProductListView = new ProductListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("ParentCategory", categoryId);
        ProductListView.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, ProductListView)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void navigateToCatalog() {
        CategoryRootFragment categoryRootFragment = new CategoryRootFragment();
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragment_container, categoryRootFragment).
                addToBackStack(null).
                commit();
    }

    @Override
    public void navigateToProductCard(int productId) {
        ProductCardFragment productCardFragment = new ProductCardFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("ProductId", productId);
        productCardFragment.setArguments(bundle);
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragment_container, productCardFragment).
                addToBackStack(null).
                commit();
    }

    @Override
    public void navigateToOrder(int step) {
        switch (step) {
            case 1:
                OrderDeliveryFragment orderDeliveryFragment = new OrderDeliveryFragment();
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragment_container, orderDeliveryFragment).
                        addToBackStack(null).
                        commit();
                break;
            case 2:
                OrderContactsFragment orderContactsFragment = new OrderContactsFragment();
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragment_container, orderContactsFragment).
                        addToBackStack(null).
                        commit();
                break;
            case 3:
                OrderPaymentsFragment orderPaymentsFragment = new OrderPaymentsFragment();
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragment_container, orderPaymentsFragment).
                        addToBackStack(null).
                        commit();
                break;
        }
    }
}

package com.example.dan.mommarket;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dan.mommarket.database.AdviceDataSource;
import com.example.dan.mommarket.database.CartDataSource;
import com.example.dan.mommarket.database.CategoryDataSource;
import com.example.dan.mommarket.database.OfferDataSource;
import com.example.dan.mommarket.database.OfferItemDataSource;
import com.example.dan.mommarket.database.ProductDataSource;
import com.example.dan.mommarket.database.SQLiteHelper;
import com.example.dan.mommarket.database.ShopDataSource;
import com.example.dan.mommarket.fragments.advice.AdviceDetailFragment;
import com.example.dan.mommarket.fragments.advice.AdviceListFragment;
import com.example.dan.mommarket.fragments.cart.CartRootFragment;
import com.example.dan.mommarket.fragments.cart.OfferItemDialogFragment;
import com.example.dan.mommarket.fragments.category.CategoryFragment;
import com.example.dan.mommarket.fragments.category.CategoryRootFragment;
import com.example.dan.mommarket.fragments.category.SubCategoryFragment;
import com.example.dan.mommarket.fragments.delayed.DelayedListFragment;
import com.example.dan.mommarket.fragments.mainscreen.MainScreenFragment;
import com.example.dan.mommarket.fragments.order.OrderContactsFragment;
import com.example.dan.mommarket.fragments.order.OrderDeliveryFragment;
import com.example.dan.mommarket.fragments.order.OrderPaymentsFragment;
import com.example.dan.mommarket.fragments.order.OrderThankForPurchase;
import com.example.dan.mommarket.fragments.product.ProductCardFragment;
import com.example.dan.mommarket.fragments.product.ProductListFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Navigator {

    SQLiteHelper dbHelper;
    private ActionBarDrawerToggle toggle;
    private String previousTitle = "";

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
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(previousTitle);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("asdsa", String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                        Log.i("asdsa", "WE ARE HERE");
                        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
                        getSupportActionBar().setTitle("");
                    } else {
                        getSupportActionBar().setTitle(previousTitle);
                    }
                    getSupportFragmentManager().popBackStack();


                } else {
                    drawer.openDrawer(Gravity.LEFT, true);
                }
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ProductDataSource.getInstance().setDatabase(this);
        CategoryDataSource.getInstance().setDatabase(this);
        AdviceDataSource.getInstance().setDatabase(this);
        OfferDataSource.getInstance().setDatabase(this);
        ShopDataSource.getInstance().setDatabase(this);
        CartDataSource.getInstance().setDatabase(this);
        OfferItemDataSource.getInstance().setDatabase(this);

        navigateToMainScreen(); ///this is bullshit, but if change all 4 rows for 1 "navigateToMainScreen() and pick
        navigateToDelayed();    ///Каталог from navigation drawer, home button will dissapear
        clearBackStack();       ///it's too late now, so I will fix it later
    }



    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    void insertDataToDB(SQLiteDatabase db) {
        dbHelper.insertFakeData(db, this, R.raw.check_list);
        dbHelper.insertFakeData(db, this, R.raw.feature);
      //  dbHelper.insertFakeData(db, this, R.raw.offer_item);
        dbHelper.insertFakeData(db, this, R.raw.offer);
        dbHelper.insertFakeData(db, this, R.raw.offer1);
        dbHelper.insertFakeData(db, this, R.raw.offer2);
        dbHelper.insertFakeData(db, this, R.raw.offer3);
        dbHelper.insertFakeData(db, this, R.raw.offer4);
        dbHelper.insertFakeData(db, this, R.raw.product);
        dbHelper.insertFakeData(db, this, R.raw.product1);
        dbHelper.insertFakeData(db, this, R.raw.product_category);
        dbHelper.insertFakeData(db, this, R.raw.product_feature);
        dbHelper.insertFakeData(db, this, R.raw.shop);
        dbHelper.insertFakeData(db, this, R.raw.advice);
        dbHelper.insertFakeData(db, this, R.raw.image);
        dbHelper.insertFakeData(db, this, R.raw.image1);
    }

    @Override
    public void onBackPressed() {
        int backStackSize = getSupportFragmentManager().getBackStackEntryCount();
        if (backStackSize == 1) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            getSupportActionBar().setTitle("");
        } else {
            if (backStackSize != 0) {
                getSupportActionBar().setTitle(previousTitle);
            }
        }
        if (backStackSize == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main_screen) {
            navigateToMainScreen();
        } else if (id == R.id.nav_catalog) {
            clearBackStack();
            navigateToCatalog();
        } else if (id == R.id.nav_advices) {
            clearBackStack();
            navigateToAdviceList();
        } else if (id == R.id.nav_cart) {
            clearBackStack();
            navigateToCart();
        } else if (id == R.id.nav_checklist) {
            clearBackStack();
            navigateToDelayed();
        }/* else if (id == R.id.nav_my_orders) {
            clearBackStack();
            navigateToOrder(1);
        }*/ else if (id == R.id.nav_view) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void clearBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
        navigateToMainScreen();
    }

    @Override
    public void navigateToAdviceDetail(int adviceId) {
        AdviceDetailFragment adviceDetailFragment = new AdviceDetailFragment();
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        previousTitle = (String) getSupportActionBar().getTitle();
        getSupportActionBar().setTitle("Советы");
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
        previousTitle = (String) getSupportActionBar().getTitle();
        getSupportActionBar().setTitle("Каталог");
        if (childCount != 0) {
            CategoryFragment categoryChildListView = new CategoryFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("ParentCategory", categoryId);
            categoryChildListView.setArguments(bundle);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, categoryChildListView)
                    .addToBackStack(null)
                    .commit();

        } else {
            navigateToProductList(categoryId);
        }
    }

    @Override
    public void navigateToSubCategory(int categoryId, int childCount) {
        previousTitle = (String) getSupportActionBar().getTitle();
        getSupportActionBar().setTitle("Каталог");
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
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        previousTitle = (String) getSupportActionBar().getTitle();
        getSupportActionBar().setTitle("Каталог");
        CategoryRootFragment categoryRootFragment = new CategoryRootFragment();
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragment_container, categoryRootFragment).
                addToBackStack(null).
                commit();
    }

    @Override
    public void navigateToProductCard(int productId) {
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        previousTitle = (String) getSupportActionBar().getTitle();
        getSupportActionBar().setTitle("");
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
        getSupportActionBar().setTitle("Оформление заказа");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        switch (step) {
            case 1:
                previousTitle = (String) getSupportActionBar().getTitle();
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
            case 4:
                OrderThankForPurchase orderThankForPurchase = new OrderThankForPurchase();
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragment_container, orderThankForPurchase).
                        addToBackStack(null).
                        commit();
                break;
        }
    }

    @Override
    public void navigateToCart() {
//        clearBackStack();
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        previousTitle = (String) getSupportActionBar().getTitle();
        getSupportActionBar().setTitle("Корзина");
        CartRootFragment cartRootFragment = new CartRootFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("CartType", 1);
        cartRootFragment.setArguments(bundle);
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragment_container, cartRootFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void navigateToMainScreen() {
        previousTitle = (String) getSupportActionBar().getTitle();
        getSupportActionBar().setTitle("");
        MainScreenFragment mainScreenFragment = new MainScreenFragment();
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragment_container, mainScreenFragment)
                .commit();
    }

    @Override
    public void navigateToAdviceList() {
        previousTitle = (String) getSupportActionBar().getTitle();
        getSupportActionBar().setTitle("Советы");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        AdviceListFragment adviceListFragment = new AdviceListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, adviceListFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showOfferItemDialog(int offerItemId) {
        OfferItemDialogFragment offerItemDialogFragment = new OfferItemDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("OfferItemId", offerItemId);
        offerItemDialogFragment.setArguments(bundle);
        offerItemDialogFragment.show(getSupportFragmentManager(), "dialog_fragment");
    }

    @Override
    public void navigateToDelayed() {
        clearBackStack();
        previousTitle = (String) getSupportActionBar().getTitle();
        getSupportActionBar().setTitle("Отложенное");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DelayedListFragment delayedListFragment = new DelayedListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, delayedListFragment)
                .addToBackStack(null)
                .commit();
    }
}

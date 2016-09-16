package com.example.dan.mommarket.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dan.mommarket.model.Feature;
import com.example.dan.mommarket.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dan on 21.08.16.
 */

public class ProductDataSource {
    private static SQLiteDatabase database;
    private static SQLiteHelper dbHelper;

    private static ProductDataSource instance;

    public static synchronized ProductDataSource getInstance() {
        if (instance == null) {
            instance = new ProductDataSource();
        }
        return instance;
    }

    public static void setDatabase(Context context) {
        dbHelper = new SQLiteHelper(context);
        return;
    }

    /**
     * Get product by Id
     *
     * @param productId
     * @return
     */
    public static Product getProductById(int productId) {
        database = dbHelper.getReadableDatabase();
        Product product = null;
        Cursor productCursor = database.rawQuery("select " +
                " p." + Contract.ProductDB.ID +
                " ,p." + Contract.ProductDB.NAME +
                " ,min(o." + Contract.OfferDB.PRICE + ")" +
                " ,p." + Contract.ProductDB.DESCRIPTION +
                " ,c." + Contract.ProductCategoryDB.ID + " CATEGORY_ID" +
                " ,c." + Contract.ProductCategoryDB.NAME + " CATEGORY_NAME" +
                " ,group_concat(i." + Contract.ImageDB.URL + ") URL" +
                " ,max(cpf." + Contract.ProductFeatureDB.VALUE + ") " +
                " from " + Contract.ProductDB.TABLE + " p" +
                " left join " + Contract.ProductCategoryDB.TABLE + " c on c." + Contract.ProductCategoryDB.ID + "= p." + Contract.ProductDB.CATEGORY_ID + " " +
                " left join " + Contract.ProductFeatureDB.TABLE + " cpf on cpf." + Contract.ProductFeatureDB.FEATURE_ID + "= c." + Contract.ProductCategoryDB.CARD_FEATURE_ID + " " +
                " AND cpf." + Contract.ProductFeatureDB.PRODUCT_ID + " = p." + Contract.ProductDB.ID +
                " left join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.PRODUCT_ID + "=p." + Contract.ProductDB.ID +
                " left join " + Contract.ImageDB.TABLE + " i on i." + Contract.ImageDB.ITEM_ID + "=p." + Contract.ProductDB.ID +
                " Where p." + Contract.ProductDB.ID + "=?" +
                " group by p." + Contract.ProductDB.ID + ";", new String[]{Integer.toString(productId)});
        productCursor.moveToFirst();
        if (!productCursor.isAfterLast()) {
            product = productCursorToProduct(productCursor);
        }
        productCursor.close();
        return product;
    }

    public static List<Product> getAllProducts() {
        database = dbHelper.getReadableDatabase();
        List<Product> productList = new ArrayList<>();
        Cursor productCursor = database.rawQuery("select " +
                " p." + Contract.ProductDB.ID +
                " ,p." + Contract.ProductDB.NAME +
                " ,min(o." + Contract.OfferDB.PRICE + ") " + Contract.OfferDB.PRICE +
                " ,p." + Contract.ProductDB.DESCRIPTION +
                " ,c." + Contract.ProductCategoryDB.ID + " CATEGORY_ID" +
                " ,c." + Contract.ProductCategoryDB.NAME + " CATEGORY_NAME" +
                " ,min(i." + Contract.ImageDB.URL + ") URL" +
                " ,max(cpf." + Contract.ProductFeatureDB.VALUE + ") " +
                " from " + Contract.ProductDB.TABLE + " p" +
                " left join " + Contract.ProductCategoryDB.TABLE + " c on c." + Contract.ProductCategoryDB.ID + "= p." + Contract.ProductDB.CATEGORY_ID + " " +
                " left join " + Contract.ProductFeatureDB.TABLE + " cpf on cpf." + Contract.ProductFeatureDB.FEATURE_ID + "= c." + Contract.ProductCategoryDB.CARD_FEATURE_ID + " " +
                " AND cpf." + Contract.ProductFeatureDB.PRODUCT_ID + " = p." + Contract.ProductDB.ID +
                " left join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.PRODUCT_ID + "=p." + Contract.ProductDB.ID +
                " left join " + Contract.ImageDB.TABLE + " i on i." + Contract.ImageDB.ITEM_ID + "=p." + Contract.ProductDB.ID +
                " group by p." + Contract.ProductDB.ID + ";", null);

        productCursor.moveToFirst();
        while (!productCursor.isAfterLast()) {
            productList.add(productCursorToProduct(productCursor));
            productCursor.moveToNext();
        }
        productCursor.close();
        return productList;
    }

    /**
     * if (listId == 0) return cartList
     * if (listId == 1) return delayedList
     *
     * @param
     * @return
     */
    public static List<Product> getListProducts(int listId) {
        database = dbHelper.getReadableDatabase();
        List<Product> productList = new ArrayList<>();
        Cursor productCursor = database.rawQuery("select " +
                " p." + Contract.ProductDB.ID +
                " ,p." + Contract.ProductDB.NAME +
                " ,o." + Contract.OfferDB.PRICE +
                " ,p." + Contract.ProductDB.DESCRIPTION +
                " ,c." + Contract.ProductCategoryDB.ID + " CATEGORY_ID" +
                " ,c." + Contract.ProductCategoryDB.NAME + " CATEGORY_NAME" +
                " ,min(i." + Contract.ImageDB.URL + ") URL" +
                " ,max(cpf." + Contract.ProductFeatureDB.VALUE + ") " +
                " from " + Contract.ProductDB.TABLE + " p" +
                " left join " + Contract.ProductCategoryDB.TABLE + " c on c." + Contract.ProductCategoryDB.ID + "= p." + Contract.ProductDB.CATEGORY_ID + " " +
                " left join " + Contract.ProductFeatureDB.TABLE + " cpf on cpf." + Contract.ProductFeatureDB.FEATURE_ID + "= c." + Contract.ProductCategoryDB.CARD_FEATURE_ID + " " +
                " AND cpf." + Contract.ProductFeatureDB.PRODUCT_ID + " = p." + Contract.ProductDB.ID +
                " left join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.PRODUCT_ID + "=p." + Contract.ProductDB.ID +
                " left join " + Contract.OfferItemDB.TABLE + " lo on lo." + Contract.OfferItemDB.OFFER_ID + "=o." + Contract.OfferDB.ID +
                " left join " + Contract.ImageDB.TABLE + " i on i." + Contract.ImageDB.ITEM_ID + "=p." + Contract.ProductDB.ID +
                " Where lo." + Contract.OfferItemDB.LIST_ID + "=?" +
                " group by p." + Contract.ProductDB.ID + ";", new String[]{Integer.toString(listId)});
        productCursor.moveToFirst();
        while (!productCursor.isAfterLast()) {
            productList.add(productCursorToProduct(productCursor));
            productCursor.moveToNext();
        }
        productCursor.close();
        return productList;
    }

    public static List<Product> getListDelayedProducts(int listId) {
        database = dbHelper.getReadableDatabase();
        List<Product> productList = new ArrayList<>();
        Cursor productCursor = database.rawQuery("select " +
                " p." + Contract.ProductDB.ID +
                " ,p." + Contract.ProductDB.NAME +
                " ,o." + Contract.OfferDB.PRICE +
                " ,p." + Contract.ProductDB.DESCRIPTION +
                " ,c." + Contract.ProductCategoryDB.ID + " CATEGORY_ID" +
                " ,c." + Contract.ProductCategoryDB.NAME + " CATEGORY_NAME" +
                " ,min(i." + Contract.ImageDB.URL + ") URL" +
                " ,max(cpf." + Contract.ProductFeatureDB.VALUE + ") " +
                " ,max(lo." + Contract.OfferItemDB.COUNT + ") " +
                " from " + Contract.ProductDB.TABLE + " p" +
                " left join " + Contract.ProductCategoryDB.TABLE + " c on c." + Contract.ProductCategoryDB.ID + "= p." + Contract.ProductDB.CATEGORY_ID + " " +
                " left join " + Contract.ProductFeatureDB.TABLE + " cpf on cpf." + Contract.ProductFeatureDB.FEATURE_ID + "= c." + Contract.ProductCategoryDB.CARD_FEATURE_ID + " " +
                " AND cpf." + Contract.ProductFeatureDB.PRODUCT_ID + " = p." + Contract.ProductDB.ID +
                " left join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.PRODUCT_ID + "=p." + Contract.ProductDB.ID +
                " left join " + Contract.OfferItemDB.TABLE + " lo on lo." + Contract.OfferItemDB.PRODUCT_ID + "=p." + Contract.ProductDB.ID +
                " left join " + Contract.ImageDB.TABLE + " i on i." + Contract.ImageDB.ITEM_ID + "=p." + Contract.ProductDB.ID +
                " Where lo." + Contract.OfferItemDB.LIST_ID + "=?" +
                " group by p." + Contract.ProductDB.ID + ";", new String[]{Integer.toString(listId)});
        productCursor.moveToFirst();
        while (!productCursor.isAfterLast()) {
            productList.add(productCursorToProduct(productCursor));
            productCursor.moveToNext();
        }
        productCursor.close();
        return productList;
    }

    public static List<Product> getListProductsByCategoryId(int categoryId) {
        database = dbHelper.getReadableDatabase();
        List<Product> productList = new ArrayList<>();
        Cursor productCursor = database.rawQuery("select " +
                " p." + Contract.ProductDB.ID +
                " ,p." + Contract.ProductDB.NAME +
                " ,min(o." + Contract.OfferDB.PRICE + ")" +
                " ,p." + Contract.ProductDB.DESCRIPTION +
                " ,c." + Contract.ProductCategoryDB.ID + " CATEGORY_ID" +
                " ,c." + Contract.ProductCategoryDB.NAME + " CATEGORY_NAME" +
                " ,min(i." + Contract.ImageDB.URL + ") URL" +
                " ,max(cpf." + Contract.ProductFeatureDB.VALUE + ") " +
                " ,max(lo." + Contract.OfferItemDB.COUNT + ") " +
                " from " + Contract.ProductDB.TABLE + " p" +
                " left join " + Contract.ProductCategoryDB.TABLE + " c on c." + Contract.ProductCategoryDB.ID + "= p." + Contract.ProductDB.CATEGORY_ID + " " +
                " left join " + Contract.ProductFeatureDB.TABLE + " cpf on cpf." + Contract.ProductFeatureDB.FEATURE_ID + "= c." + Contract.ProductCategoryDB.CARD_FEATURE_ID + " " +
                " AND cpf." + Contract.ProductFeatureDB.PRODUCT_ID + " = p." + Contract.ProductDB.ID +
                " left join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.PRODUCT_ID + "=p." + Contract.ProductDB.ID +
                " left join " + Contract.ImageDB.TABLE + " i on i." + Contract.ImageDB.ITEM_ID + "=p." + Contract.ProductDB.ID +
                " left join " + Contract.OfferItemDB.TABLE + " lo on lo." + Contract.OfferItemDB.PRODUCT_ID + "=p." + Contract.ProductDB.ID +
                " Where p." + Contract.ProductDB.CATEGORY_ID + "=?" +
                " group by p." + Contract.ProductDB.ID + ";", new String[]{Integer.toString(categoryId)});
        productCursor.moveToFirst();
        while (!productCursor.isAfterLast()) {
            productList.add(productCursorToProduct(productCursor));
            productCursor.moveToNext();
        }
        productCursor.close();
        return productList;
    }

    public static List<Product> getProductsByFeatures(List<Feature> features) {
        database = dbHelper.getReadableDatabase();
        String[] whereClauseArray = new String[features.size() * 2];
        String whereClause = "";
        int i = 0;
        for (Feature x : features) {
            if (whereClause != "") whereClause += " OR ";
            whereClause += "pf." + Contract.ProductFeatureDB.FEATURE_ID + "=? AND pf." + Contract.ProductFeatureDB.VALUE + "=?";
            whereClauseArray[i++] = Integer.toString(x.getId());
            whereClauseArray[i++] = x.getValue();
        }
        List<Product> productList = new ArrayList<>();
        Cursor productCursor = database.rawQuery("select " +
                " p." + Contract.ProductDB.ID +
                " ,p." + Contract.ProductDB.NAME +
                " ,o." + Contract.OfferDB.PRICE +
                " ,p." + Contract.ProductDB.DESCRIPTION +
                " ,c." + Contract.ProductCategoryDB.ID + " CATEGORY_ID" +
                " ,c." + Contract.ProductCategoryDB.NAME + " CATEGORY_NAME" +
                " ,min(i." + Contract.ImageDB.URL + ") URL" +
                " ,max(cpf." + Contract.ProductFeatureDB.VALUE + ") " +
                " from " + Contract.ProductDB.TABLE + " p" +
                " left join " + Contract.ProductCategoryDB.TABLE + " c on c." + Contract.ProductCategoryDB.ID + "= p." + Contract.ProductDB.CATEGORY_ID + " " +
                " left join " + Contract.ProductFeatureDB.TABLE + " cpf on cpf." + Contract.ProductFeatureDB.FEATURE_ID + "= c." + Contract.ProductCategoryDB.CARD_FEATURE_ID + " " +
                " AND cpf." + Contract.ProductFeatureDB.PRODUCT_ID + " = p." + Contract.ProductDB.ID +
                " left join " + Contract.OfferDB.TABLE + " o on o." + Contract.OfferDB.PRODUCT_ID + "=p." + Contract.ProductDB.ID +
                " left join " + Contract.OfferItemDB.TABLE + " lo on lo." + Contract.OfferItemDB.OFFER_ID + "=o." + Contract.OfferDB.ID +
                " left join " + Contract.ProductFeatureDB.TABLE + " pf on pf." + Contract.ProductFeatureDB.PRODUCT_ID + "=p." + Contract.ProductDB.ID +
                " left join " + Contract.ImageDB.TABLE + " i on i." + Contract.ImageDB.ITEM_ID + "=i." + Contract.ProductDB.ID +
                " Where " + whereClause +
                " group by p." + Contract.ProductDB.ID + " having count(p." + Contract.ProductDB.ID + ")=" + features.size() + ";", whereClauseArray);
        productCursor.moveToFirst();
        while (!productCursor.isAfterLast()) {
            productList.add(productCursorToProduct(productCursor));
            productCursor.moveToNext();
        }
        productCursor.close();
        return productList;
    }

    private static Product productCursorToProduct(Cursor productCursor) {
        Product product = null;
        product = new Product(productCursor.getInt(0),
                productCursor.getString(1),
                productCursor.getFloat(2),
                productCursor.getString(3),
                productCursor.getInt(4),
                productCursor.getString(5),
                productCursor.getString(6) == null ? null : Arrays.asList((productCursor.getString(6)).split(",")),
                productCursor.getColumnCount() < 8 ? null : productCursor.getString(7),
                productCursor.getColumnCount() < 9 ? 0 : productCursor.getInt(8));
        return product;
    }

    public static void deleteProductFromList(int productId, int listId) {
        database = dbHelper.getReadableDatabase();
        database.delete(Contract.OfferItemDB.TABLE,
                Contract.OfferItemDB.LIST_ID + " = ? AND " + Contract.OfferItemDB.PRODUCT_ID + " = ?",
                new String[]{String.valueOf(listId), String.valueOf(productId)});
    }

    public static void addProductToList(int productId, int listId) {
        database = dbHelper.getReadableDatabase();
        ContentValues cv = new ContentValues();
        Cursor cursor = database.query(Contract.OfferItemDB.TABLE,
                new String[]{Contract.OfferItemDB.ID, Contract.OfferItemDB.COUNT},
                Contract.OfferItemDB.LIST_ID + " = ? AND " + Contract.OfferItemDB.PRODUCT_ID + " = ?",
                new String[]{String.valueOf(listId), String.valueOf(productId)},
                null,
                null,
                null);
        cursor.moveToFirst();
        if (cursor.isAfterLast()) {
            //нет записей
            cv.put(Contract.OfferItemDB.PRODUCT_ID, productId);
            cv.put(Contract.OfferItemDB.LIST_ID, listId);
            cv.put(Contract.OfferItemDB.COUNT, "1");
            database.insert(Contract.OfferItemDB.TABLE, null, cv);
        }
    }
}

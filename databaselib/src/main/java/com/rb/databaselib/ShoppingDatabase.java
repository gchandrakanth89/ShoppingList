package com.rb.databaselib;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.rb.pojo.ShoppingList;
import com.rb.pojo.ShoppingListItem;
import com.rb.pojoimpl.ShoppingListImpl;
import com.rb.pojoimpl.ShoppingListItemImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pervacio on 02-07-2017.
 */

public class ShoppingDatabase extends SQLiteOpenHelper {

    private static final String USER_TABLE_NAME = "USER_TABLE";
    private static final String USER_ID = "_id";
    private static final String USER_USER_ID = "user_id";
    private static final String USER_NAME = "name";
    private static final String USER_PH_NUM = "phone_number";
    private static final String USER_EMAIL_ID = "email_id";


    private static final String ITEM_TABLE_NAME = "ITEM_TABLE";
    private static final String ITEM_ID = "_id";
    private static final String ITEM_NAME = "name";
    private static final String ITEM_IMAGE_URL = "url";
    private static final String ITEM_PRICE = "price";
    private static final String ITEM_BRAND = "brand";
    private static final String ITEM_QUANTITY = "quantity";
    private static final String ITEM_USER_ID = "user_id";


    private static final String SHOPPING_LIST_TABLE = "SHOPPING_LIST_TABLE";
    private static final String SHOPPING_LIST_ID = "_id";
    private static final String SHOPPING_LIST_NAME = "name";
    private static final String SHOPPING_LIST_USER_ID = "user_id";


    private static final String SL_ITEMS_TABLE = "shopping_list_items";
    private static final String SL_ID = "_id";
    private static final String SL_ITEMS_ID = "item_id";
    private static final String SL_LIST_ID = "shopping_list_id";
    private static final String SL_QUANTITY = "quantity";
    private static final String SL_PRICE = "price";
    private static final String SL_BRAND = "brand";
    private static final String SL_COMMENTS = "comments";


    private static final String TAG = ShoppingDatabase.class.getSimpleName();
    private static final String DB_NAME = "shopping_list.sqlite";

    private Context context;


    public ShoppingDatabase(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d(TAG, "onCreate");
        String userTableStmt = "create table " + USER_TABLE_NAME + "(" +
                USER_ID + " INTEGER PRIMARY KEY, " +
                USER_USER_ID + " TEXT, " +
                USER_NAME + " TEXT, " +
                USER_PH_NUM + " TEXT, " +
                USER_EMAIL_ID + " TEXT)";


        String itemTableStmt = "create table " + ITEM_TABLE_NAME + "(" +
                ITEM_ID + " INTEGER PRIMARY KEY, " +
                ITEM_NAME + " TEXT, " +
                ITEM_IMAGE_URL + " TEXT, " +
                ITEM_PRICE + " NUMBER, " +
                ITEM_BRAND + " TEXT, " +
                ITEM_QUANTITY + " NUMBER, " + ITEM_USER_ID + " INTEGER," +
                " FOREIGN KEY(" + ITEM_USER_ID + ") REFERENCES " + USER_TABLE_NAME + "(" + USER_ID + "))";

        String shoppingListStmt = "create table " + SHOPPING_LIST_TABLE + "(" +
                SHOPPING_LIST_ID + " INTEGER PRIMARY KEY, " +
                SHOPPING_LIST_NAME + " TEXT, " +
                SHOPPING_LIST_USER_ID + " INTEGER, " +
                "FOREIGN KEY(" + SHOPPING_LIST_USER_ID + ") REFERENCES " + USER_TABLE_NAME + "(" + USER_ID + ") )";

        String shoppingListItemsStmt = "create table " + SL_ITEMS_TABLE + "(" +
                SL_ID + " INTEGER PRIMARY KEY, " +
                SL_ITEMS_ID + " INTEGER, " +
                SL_LIST_ID + " INTEGER, " +
                SL_QUANTITY + " INTEGER, " +
                SL_PRICE + " INTEGER, " +
                SL_BRAND + " TEXT, " +
                SL_COMMENTS + " TEXT, " +
                "FOREIGN KEY(" + SL_ITEMS_ID + ") REFERENCES " + ITEM_TABLE_NAME + "(" + ITEM_ID + "), " +
                "FOREIGN KEY(" + SL_LIST_ID + ") REFERENCES " + SHOPPING_LIST_TABLE + "(" + SHOPPING_LIST_ID + "))";

        db.execSQL(userTableStmt);
        db.execSQL(itemTableStmt);
        db.execSQL(shoppingListStmt);
        db.execSQL(shoppingListItemsStmt);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void addUser() {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID, 1);
        contentValues.put(USER_USER_ID, "abc123");
        contentValues.put(USER_NAME, "abc");
        contentValues.put(USER_PH_NUM, "123456789");
        contentValues.put(USER_EMAIL_ID, "abc@gmail.com");
        long insert = database.insert(USER_TABLE_NAME, null, contentValues);
        Log.d(TAG, "Insert success " + insert);
        database.close();
    }

    public long addItem(String name, String url, double price, String brand, int quantity, long userId) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues(5);
        values.put(ITEM_NAME, name);
        values.put(ITEM_IMAGE_URL, url);
        values.put(ITEM_PRICE, price);
        values.put(ITEM_BRAND, brand);
        values.put(ITEM_QUANTITY, quantity);
        values.put(ITEM_USER_ID, userId);
        long id = database.insert(ITEM_TABLE_NAME, null, values);
        database.close();
        return id;
    }

    public long createShoppingList(String name, long userId) {
        ContentValues cv = new ContentValues(2);
        cv.put(SHOPPING_LIST_NAME, name);
        cv.put(SHOPPING_LIST_USER_ID, userId);
        SQLiteDatabase database = getWritableDatabase();
        long id = database.insert(SHOPPING_LIST_TABLE, null, cv);
        database.close();
        return id;
    }

    public List<ShoppingList> getShoppingList() {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(SHOPPING_LIST_TABLE, null, null, null, null, null, null);
        List<ShoppingList> list = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            long id = cursor.getLong(0);
            String name = cursor.getString(1);
            long userId = cursor.getLong(2);
            list.add(new ShoppingListImpl(id, userId, name));
        }
        cursor.close();
        database.close();
        return list;
    }

    public long addItemToShoppingList(long shoppingListId, long itemId, int quantity, double price, String brand, String comment) {

        ContentValues cv = new ContentValues(2);
        cv.put(SL_ITEMS_ID, itemId);
        cv.put(SL_LIST_ID, shoppingListId);
        cv.put(SL_QUANTITY, quantity);
        cv.put(SL_PRICE, price);
        cv.put(SL_BRAND, brand);
        cv.put(SL_COMMENTS, comment);
        SQLiteDatabase database = getWritableDatabase();
        long id = database.insert(SL_ITEMS_TABLE, null, cv);
        database.close();
        return id;
    }

    public List<ShoppingListItem> getShoppingListItems(long shoppingListId) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(SL_ITEMS_TABLE, null, SL_LIST_ID + " = ?", new String[]{shoppingListId + ""}, null, null, null);
        List<ShoppingListItem> list = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            long id = cursor.getLong(0);
            long itemId = cursor.getLong(1);
            long listId = cursor.getLong(2);
            int quantity = cursor.getInt(3);
            double price = cursor.getDouble(4);
            String brand = cursor.getString(5);
            String comments = cursor.getString(6);
            list.add(new ShoppingListItemImpl(id, itemId, listId, quantity, price, brand, comments));
        }
        cursor.close();
        database.close();
        return list;
    }

    public void exportDB() {
        File sd = Environment.getExternalStorageDirectory();
        File data = Environment.getDataDirectory();
        FileChannel source = null;
        FileChannel destination = null;
        String currentDBPath = "/data/" + "com.rb.shoppinglist" + "/databases/" + DB_NAME;
        String backupDBPath = DB_NAME;
        File currentDB = new File(data, currentDBPath);
        File backupDB = new File(sd, backupDBPath);
        try {
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();
            Toast.makeText(context, "DB Exported!", Toast.LENGTH_LONG).show();
            Log.d(TAG, "DB path " + backupDB.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void populateDummyData() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("database", Context.MODE_PRIVATE);
        boolean populated = sharedPreferences.getBoolean("populated", false);

        if (populated) {
            return;
        }

        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean("populated", true);
        edit.commit();

        addUser();
        long item1 = addItem("Soap", "url1", 20.0, "Santoor", 5, 1);
        long item2 = addItem("Paste", "url2", 20.0, "Colgate", 5, 1);
        long item3 = addItem("Brush", "url3", 20.0, "Ajay-Quest", 5, 1);
        long item4 = addItem("Fairness cream", "url4", 20.0, "Fair & Lovely", 5, 1);
        long item5 = addItem("Face Powder", "url5", 20.0, "Z", 5, 1);

        Log.d(TAG, "populateDummyData " + item1);

        long shoppingList1 = createShoppingList("Main shopping list", 1);
        long shoppingList2 = createShoppingList("Sub shopping list", 1);

        addItemToShoppingList(shoppingList1, item1, 10, 20.25, "Santoor", "abcd");
        addItemToShoppingList(shoppingList1, item2, 10, 20.25, "Santoor", "abcd");
        addItemToShoppingList(shoppingList1, item3, 10, 20.25, "Santoor", "abcd");
        addItemToShoppingList(shoppingList2, item4, 10, 20.25, "Santoor", "abcd");
        addItemToShoppingList(shoppingList2, item5, 10, 20.25, "Santoor", "abcd");

    }

}

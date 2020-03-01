package com.example.shoppingsafari;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productDB.db";
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTNAME = "productname";
    public static final String COLUMN_SHELF = "shelf";
    private String data="";
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int
            version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCTNAME + " TEXT, " + COLUMN_SHELF +  " INTEGER "+
                ");";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }
    public void addProduct(myproduct product){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.get_productname());
        values.put(COLUMN_SHELF, product.getShelf());
        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_PRODUCTS, null, values);

    }
    public ArrayList databaseToString(){
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";
        Cursor res = db.rawQuery( "select * from "+TABLE_PRODUCTS, null );
        res.moveToFirst();
        while(res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex("name")));
            res.moveToNext();
        }
        return array_list;
    }
    public String findData(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_PRODUCTS, null);
        String res="";
        if (c.moveToFirst()){
            do {
                // Passing values
                String column1 = c.getString(0);
                String column2 = c.getString(1);
                String column3 = c.getString(2);
                String prod = "S.No: "+column1 + "\nProduct: " + column2 + "\nShelf Number: " + column3 + "\n"
                        +"-----------------------\n";
                // details.append(prod);
                res = res+prod;
                // Do something Here with values
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        data=res;
        return res;
    }
    public ArrayList<String> findshelves()
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT DISTINCT shelf FROM "+TABLE_PRODUCTS, null);
        ArrayList<String>all=new ArrayList<>();
        if (c.moveToFirst()){
            do {

                String column3 = c.getString(0);;
                all.add(column3);
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        for(int i=0;i<all.size();i++)
            Log.e("yo",all.get(i));
        return all;
    }

    public void deleteProduct(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE 1");
        onUpgrade(db,1,2);
        db.close();
    }

}
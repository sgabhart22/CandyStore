package com.sgabhart.candystore;

/**
 * Created by Admin on 9/20/2017.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "candyDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CANDY = "candy";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PRICE = "price";

    public DatabaseManager(Context cx){
        super(cx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        String sqlCreate = "create table " + TABLE_CANDY + "( " + ID +
                " integer primary key autoincrement" + NAME + " text, " +
                PRICE + "  real )";

        db.execSQL(sqlCreate);
    } // onCreate

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists " + TABLE_CANDY);
        onCreate(db);
    } // onUpgrade

    public void insert(Candy candy){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_CANDY + " values(null, '" +
                candy.getName() + "', '" + candy.getPrice() + "' )";

        db.execSQL(sqlInsert);
        db.close();
    } // insert

    public void deleteById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " + TABLE_CANDY + " where " +
                ID + " = " + id;

        db.execSQL(sqlDelete);
        db.close();
    } // deleteById

    public void updateById(int id, String name, double price){
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlUpdate = "update " + TABLE_CANDY + " set " + NAME +
                " = '" + name + "', " + PRICE + " = '" + price + "'" +
                " where " + ID + " = " + id;

        db.execSQL(sqlUpdate);
        db.close();
    } // updateById

    public ArrayList<Candy> selectAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Candy> candies = new ArrayList<>();

        String sqlQuery = "select * from " + TABLE_CANDY;
        Cursor cursor = db.rawQuery(sqlQuery, null);

        while(cursor.moveToNext()) {
            Candy currentCandy = new Candy(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getDouble(2));
            candies.add(currentCandy);
        }

        db.close();
        return candies;
    } // selectAll

    public Candy selectById(int id){
        String sqlQuery = "select * from " + TABLE_CANDY +
                " where " + ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        Candy candy = null;
        if(cursor.moveToFirst()){
            candy = new Candy(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                    cursor.getDouble(2));
        }

        return candy;
    }
}

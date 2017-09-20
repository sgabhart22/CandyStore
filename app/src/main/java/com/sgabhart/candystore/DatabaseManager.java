package com.sgabhart.candystore;

/**
 * Created by Admin on 9/20/2017.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        
    }
}

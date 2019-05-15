package com.ninositsolution.inveleapp.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CartDatabase extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "cart";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "cart_table";
    private static final String KEY_PARENT = "KEY_PARENT";
    private static final String KEY_CHILD = "KEY_CHILD";
    private static final String KEY_VALUE = "KEY_VALUE";
    private static final String KEY_AMOUNT = "KEY_AMOUNT";

    private static final String CREATE_CALL_TABLE = " CREATE TABLE "+ TABLE_NAME + " (" +
            KEY_PARENT + " INTEGER NOT NULL, " + KEY_CHILD + " INTEGER NOT NULL, " + KEY_VALUE + " INTEGER NOT NULL, " +
            KEY_AMOUNT + " TEXT NOT NULL );" ;

    public CartDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private SQLiteDatabase db;

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_CALL_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME + "'");
        onCreate(db);

    }

    public long insertValues(int parent, int child, int value)
    {
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_PARENT, parent);
        values.put(KEY_CHILD, child);
        values.put(KEY_VALUE, value);
        values.put(KEY_AMOUNT, " ");

        return db.insert(TABLE_NAME, null, values);
    }

    public void deleteValues()
    {
        db.execSQL("delete from "+ TABLE_NAME);
        db.close();
    }

    public long updateAmount(int parent, int child, int amount)
    {
        String condition = KEY_PARENT+" = "+parent+" AND "+KEY_CHILD+" = "+child;

        ContentValues values = new ContentValues();

        values.put(KEY_AMOUNT, amount);

        return (long) db.update(TABLE_NAME, values, condition, null);
    }


}

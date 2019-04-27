package com.ninositsolution.inveleapp.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ninositsolution.inveleapp.search.SearchKeywordModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseInvele extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "Invele";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "SearchHistory";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    private static final String CREATE_CALL_TABLE = " CREATE TABLE "+ TABLE_NAME + " (" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_NAME + " TEXT NOT NULL );" ;

    private SQLiteDatabase db;

    public DatabaseInvele(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_CALL_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME + "'");
        onCreate(db);

    }

    public long addSearchHistory(String value)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, value);

        return db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public List<SearchKeywordModel> getAllSearchHistory()
    {
        List<SearchKeywordModel> modelList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst())
        {
            do {

                SearchKeywordModel model = new SearchKeywordModel();

                String id = c.getString(c.getColumnIndex(KEY_ID));
                String name = c.getString(c.getColumnIndex(KEY_NAME));

                model.setId(Integer.parseInt(id));
                model.setName(name);

                modelList.add(model);

            } while (c.moveToNext());
        }

        c.close();

        return modelList;
    }

    public void deleteKeyword(int id)
    {
        db = this.getWritableDatabase();

        db.execSQL("DELETE FROM SearchHistory WHERE id = "+id);
        db.close();
    }

    public void deleteAllHistory()
    {
        db.execSQL("delete from "+ TABLE_NAME);
        db.close();
    }
}
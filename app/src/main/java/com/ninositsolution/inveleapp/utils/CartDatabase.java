package com.ninositsolution.inveleapp.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartDatabase extends SQLiteOpenHelper {

    private static final String TAG = "CartDatabase";
    public static String DATABASE_NAME = "cart";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "cart_table";
    private static final String KEY_PARENT = "KEY_PARENT";
    private static final String KEY_CHILD = "KEY_CHILD";
    private static final String KEY_VALUE = "KEY_VALUE";
    private static final String KEY_AMOUNT = "KEY_AMOUNT";
    private static final String KEY_SHOPPING_AMOUNT = "KEY_SHOPPING_AMOUNT";
    private static final String KEY_QUANTITY = "KEY_QUANTITY";

    private static final String CREATE_CALL_TABLE = " CREATE TABLE "+ TABLE_NAME + " (" +
            KEY_PARENT + " INTEGER NOT NULL, " + KEY_CHILD + " INTEGER NOT NULL, " +
            KEY_VALUE + " INTEGER DEFAULT 0, " + KEY_QUANTITY + " INTEGER NOT NULL, " + KEY_SHOPPING_AMOUNT + " TEXT NOT NULL, " +
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

    public long insertValues(int parent, int child, int quantity, String amount, String shoppingAmount)
    {
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_PARENT, parent);
        values.put(KEY_CHILD, child);
        values.put(KEY_QUANTITY, quantity);
        values.put(KEY_AMOUNT, amount);
        values.put(KEY_SHOPPING_AMOUNT, shoppingAmount);

        return db.insert(TABLE_NAME, null, values);
    }

    public void deleteValues()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL("delete from "+ TABLE_NAME);
            db.close();
        } catch (Exception e)
        {
            Log.e(TAG, e.getMessage());
        }

    }

    public boolean getParentCheckbox(int position)
    {
        Cursor cursor = null;
        int value;
        List<Integer> results = new ArrayList<>();

        try {
            cursor = getReadableDatabase().rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_PARENT+"=?", new String[] {position + ""});
            if(cursor.getCount() > 0) {

                Log.i(TAG, "count -> "+cursor.getCount());

                if (cursor.moveToFirst())
                {
                    do {
                        value = cursor.getInt(cursor.getColumnIndex(KEY_VALUE));

                        Log.i(TAG, "value -> "+value);

                        results.add(value);

                    } while (cursor.moveToNext());

                }

            }
        }
        catch(Exception e)
        {
            Log.e(TAG, "getParentCheckbox -> "+e);
        }

        finally {
            cursor.close();

            return !results.contains(0);

        }
    }

    public boolean getChildCheckBox(int parentPosition, int childPosition)
    {
        Cursor cursor = null;
        int value = 0;

        try {
            cursor = getReadableDatabase().rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_PARENT+"=? AND "+KEY_CHILD+"=?",
                    new String[] {parentPosition +"", childPosition+""});

            if (cursor.getCount() > 0)
            {
                if (cursor.moveToFirst())
                {
                    Log.i(TAG, "Count -> "+cursor.getCount());
                    value = cursor.getInt(cursor.getColumnIndex(KEY_VALUE));
                    Log.i(TAG, "Value -> "+value);
                }

            }

        } catch (Exception e)
        {
            Log.e(TAG, "Error -> "+e);
        }

        finally {
            cursor.close();

            return value == 1;
        }

    }

    public void checkAllSubItems(int position)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String condition = KEY_PARENT+" = "+position;
            ContentValues values = new ContentValues();
            values.put(KEY_VALUE, 1);

            db.update(TABLE_NAME, values, condition, null);

        } catch (Exception e) {
            Log.i(TAG, "Error -> "+e);
        }
    }

    public void unCheckAllSubitems(int position)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String condition = KEY_PARENT+" = "+position;
            ContentValues values = new ContentValues();
            values.put(KEY_VALUE, 0);

            db.update(TABLE_NAME, values, condition, null);

        } catch (Exception e) {
            Log.i(TAG, "Error -> "+e);
        }
    }

    public void checkSubItem(int parentPosition, int childPosition)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String condition = KEY_PARENT+" = "+parentPosition+" AND "+KEY_CHILD+" = "+childPosition;
            ContentValues values = new ContentValues();
            values.put(KEY_VALUE, 1);

            db.update(TABLE_NAME, values, condition, null);

        } catch (Exception e) {
            Log.i(TAG, "Error -> "+e);
        }
    }

    public void unCheckSubItem(int parentPosition, int childPosition)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String condition = KEY_PARENT+" = "+parentPosition+" AND "+KEY_CHILD+" = "+childPosition;
            ContentValues values = new ContentValues();
            values.put(KEY_VALUE, 0);

            db.update(TABLE_NAME, values, condition, null);

        } catch (Exception e) {
            Log.i(TAG, "Error -> "+e);
        }
    }

    public String getTotalAmount()
    {
        Cursor cursor = null;
        String value;
        int quantity;
        List<String> amounts = new ArrayList<>();
        List<Integer> quans = new ArrayList<>();
        double rate = 0.0;

        try {
            cursor = getReadableDatabase().rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_VALUE+"=?", new String[] {1 + ""});
            if(cursor.getCount() > 0) {

                Log.i(TAG, "count -> "+cursor.getCount());

                if (cursor.moveToFirst())
                {
                    do {
                        value = cursor.getString(cursor.getColumnIndex(KEY_AMOUNT));
                        quantity = cursor.getInt(cursor.getColumnIndex(KEY_QUANTITY));

                        Log.i(TAG, "value -> "+value);

                        amounts.add(value);
                        quans.add(quantity);

                    } while (cursor.moveToNext());

                }

            }
        }
        catch(Exception e)
        {
            Log.e(TAG, "getTotalAmount -> "+e);
        }

        finally {
            cursor.close();
            for (int i=0; i<amounts.size(); i++)
            {
                rate = rate+(Double.valueOf(amounts.get(i))*quans.get(i));
            }

            BigDecimal bigDecimal = new BigDecimal(rate);
            bigDecimal =  bigDecimal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            return bigDecimal.toPlainString();

        }
    }

    public void checkAllBoxes()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            //String condition = KEY_PARENT+" = "+parentPosition+" AND "+KEY_CHILD+" = "+childPosition;
            ContentValues values = new ContentValues();
            values.put(KEY_VALUE, 1);

            db.update(TABLE_NAME, values, null, null);

        } catch (Exception e) {
            Log.i(TAG, "Error -> "+e);
        }
    }

    public void unCheckAllBoxes()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            //String condition = KEY_PARENT+" = "+parentPosition+" AND "+KEY_CHILD+" = "+childPosition;
            ContentValues values = new ContentValues();
            values.put(KEY_VALUE, 0);

            db.update(TABLE_NAME, values, null, null);

        } catch (Exception e) {
            Log.i(TAG, "Error -> "+e);
        }
    }

    public boolean allBoxesChecked()
    {
        List<Integer> integerList = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = getReadableDatabase().rawQuery("SELECT * FROM "+TABLE_NAME, null);

            if (cursor.getCount()>0)
            {
                if (cursor.moveToFirst())
                {
                    do {
                            integerList.add(cursor.getInt(cursor.getColumnIndex(KEY_VALUE)));


                    } while (cursor.moveToNext());
                }
            }
        } catch (Exception e)
        {
            Log.e(TAG, "Error -> "+e);

        } finally {

            {
                cursor.close();

                return !integerList.contains(0);
            }
        }
    }

}
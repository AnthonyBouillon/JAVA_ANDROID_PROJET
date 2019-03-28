package com.wimso.android_sqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wimso.android_sqlite.model.Contact;


public class SQLiteDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "Contact.db";
    private static final String TABLE_NAME = "contact";
    private static final String COLUMN_ID = "contact_id";
    private static final String COLUMN_NAME = "contact_name";
    private static final String COLUMN_PHONE = "contact_phone";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + "TEXT" + "," + COLUMN_PHONE + "TEXT" + " )";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public SQLiteDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void create(Contact contact){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, contact.getName());
        values.put(COLUMN_PHONE, contact.getPhone());
        db.insert(TABLE_NAME, null, values);
    }

    public Cursor retrieve(){
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {COLUMN_ID, COLUMN_NAME, COLUMN_PHONE };
        Cursor c = db.query(TABLE_NAME, projection, null, null, null, null, null);
        return c;
    }

    public void update(Contact contact){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, contact.getName());
        values.put(COLUMN_PHONE, contact.getPhone());
        String selection = COLUMN_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(contact.getId()) };
        db.update(TABLE_NAME, values, selection, selectionArgs);
    }

    public void delete(int id){
        SQLiteDatabase db = getReadableDatabase();
        String selection = COLUMN_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(id) };
        db.delete(TABLE_NAME, selection, selectionArgs);
    }
}

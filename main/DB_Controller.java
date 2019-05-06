package com.darrelbott.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DB_Controller extends SQLiteOpenHelper {

    private static final String TABLE_COLLECTION = "collection";
    private static final String COLUMN_COLLECTION_ACCOUNT = "account";
    private static final String COLUMN_COLLECTION_NAME = "card_name";
    private static final String COLUMN_COLLECTION_TYPE = "type";
    private static final String COLUMN_COLLECTION_RARITY = "rarity";
    private static final String COLUMN_COLLECTION_ARTIST = "artist";

    SQLiteDatabase db;
    Context mContext;
    int version = 1;

    public DB_Controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "collection.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_COLLECTION + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_COLLECTION_ACCOUNT + " TEXT, "
                + COLUMN_COLLECTION_NAME + " TEXT, "
                + COLUMN_COLLECTION_TYPE + " TEXT, "
                + COLUMN_COLLECTION_RARITY + " TEXT, "
                + COLUMN_COLLECTION_ARTIST + " TEXT)");
        Log.e(TABLE_COLLECTION, "TABLE COLLECTION HAS BEEN MADE");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS collection;");
        //onCreate(sqLiteDatabase);
    }

    public void insert_card(String account, String card_name, String type, String rarity, String artist) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COLLECTION_ACCOUNT,account);
        contentValues.put(COLUMN_COLLECTION_NAME,card_name);
        contentValues.put(COLUMN_COLLECTION_TYPE,type);
        contentValues.put(COLUMN_COLLECTION_RARITY,rarity);
        contentValues.put(COLUMN_COLLECTION_ARTIST,artist);

        db.insert(TABLE_COLLECTION,null,contentValues);


        //this.getWritableDatabase().insertOrThrow("collection.db","",contentValues);
    }
}

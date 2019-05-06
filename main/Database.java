package com.darrelbott.finalproject.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.darrelbott.finalproject.Model.Cards;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

// Help from EDMT Dev tutorial
public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME="cards.db";
    private static final int DB_VER=1;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }




    // function get all cards
    public List<Cards> getCards() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        // Make sure all is columns name in the table
        String[] sqlSelect={"card_name","type","rarity","artist"};
        String tableName="cards"; //make sure this is the table name

        qb.setTables(tableName);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<Cards> result = new ArrayList<>();
        if(cursor.moveToFirst()) {
            do{
                Cards cards = new Cards();

                cards.setName(cursor.getString(cursor.getColumnIndex("card_name")));
                cards.setType(cursor.getString(cursor.getColumnIndex("type")));
                cards.setRarity(cursor.getString(cursor.getColumnIndex("rarity")));
                cards.setArtist(cursor.getString(cursor.getColumnIndex("artist")));

                result.add(cards);
            } while (cursor.moveToNext());
        }
        return result;
    }




    // function get all cards name
    public List<String> getNames() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        // Make sure all is column name in the table
        String[] sqlSelect={"card_name"};
        String tableName="cards"; // make sure this is the table name

        qb.setTables(tableName);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<String> result = new ArrayList<>();
        if(cursor.moveToFirst()) {
            do{
                // result.add(cursor.getString(cursor.getColumnIndex("Name")));
                result.add(cursor.getString(cursor.getColumnIndex("card_name")));
            } while (cursor.moveToNext());
        }
        return result;
    }




    // function get card by name
    public List<Cards> getCardsByName(String name) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        // Make sure all is column name in your table
        String[] sqlSelect={"card_name","type","rarity","artist"};
        String tableName="cards"; //make sure this is the table name

        qb.setTables(tableName);
        // SQL select statement using cursor
        // SELECT * FROM Table WHERE Name LIKE %variable%
        // to be specific to the name change to: "Name = ?", new String[]{name}
        Cursor cursor = qb.query(db,sqlSelect,"card_name LIKE ?",new String[]{"%"+name+"%"},null,null,null);
        List<Cards> result = new ArrayList<>();
        if(cursor.moveToFirst()) {
            do{
                Cards cards = new Cards();
                cards.setName(cursor.getString(cursor.getColumnIndex("card_name")));
                cards.setType(cursor.getString(cursor.getColumnIndex("type")));
                cards.setRarity(cursor.getString(cursor.getColumnIndex("rarity")));
                cards.setArtist(cursor.getString(cursor.getColumnIndex("artist")));

                result.add(cards);
            } while (cursor.moveToNext());
        }
        return result;
    }
}

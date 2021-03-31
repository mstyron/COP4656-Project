package com.example.fantasybaseball;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class playerContentProvider extends ContentProvider {

    public final static String DBNAME = "hitters";
    public final static String TABLE_HITTERS = "hitters";
    public final static String COLUMN_NAME = "name";                    //String value
    public final static String COLUMN_POSITION = "position";            //String/VarChar value
    public final static String COLUMN_GAMES = "games";                  //int value
    public final static String COLUMN_AVG = "avg";                      //float value
    public final static String COLUMN_OBP = "obp";                      //float value
    public final static String COLUMN_SLG = "slg";                      //float value
    public final static String COLUMN_HR = "hr";                        //int value
    public final static String COLUMN_SB = "sb";                        //int value

    public final static Uri CONTENT_URI = Uri.parse("content://com.example.fantasybaseball.provider/" + TABLE_HITTERS);

    public playerContentProvider(){}

    @Override
    public boolean onCreate(){
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}

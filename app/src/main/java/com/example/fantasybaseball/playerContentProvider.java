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
    public static String tableName() {
            return TABLE_HITTERS;
        }
    static String getCreateSql()
        {
            return "CREATE TABLE "+TABLE_HITTERS+" ( " +
                    COLUMN_NAME + " VARCHAR(100) PRIMARY KEY, " +
                    COLUMN_POSITION + " VARCHAR(100) PRIMARY KEY, " +
                    COLUMN_GAMES + " VARCHAR(100) PRIMARY KEY, " +
                    COLUMN_AVG + " VARCHAR(100) NOT NULL UNIQUE, " +
                    COLUMN_OBP + " VARCHAR(100) NOT NULL, " +
                    COLUMN_SLG + " VARCHAR(6) NOT NULL, "  +
                    COLUMN_HR + " VARCHAR(100) NOT NULL, " +
                    COLUMN_SB + " VARCHAR(100) NOT NULL)" +
                    lastlogin + " DATETIME DEFAULT CURRENT_TIMESTAMP)";
        }
    

    public static final String SQL_CREATE_MAIN = getCreateSql();

    private static final class MainDatabaseHelper extends SQLiteOpenHelper {

        MainDatabaseHelper(Context context) {
            super(context, DBNAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_MAIN);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
    public playerContentProvider(){}
    private MainDatabaseHelper helper;

    @Override
    public boolean onCreate(){
       getContext().deleteDatabase(DBNAME);
        helper = new MainDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return helper.getWritableDatabase()
                .query(tableName(), projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = helper.getWritableDatabase().insert(tableName(), null, values);
        return Uri.withAppendedPath(CONTENT_URI, "" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return helper.getWritableDatabase().delete(tableName(), selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
         return helper.getWritableDatabase()
                .update(tableName(), values, selection, selectionArgs);
    }

}

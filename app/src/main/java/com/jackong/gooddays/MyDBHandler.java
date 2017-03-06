package com.jackong.gooddays;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class MyDBHandler extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "recordeddates.db";
    public static final String TABLE_DATES_RECORDED = "recorded_dates";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DATE_NAME = "dateName";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_REPEAT = "repeatEveryYear";
    public static final String COLUMN_DAYS = "days";
    public static final String query = "CREATE TABLE " + TABLE_DATES_RECORDED + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_DATE_NAME + " TEXT," +
            COLUMN_DATE + " TEXT NOT NULL," +
            COLUMN_REPEAT + " INTEGER DEFAULT 0," +
            COLUMN_DAYS + " INTEGER DEFAULT 0" +
            ")";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATES_RECORDED);
        onCreate(db);
    }

    public void addDates(RecordedDates recordedDates){
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE_NAME,recordedDates.get_datename());
        values.put(COLUMN_DATE,recordedDates.get_date());
        values.put(COLUMN_REPEAT,recordedDates.get_repeateveryyear());
        values.put(COLUMN_DAYS,recordedDates.get_days());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_DATES_RECORDED,null,values);
        db.close();
    }

    public void deleteDates(String datesNameText, String datesText){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_DATES_RECORDED + " WHERE " + COLUMN_DATE_NAME + "=\"" + datesNameText + "\" AND " + COLUMN_DATE + "=\"" + datesText + "\";" );
    }

    public Cursor getAllRows(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_DATES_RECORDED;
        Cursor c = db.rawQuery(query,null);
        if (c!=null){
            c.moveToFirst();

        }
        return c;
    }

    public void UpdateDB(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EEEE");
        long date = System.currentTimeMillis();
        String date_now = sdf.format(date);

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_DATES_RECORDED + " WHERE " + COLUMN_DATE + "=\"" + date_now + "\";" );

    }
}

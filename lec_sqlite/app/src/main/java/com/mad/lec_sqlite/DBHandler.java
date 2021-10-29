package com.mad.lec_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    public static final String Database_Name = "students.db3";
    public static final String Table_Name = "Student_Table";
    public static final String col_1 = "ID";
    public static final String col_2 = "Name";
    public static final String col_3 = "Marks";

    public DBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Table_Name + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Marks INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }

    public boolean insertData(String name, String mark){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2, name);
        contentValues.put(col_3, Integer.parseInt(mark));
        long result = db.insert(Table_Name, null, contentValues);
        if(result == -1){
            return false;
        }
        else
            return true;
    }

    public Cursor selectAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Table_Name, null);
        return cursor;
    }

    public int updateInfo(String name, String mark){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col_3, mark);
        String selection = col_2 + " LIKE ?";
        String[] selectionArgs = {name};
        int rowId = db.update(Table_Name, values, selection, selectionArgs);
        return rowId;
    }


    public void deleteInfo(String name){
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = col_2 + " LIKE ?";
        String[] selectionArgs = {name};
        db.delete(Table_Name, selection, selectionArgs);

    }


}





























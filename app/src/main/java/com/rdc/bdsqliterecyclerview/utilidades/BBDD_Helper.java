package com.rdc.bdsqliterecyclerview.utilidades;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BBDD_Helper extends SQLiteOpenHelper {

    private static final String TABLA_MASCOTAS = "CREATE TABLE mascotas" +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, dueño TEXT, nombre TEXT, raza TEXT)";


    public BBDD_Helper( Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "bd_mascotas.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLA_MASCOTAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS mascotas");
        onCreate(db);
    }
}

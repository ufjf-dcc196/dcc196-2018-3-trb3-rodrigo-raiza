package com.example.rodri.filmesseries.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class VideosDbHelper extends  SQLiteOpenHelper{

    public final static int DATABASE_VERSION = 1;
    public final static String  DATABASE_NAME = "filmes.db";

    public VideosDbHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(VideosContract.Filme.CREATE_FILME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(VideosContract.Filme.DROP_FILME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db,oldVersion,newVersion);
    }
}

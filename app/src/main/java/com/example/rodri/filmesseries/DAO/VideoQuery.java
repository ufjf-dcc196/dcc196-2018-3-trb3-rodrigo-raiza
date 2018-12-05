package com.example.rodri.filmesseries.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class VideoQuery {

    public static void InserirFilme(SQLiteDatabase db, String titulo, String data, String idmdb, String imagem, String descricao){
        ContentValues values = new ContentValues();
        values.put(VideosContract.Filme.COLUMN_NAME_TITULO,titulo);
        values.put(VideosContract.Filme.COLUMN_NAME_DATA,data);
        values.put(VideosContract.Filme.COLUMN_NAME_IDMDB,idmdb);
        values.put(VideosContract.Filme.COLUMN_NAME_IMAGEM, imagem);
        values.put(VideosContract.Filme.COLUMN_NAME_DESCRICAO,descricao);
        long id = db.insert(VideosContract.Filme.TABLE_NAME,null, values);
        values.put(VideosContract.Filme._ID, Long.valueOf(id));
        Log.i("DBINFO","registro criado com id: " + id);
    }

    public static Cursor getCursorFilmes(SQLiteDatabase db) {
        String[] visao = {
                VideosContract.Filme._ID,
                VideosContract.Filme.COLUMN_NAME_TITULO,
                VideosContract.Filme.COLUMN_NAME_DATA,
                VideosContract.Filme.COLUMN_NAME_DESCRICAO,
                VideosContract.Filme.COLUMN_NAME_IDMDB,
                VideosContract.Filme.COLUMN_NAME_IMAGEM
        };
        return db.query(
                VideosContract.Filme.TABLE_NAME,
                visao,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static Cursor getCursorFilmeId(SQLiteDatabase db,String id) {

        String[] visao = {
                VideosContract.Filme._ID,
                VideosContract.Filme.COLUMN_NAME_TITULO,
                VideosContract.Filme.COLUMN_NAME_IDMDB,
                VideosContract.Filme.COLUMN_NAME_DATA,
                VideosContract.Filme.COLUMN_NAME_DESCRICAO,
                VideosContract.Filme.COLUMN_NAME_IMAGEM

        };
        String select = VideosContract.Filme._ID+"=?";
        String[] selectionArgs = {(id)};
        return db.query(
                VideosContract.Filme.TABLE_NAME,
                visao,
                select,
                selectionArgs,
                null,
                null,
                null,
                null);
    }

    public static void UpdateFilme(SQLiteDatabase db,String id,String titulo, String data, String idmdb, String imagem, String descricao){
        ContentValues values = new ContentValues();
        values.put(VideosContract.Filme.COLUMN_NAME_TITULO, titulo);
        values.put(VideosContract.Filme.COLUMN_NAME_IDMDB, idmdb);
        values.put(VideosContract.Filme.COLUMN_NAME_IMAGEM, imagem);
        values.put(VideosContract.Filme.COLUMN_NAME_DESCRICAO, descricao);
        values.put(VideosContract.Filme.COLUMN_NAME_DATA, data);
        String whereClause = VideosContract.Filme._ID+"=?";
        String[] whereArgs = {(id)};
        db.update(VideosContract.Filme.TABLE_NAME,values,whereClause,whereArgs);
    }

    public static void deleteFilme(SQLiteDatabase db,String idFilme){
        String select = VideosContract.Filme._ID+"=?";
        String[] selectArgs = {idFilme};
        db.delete(VideosContract.Filme.TABLE_NAME,select,selectArgs);
    }
}

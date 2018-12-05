package com.example.rodri.filmesseries.DAO;

import android.provider.BaseColumns;

public class VideosContract {
    public final class Filme implements BaseColumns {
        public final static String TABLE_NAME = "TB_FILME";
        public final static String COLUMN_NAME_TITULO = "TITULO";
        public final static String COLUMN_NAME_DATA = "DATA";
        public final static String COLUMN_NAME_IDMDB = "IDIMDB";
        public final static String COLUMN_NAME_IMAGEM = "IMAGEM";
        public final static String COLUMN_NAME_DESCRICAO = "DESCRICAO";
        public final static String CREATE_FILME =
                "CREATE TABLE " + Filme.TABLE_NAME + " ("
                        + Filme._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + Filme.COLUMN_NAME_TITULO+ " TEXT, "
                        + Filme.COLUMN_NAME_DATA+ " TEXT, "
                        + Filme.COLUMN_NAME_IDMDB+ " TEXT, "
                        + Filme.COLUMN_NAME_IMAGEM+ " TEXT, "
                        + Filme.COLUMN_NAME_DESCRICAO+ " TEXT "
                        +")";
        public final static String DROP_FILME  = "DROP TABLE IF EXISTS "
                + Filme.TABLE_NAME;
    }


}

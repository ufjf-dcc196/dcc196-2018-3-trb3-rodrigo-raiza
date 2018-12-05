package com.example.rodri.filmesseries.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rodri.filmesseries.Adapter.FilmeAssistidoAdapter;
import com.example.rodri.filmesseries.DAO.VideoQuery;
import com.example.rodri.filmesseries.DAO.VideosDbHelper;
import com.example.rodri.filmesseries.R;

public class ListaFilmesActivity extends AppCompatActivity {
    private VideosDbHelper dbHelper;
    private FilmeAssistidoAdapter adapter;
    private RecyclerView rvFilme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);
        dbHelper = new VideosDbHelper(getApplicationContext());
        adapter = new FilmeAssistidoAdapter(VideoQuery.getCursorFilmes(dbHelper.getReadableDatabase()));
        rvFilme = (RecyclerView) findViewById(R.id.rv_filme_assistido);
        rvFilme.setLayoutManager(new LinearLayoutManager(this));
        rvFilme.setAdapter(adapter);

    }
}

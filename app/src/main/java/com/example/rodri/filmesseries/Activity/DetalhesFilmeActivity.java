package com.example.rodri.filmesseries.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rodri.filmesseries.Classe.Filmes;
import com.example.rodri.filmesseries.DAO.VideoQuery;
import com.example.rodri.filmesseries.DAO.VideosContract;
import com.example.rodri.filmesseries.DAO.VideosDbHelper;
import com.example.rodri.filmesseries.R;

public class DetalhesFilmeActivity extends AppCompatActivity {
    private VideosDbHelper dbHelper;
    private Button btnInserir;
    private Filmes f;
    private TextView txtTitulo;
    private TextView txtData;
    private TextView txtDescricao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_filme);
        f = (Filmes) getIntent().getSerializableExtra("Detalhe Filme");
        dbHelper = new VideosDbHelper(getApplicationContext());
        txtTitulo = findViewById(R.id.txt_filme_detalhes_titulo);
        txtData = findViewById(R.id.txt_filme_detalhes_data);
        txtDescricao = findViewById(R.id.txt_filme_detalhes_descricao);
        txtTitulo.setText(f.getTitulo());
        txtDescricao.setText(f.getDescricao());
        txtData.setText(f.getDate());
        btnInserir = findViewById(R.id.btn_detalhes_inserir);
        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                VideoQuery.InserirFilme(dbHelper.getReadableDatabase(),f.getTitulo(),f.getDate(),f.getId(),f.getImagem(),f.getDescricao());
                finish();
            }
        });


    }
}

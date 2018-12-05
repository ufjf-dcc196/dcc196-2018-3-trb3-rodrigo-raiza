package com.example.rodri.filmesseries;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rodri.filmesseries.Activity.TMDBActivity;


public class MainActivity extends AppCompatActivity{
    private Button btnListaTMDB;
    private Button btnListaAssistidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnListaTMDB = findViewById(R.id.btn_lista_tmdb);
        btnListaTMDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TMDBActivity.class);
                startActivity(intent);
            }
        });
        btnListaAssistidos = findViewById(R.id.btn_lista_assistidos);

    }


}

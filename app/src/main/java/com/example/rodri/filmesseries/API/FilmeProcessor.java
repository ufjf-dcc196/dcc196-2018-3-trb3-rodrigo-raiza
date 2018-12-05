package com.example.rodri.filmesseries.API;

import com.example.rodri.filmesseries.Classe.Filmes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FilmeProcessor {
    public static List<Filmes> process(String input) {
        List<Filmes> filmes = new ArrayList<>();
        if(input != null && input.startsWith("{")){

            try {
                JSONObject json = new JSONObject(input);

                JSONArray results = json.getJSONArray("results");
                if(results.length() > 0){
                    for (int i = 0; i < results.length(); i++) {

                        JSONObject c = results.getJSONObject(i);
                        String titulo = c.getString("title");
                        String id = c.getString("id");
                        String popularidade = c.getString("popularity");
                        String imagem = c.getString("poster_path");
                        String descricao = c.getString("overview");
                        String date = c.getString("release_date");

                        Filmes filme = new Filmes(titulo, id, popularidade, descricao, date, imagem);
                        filmes.add(filme);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return filmes;
    }
}



package com.example.rodri.filmesseries.API;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.rodri.filmesseries.Classe.Filmes;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class FilmeFetcherAsyncTask extends AsyncTaskLoader<List<Filmes>> {

    private List<Filmes> filmesList;
    String url;

    public FilmeFetcherAsyncTask(Context context, String url){
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        if (filmesList != null) {
            // Se não recuperamos nada, usamos os dados "em cache"
            deliverResult(filmesList);
        } else {
            // Caso contrário, forçamos o load
            forceLoad();
        }
    }

    @Override
    public List<Filmes> loadInBackground() {
        HttpHandler sh = new HttpHandler();
        String urlAPI = url;
        String jsonString = "";
        try {
            jsonString = sh.makeHttpRequest(createUrl(urlAPI));
        } catch (IOException e) {
            return null;
        }
        //Neste ponto fazemos a conversão do JSON para uma lista de objetos do tipo Filmes
        List<Filmes> filmes = FilmeProcessor.process(jsonString);
        return filmes;

    }

    private URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            return null;
        }
        return url;
    }
    @Override
    public void deliverResult(List<Filmes> filmes) {
        // Aqui, guardamos localmente o resultado retornado para que possamos usá-lo mais tarde, se precisar como "cache"
        filmesList = filmes;
        super.deliverResult(filmesList);
    }

    private void closeResource(BufferedReader br){
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


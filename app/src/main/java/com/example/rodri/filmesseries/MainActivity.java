package com.example.rodri.filmesseries;

import android.app.LoaderManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Loader;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.LoaderManager.LoaderCallbacks;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<Filmes>>{


    private List<Filmes> filmesList;
    private FilmesAdapter fAdapter;
    private String TAG = MainActivity.class.getSimpleName();
    private RecyclerView listView;
    private View loadingIndicator;
    private Button btnAtualizar;
    private static String REQUEST_URL = "https://api.themoviedb.org/3/discover/movie?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filmesList = new ArrayList<Filmes>();
        listView = (RecyclerView) findViewById(R.id.list);
        listView.setLayoutManager(new LinearLayoutManager(this));
        loadingIndicator = findViewById(R.id.loading_indicator);
        btnAtualizar = (Button) findViewById(R.id.btn_atualizar);

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initLoader(MainActivity.this);
            }
        });
        listView.setAdapter(fAdapter);
        initLoader(MainActivity.this);
    }

    private void initLoader(Context context){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(this.getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.restartLoader(0, null, this);
        } else {
            showDialog();
            loadingIndicator.setVisibility(View.VISIBLE);
            btnAtualizar.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public Loader<List<Filmes>> onCreateLoader(int i, Bundle bundle) {
        Uri baseUri = Uri.parse(REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("api_key", "e381b13f80612bf79dca68e0c652d549");

        loadingIndicator.setVisibility(View.INVISIBLE);
        btnAtualizar.setVisibility(View.INVISIBLE);
        return new FilmeFetcherAsyncTask(this,uriBuilder.toString());    }


    @Override
    public void onLoadFinished(Loader<List<Filmes>> loader, List<Filmes> filmes) {
        filmesList = filmes;
        if(filmesList != null && !filmesList.isEmpty() ){
            fAdapter = new FilmesAdapter(filmesList);
            listView.setAdapter(fAdapter);
            loadingIndicator.setVisibility(View.INVISIBLE);
            btnAtualizar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Filmes>> loader) {
        filmesList = new ArrayList<>();
        fAdapter = new FilmesAdapter(filmesList);
        listView.setAdapter(fAdapter);
    }

    private void showDialog() throws Resources.NotFoundException {
        new AlertDialog.Builder(this)
                .setTitle("Aviso")
                .setMessage("Favor verificar sua conexão com a internet")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }})
                .setNegativeButton(android.R.string.no, null).show();

    }
}

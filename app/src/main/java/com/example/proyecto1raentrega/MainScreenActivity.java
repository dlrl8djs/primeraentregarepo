package com.example.proyecto1raentrega;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto1raentrega.db.AppDatabase;
import com.example.proyecto1raentrega.models.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class MainScreenActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMovies;
    private PeliculaAdapter peliculaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        // Inicializar RecyclerView
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));

        new LoadPeliculasTask().execute();
    }

    // Clase para cargar películas en segundo plano
    private class LoadPeliculasTask extends AsyncTask<Void, Void, ArrayList<Pelicula>> {

        @Override
        protected ArrayList<Pelicula> doInBackground(Void... voids) {
            // Acceder a DB
            AppDatabase db = AppDatabase.getInstance(MainScreenActivity.this);
            return Pelicula.getAllPeliculas(db);
        }

        @Override
        protected void onPostExecute(ArrayList<Pelicula> peliculas) {
            // Configurar el adaptador
            peliculaAdapter = new PeliculaAdapter(peliculas);
            recyclerViewMovies.setAdapter(peliculaAdapter);
        }
    }
}
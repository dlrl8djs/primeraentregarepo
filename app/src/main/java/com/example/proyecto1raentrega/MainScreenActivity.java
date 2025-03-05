package com.example.proyecto1raentrega;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.util.Log;
import com.example.proyecto1raentrega.db.AppDatabase;
import com.example.proyecto1raentrega.models.Coleccion;
import com.example.proyecto1raentrega.models.Pelicula;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class MainScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppDatabase db = AppDatabase.getInstance(this);

        Thread t = new Thread(()->
        {

            db.getOpenHelper().getWritableDatabase().execSQL("DELETE FROM peliculas");
            db.getOpenHelper().getWritableDatabase().execSQL("DELETE FROM colecciones");

            ArrayList<Pelicula> alp = new ArrayList<Pelicula>();
            alp.add(new Pelicula("Pelicula 1 titulo", 1920));
            alp.add(new Pelicula("Pelicula 2 titulo", 2026));
            alp.add(new Pelicula("Pelicula 3 titulo", 2020));
            alp.add(new Pelicula("Pelicula 4 titulo", 1960));
            alp.add(new Pelicula("Pelicula 5 titulo", 2005));

            for(Pelicula p : alp){
                db.peliculaDao().insert(p);
            }

            List<Pelicula> listaps = db.peliculaDao().getAllPeliculas();
            for(Pelicula p : listaps){
                Log.d("Prueba peliculas creadas", "pelicula: "+p);
            }


            List<Integer> peliculasIds = Arrays.asList(listaps.get(0).getId(),listaps.get(2).getId());
            Coleccion coleccion = new Coleccion("Coleccion de de prueba 1", peliculasIds);
            db.coleccionDao().insert(coleccion);

            peliculasIds = Arrays.asList(listaps.get(1).getId(),listaps.get(3).getId());
            coleccion = new Coleccion("Coleccion de de prueba 2", peliculasIds);
            db.coleccionDao().insert(coleccion);

            Coleccion c1 = db.coleccionDao().getColeccionByNombre("Coleccion de de prueba 1");
            Coleccion c2 = db.coleccionDao().getColeccionByNombre("Coleccion de de prueba 2");

            Log.d("Colec 1", c1.toString(this));
            Log.d("Colec 2",c2.toString(this));

            db.coleccionDao().delete(c2);

            Log.d("Borrado de colec", "cantidad de colecs en memoria"+db.coleccionDao().getAllColecciones().size());

            db.peliculaDao().delete(db.peliculaDao().getPeliculaById(c1.getPeliculasIds().get(0)));

            Log.d("Borrado de pelicula","peliculas en c1: "+c1.toString(this));

            c1.setPeliculasIds(Arrays.asList(listaps.get(2).getId(),listaps.get(4).getId()));
            c1.setNombre("Colec de peliculas 3");
            db.coleccionDao().insert(c1);

            Log.d("Modificada c1", ""+db.coleccionDao().getColeccionById(c1.getId()).toString(this));

        }
        );
        t.start();

    }
}
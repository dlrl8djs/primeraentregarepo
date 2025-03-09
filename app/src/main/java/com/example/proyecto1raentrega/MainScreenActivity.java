package com.example.proyecto1raentrega;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import com.example.proyecto1raentrega.db.AppDatabase;
import com.example.proyecto1raentrega.db.ColeccionEntity;
import com.example.proyecto1raentrega.db.PeliculaEntity;
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

        Thread t2 = new Thread(()-> {

        db.getOpenHelper().getWritableDatabase().execSQL("DELETE FROM peliculas");
        db.getOpenHelper().getWritableDatabase().execSQL("DELETE FROM colecciones");

        // crear peliculas
        Pelicula p1 = new Pelicula("Titulo de p1", 1980);
        Pelicula p2 = new Pelicula("Titulo de p2", 2010);
        Pelicula p3 = new Pelicula("Titulo de p3", 2004);
        Pelicula p4 = new Pelicula("Titulo de p4", 2001);
        p1.persist(db);
        p2.persist(db);
        p3.persist(db);
        p4.persist(db);

        Log.d("creado p1", p1.toString());
        Log.d("creado p2", p2.toString());
        Log.d("creado p3", p3.toString());
        Log.d("creado p4", p4.toString());


        // crear colecciones
        Coleccion c1 = new Coleccion("nombre de c1");
        Coleccion c2 = new Coleccion("nombre de c2");
        c1.persist(db);
        c2.persist(db);

        Log.d("creado c1", c1.toString());
        Log.d("creado c2", c2.toString());


        // update peliculas
        p1.setTitulo("titulo de p1 actualizado");
        p1.persist(db);
        p1 = Pelicula.getById(db, p1.getId());
        Log.d("update p1", p1.toString());


        // update colecciones
        c1.setNombre("nombre de c1 actualizado");
        c1.persist(db);
        c1 = Coleccion.getById(db, c1.getId());
        Log.d("update c1", c1.toString());


        // add pelicula a coleccion
        c1.addPelicula(p1,db);
        c1.addPelicula(p2,db);
        c1.addPelicula(p3,db);
        c2.addPelicula(p1,db);
        c2.addPelicula(p3,db);
        c1.persist(db);
        c2.persist(db);
        c1 = Coleccion.getById(db, c1.getId());
        c2 = Coleccion.getById(db, c2.getId());
        Log.d("add p123 a c1", c1.toString());
        Log.d("add p13 a c2", c2.toString());

        // remove pelicula de coleccion
        c1.removePelicula(p1,db);
        c1.persist(db);
        c1 = Coleccion.getById(db, c1.getId());
        Log.d("remove p1 de c1", c1.toString());

        // remove pelicula
        p2.delete(db);
        c1.persist(db);
        c1 = Coleccion.getById(db, c1.getId());

        Log.d("remove p2", c1.toString());

        //remove coleccion
        c1.delete(db);

        //mostrar todas colecciones
        ArrayList<Coleccion> cols = Coleccion.getAllColecciones(db);
        for(Coleccion c : cols){
            Log.d("TODAS LAS COLECCIONES",c.toString());
        }

        //mostrar todas peliculas
        ArrayList<Pelicula> pels = Pelicula.getAllPeliculas(db);
        for(Pelicula p : pels){
            Log.d("TODAS LAS PELICULAS",p.toString());
        }

        }
        );
        t2.start();

    }
}
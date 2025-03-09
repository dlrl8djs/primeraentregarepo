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

        // borrar datos existentes en la base de datos
        /*Thread t = new Thread(()->
        {

            //db.getOpenHelper().getWritableDatabase().execSQL("DELETE FROM peliculas");
            //db.getOpenHelper().getWritableDatabase().execSQL("DELETE FROM colecciones");


        }
        );
        t.start();*/


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


        /*p1 = Pelicula.getById(db, p1.getId());
        p2 = Pelicula.getById(db, p2.getId());
        p3 = Pelicula.getById(db, p3.getId());
        p4 = Pelicula.getById(db, p4.getId());*/


            ArrayList<Pelicula> pels = Pelicula.getAllPeliculas(db);
            /*for(Pelicula p : pels){
                Log.d("TODAS LAS PELICULAS",p.toString());
            }*/
            Log.d("SIZE AL PELS", ""+pels.size());
            p1 = pels.get(0);
            p2 = pels.get(1);
            p3 = pels.get(2);
            p4 = pels.get(3);



        Log.d("creado p1", p1.toString());
        Log.d("creado p2", p2.toString());
        Log.d("creado p3", p3.toString());
        Log.d("creado p4", p4.toString());


        // crear colecciones
        Coleccion c1 = new Coleccion("nombre de c1");
        Coleccion c2 = new Coleccion("nombre de c2");
        c1.persist(db);
        c2.persist(db);



        /*c1 = Coleccion.getById(db, c1.getId());
        c2 = Coleccion.getById(db, c2.getId());*/


        ArrayList<Coleccion> cols = Coleccion.getAllColecciones(db);
        for(Coleccion c : cols){
            Log.d("TODAS LAS COLECCIONES",c.toString());
        }

            Log.d("SIZE AL COLS", ""+cols.size());


            c1 = cols.get(0);
            c2 = cols.get(1);

        Log.d("creado c1", c1.toString());
        Log.d("creado c2", c2.toString());




        // update peliculas
        p1.setTitulo("titulo de p1 actualizado");
        p1.persist(db);
        p1 = Pelicula.getById(db, p1.getId());
        Log.d("update p1", p1.toString());

            cols = Coleccion.getAllColecciones(db);
            Log.d("SIZE COLS FIN 1", ""+cols.size());

        // update colecciones
        c1.setNombre("nombre de c1 actualizado");
        c1.persist(db);
        c1 = Coleccion.getById(db, c1.getId());
        Log.d("update c1", c1.toString());

            cols = Coleccion.getAllColecciones(db);
            Log.d("SIZE COLS FIN 2", ""+cols.size());

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

            cols = Coleccion.getAllColecciones(db);
            Log.d("SIZE COLS FIN 3", ""+cols.size());

        // remove pelicula de coleccion
        c1.removePelicula(p1,db);
        c1.persist(db);
        c1 = Coleccion.getById(db, c1.getId());
        Log.d("remove p1 de c1", c1.toString());

            cols = Coleccion.getAllColecciones(db);
            Log.d("SIZE COLS FIN 4", ""+cols.size());

            //cols = Coleccion.getAllColecciones(db);
            //Log.d("SIZE COLS FIN 4.5", ""+cols.size());

        // remove pelicula
        p2.delete(db);
        c1.persist(db);
        c1 = Coleccion.getById(db, c1.getId());
        Log.d("remove p1", c1.toString());

        //remove coleccion
        c1.delete(db);

        //mostrar todas colecciones
        cols = Coleccion.getAllColecciones(db);
            Log.d("SIZE COLS FIN 5", ""+cols.size());

            for(Coleccion c : cols){
            Log.d("TODAS LAS COLECCIONES LIN 152",c.toString());
        }

        //mostrar todas peliculas
         pels = Pelicula.getAllPeliculas(db);
        for(Pelicula p : pels){
            Log.d("TODAS LAS PELICULAS",p.toString());
        }

        }
        );
        t2.start();

    }
}
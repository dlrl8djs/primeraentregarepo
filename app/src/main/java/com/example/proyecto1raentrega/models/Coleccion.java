package com.example.proyecto1raentrega.models;

import android.content.Context;
import android.util.Log;

import com.example.proyecto1raentrega.db.AppDatabase;
import com.example.proyecto1raentrega.db.ColeccionEntity;
import com.example.proyecto1raentrega.db.PeliculaEntity;

import java.util.ArrayList;
import java.util.List;

public class Coleccion {

    private int id;
    private String nombre;
    private ArrayList<Pelicula> peliculas;

    public Coleccion(String nombre) {
        this.nombre = nombre;
        this.peliculas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public void addPelicula(Pelicula pelicula, AppDatabase db) {
        if (!peliculas.contains(pelicula)) {
            peliculas.add(pelicula);
        }
        //this.persist(db);
    }

    public void removePelicula(Pelicula pelicula, AppDatabase db) {
        peliculas.remove(pelicula);
        //this.persist(db);
    }

    public void persist(AppDatabase db) {

        List<Integer> peliculasIds = new ArrayList<>();
        for (Pelicula pelicula : peliculas) {
            peliculasIds.add(pelicula.getId());
        }

        ColeccionEntity entity = new ColeccionEntity(this.nombre, peliculasIds);
        entity.setId(this.id);

        //new Thread(() -> {
            db.coleccionDao().insert(entity);
            //Log.d("PERSIST USADO", this.toString());
        //}).start();
    }

    public void delete(AppDatabase db) {
        ColeccionEntity entity = new ColeccionEntity(this.nombre, new ArrayList<>());
        entity.setId(this.id);

        //new Thread(() -> {
            db.coleccionDao().delete(entity);
        //}).start();

    }

    public static Coleccion getById(AppDatabase db, int id) {
        ColeccionEntity entity = db.coleccionDao().getColeccionById(id);
        if (entity != null) {
            Coleccion coleccion = new Coleccion(entity.getNombre());

            for (int peliculaId : entity.getPeliculasIds()) {
                Pelicula pelicula = Pelicula.getById(db, peliculaId);
                if (pelicula != null) {
                    coleccion.addPelicula(pelicula, db);
                }
            }

            coleccion.setId(entity.getId());
            return coleccion;
        }
        return null;
    }

    public static ArrayList<Coleccion> getAllColecciones(AppDatabase db){
        List<ColeccionEntity> listacs = db.coleccionDao().getAllColecciones();
        //Log.d("GETALLCOLS COLECENTITY LIST",""+listacs.size());
        ArrayList<Coleccion> alcol = new ArrayList<>();
        for(ColeccionEntity entity : listacs){
            alcol.add(getById(db, entity.getId()));
        }
        return alcol;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (!(o instanceof Coleccion)) {
            return false;
        }

        Coleccion c = (Coleccion) o;

        return c.id==this.id;
    }

    public String toString(){
        String s = "Coleccion( id: "+id+" | nombre: "+nombre+" | Peliculas( ";
        for( Pelicula p : peliculas){
            s+=p+" ";
        }
        s+=")";
        return s;
    }
}
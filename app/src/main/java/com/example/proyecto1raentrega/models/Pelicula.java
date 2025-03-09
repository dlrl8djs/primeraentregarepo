package com.example.proyecto1raentrega.models;

import android.util.Log;

import com.example.proyecto1raentrega.db.AppDatabase;
import com.example.proyecto1raentrega.db.PeliculaEntity;

import java.util.ArrayList;
import java.util.List;

public class Pelicula {
    private int id;
    private String titulo;
    private int estreno;

    public Pelicula(String titulo, int estreno) {
        this.titulo = titulo;
        this.estreno = estreno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getEstreno() {
        return estreno;
    }

    public void setEstreno(int estreno) {
        this.estreno = estreno;
    }

    public void persist(AppDatabase db) {
        PeliculaEntity entity = new PeliculaEntity(this.titulo, this.estreno);
        entity.setId(this.id);

        long newid = db.peliculaDao().insert(entity);
        this.id=(int)newid;
    }

    public void delete(AppDatabase db) {
        PeliculaEntity entity = new PeliculaEntity(this.titulo, this.estreno);
        entity.setId(this.id);

        ArrayList<Coleccion> alcs = Coleccion.getAllColecciones(db);
        for(Coleccion c : alcs){
            if(c.getPeliculas().contains(this)){
                c.removePelicula(this, db);
            }
        }
        db.peliculaDao().delete(entity);
    }

    public static Pelicula getById(AppDatabase db, int id) {
        PeliculaEntity entity = db.peliculaDao().getPeliculaById(id);
        if (entity != null) {
            Pelicula pelicula = new Pelicula(entity.getTitulo(), entity.getEstreno());
            pelicula.setId(entity.getId());
            return pelicula;
        }
        return null;
    }

    public static ArrayList<Pelicula> getAllPeliculas(AppDatabase db){
        List<PeliculaEntity> listaps = db.peliculaDao().getAllPeliculas();
        ArrayList<Pelicula> alpel = new ArrayList<>();
        for(PeliculaEntity entity : listaps){
            Pelicula pelicula = new Pelicula(entity.getTitulo(), entity.getEstreno());
            pelicula.setId(entity.getId());
            alpel.add(pelicula);
        }
        return alpel;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (!(o instanceof Pelicula)) {
            return false;
        }

        Pelicula p = (Pelicula) o;

        return p.id==this.id;
    }

    @Override
    public String toString(){
        return "Pelicula( id: "+id+" | titulo: "+titulo+" | estreno: " + estreno +" )";
    }

}
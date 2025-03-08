package com.example.proyecto1raentrega.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "peliculas")
public class PeliculaEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String titulo;
    private int estreno;

    public PeliculaEntity(String titulo, int estreno) {
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

    @Override
    public String toString(){
        return "Pelicula( id: "+id+" | titulo: "+titulo+" | estreno: " + estreno +" )";
    }

}
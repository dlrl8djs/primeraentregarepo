package com.example.proyecto1raentrega.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.proyecto1raentrega.db.ListConverter;

import java.util.List;

@Entity(tableName = "colecciones")
public class Coleccion {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nombre;

    @TypeConverters(ListConverter.class)
    private List<Integer> peliculasIds;

    public Coleccion(String nombre, List<Integer> peliculasIds) {
        this.nombre = nombre;
        this.peliculasIds = peliculasIds;
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

    public List<Integer> getPeliculasIds() {
        return peliculasIds;
    }

    public void setPeliculasIds(List<Integer> peliculasIds) {
        this.peliculasIds = peliculasIds;
    }
}
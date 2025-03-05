package com.example.proyecto1raentrega.db;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.proyecto1raentrega.models.Coleccion;
import com.example.proyecto1raentrega.models.Pelicula;

import java.util.List;

@Dao
public interface PeliculaDao {

    @Insert(onConflict = REPLACE)
    void insert(Pelicula pelicula);

    @Delete
    void delete(Pelicula pelicula);

    @Query("SELECT * FROM peliculas")
    List<Pelicula> getAllPeliculas();

    @Query("SELECT * FROM peliculas WHERE id = :id")
    Pelicula getPeliculaById(int id);

    @Query("SELECT * FROM peliculas WHERE titulo = :titulo")
    Pelicula getPeliculaByTitulo(String titulo);
}
package com.example.proyecto1raentrega.db;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;

@Dao
public interface PeliculaDao {

    @Insert(onConflict = REPLACE)
    void insert(PeliculaEntity peliculaEntity);

    @Delete
    void delete(PeliculaEntity peliculaEntity);

    @Query("SELECT * FROM peliculas")
    List<PeliculaEntity> getAllPeliculas();

    @Query("SELECT * FROM peliculas WHERE id = :id")
    PeliculaEntity getPeliculaById(int id);

    @Query("SELECT * FROM peliculas WHERE titulo = :titulo")
    PeliculaEntity getPeliculaByTitulo(String titulo);
}
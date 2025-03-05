package com.example.proyecto1raentrega.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proyecto1raentrega.models.Coleccion;

import java.util.List;

@Dao
public interface ColeccionDao {

    @Insert
    void insert(Coleccion coleccion);

    @Delete
    void delete(Coleccion coleccion);

    @Query("SELECT * FROM colecciones")
    List<Coleccion> getAllColecciones();

    @Query("SELECT * FROM colecciones WHERE id = :id")
    Coleccion getColeccionById(int id);
}
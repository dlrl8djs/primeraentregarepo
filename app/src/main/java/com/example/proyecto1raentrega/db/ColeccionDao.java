package com.example.proyecto1raentrega.db;


import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proyecto1raentrega.models.ColeccionEntity;

import java.util.List;

@Dao
public interface ColeccionDao {

    @Insert(onConflict = REPLACE)
    void insert(ColeccionEntity coleccionEntity);

    @Delete
    void delete(ColeccionEntity coleccionEntity);

    @Query("SELECT * FROM colecciones")
    List<ColeccionEntity> getAllColecciones();

    @Query("SELECT * FROM colecciones WHERE id = :id")
    ColeccionEntity getColeccionById(int id);

    @Query("SELECT * FROM colecciones WHERE nombre = :nombre")
    ColeccionEntity getColeccionByNombre(String nombre);
}
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


    }
}
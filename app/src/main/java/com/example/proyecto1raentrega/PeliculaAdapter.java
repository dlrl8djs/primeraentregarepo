package com.example.proyecto1raentrega;

import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto1raentrega.models.Pelicula;

import java.util.List;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder>{

    private List<Pelicula> peliculas;

    public PeliculaAdapter(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new PeliculaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        Pelicula pelicula = peliculas.get(position);
        holder.tvMovieTitle.setText(pelicula.getTitulo());
        holder.tvMovieDescription.setText("Estreno: " + pelicula.getEstreno());
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    static class PeliculaViewHolder extends RecyclerView.ViewHolder {
        TextView tvMovieTitle;
        TextView tvMovieDescription;

        public PeliculaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMovieTitle = itemView.findViewById(R.id.tvMovieTitle);
            tvMovieDescription = itemView.findViewById(R.id.tvMovieDescription);
        }
    }
}

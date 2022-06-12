package com.example.biblioteca.adaptadores;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.biblioteca.R;
import com.example.biblioteca.entities.Libro;

public class AdaptadorListaLibros extends ArrayAdapter {
    private final Activity context;
    private final int miLayout;
    private final Libro[] libros;


    static class ViewHolder {
        TextView tituloLibro;
        TextView autorLibro;
        TextView generoLibro;
    }

    public AdaptadorListaLibros(@NonNull Activity context,
                                    int miLayout,
                                    @NonNull Libro[] libros) {
        super(context, miLayout, libros);
        this.context = context;
        this.miLayout = miLayout;
        this.libros = libros;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View fila = convertView;
        ViewHolder holder;

        if (fila == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            fila = inflater.inflate(R.layout.layout_lista_libros, null);

            holder = new ViewHolder();
            holder.tituloLibro = fila.findViewById(R.id.tituloLibro);
            holder.autorLibro = fila.findViewById(R.id.autorLibro);
            holder.generoLibro = fila.findViewById(R.id.generoLibro);

            fila.setTag(holder);
        } else {
            holder = (ViewHolder) fila.getTag();
        }
        holder.tituloLibro.setText(libros[position].getTitulo());
        holder.autorLibro.setText(libros[position].getAutor());
        holder.generoLibro.setText(libros[position].getGenero());
        return (fila);
    }
}

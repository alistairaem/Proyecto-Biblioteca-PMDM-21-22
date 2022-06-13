package com.example.biblioteca.adaptadores;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.biblioteca.R;
import com.example.biblioteca.entities.Prestamo;

public class AdaptadorListaPrestamos extends ArrayAdapter {
    private final Activity context;
    private final int miLayout;
    private final Prestamo[] prestamos;


    static class ViewHolder {
        ImageView imgPrestamo;
        TextView tituloLibroPrestamo;
        TextView inicioPrestamo;
        TextView finPrestamo;
    }

    public AdaptadorListaPrestamos(@NonNull Activity context,
                                int miLayout,
                                @NonNull Prestamo[] prestamos) {
        super(context, miLayout, prestamos);
        this.context = context;
        this.miLayout = miLayout;
        this.prestamos = prestamos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View fila = convertView;
        AdaptadorListaPrestamos.ViewHolder holder;

        if (fila == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            fila = inflater.inflate(R.layout.layout_lista_prestamos, null);

            holder = new AdaptadorListaPrestamos.ViewHolder();
            holder.imgPrestamo = fila.findViewById(R.id.imgPrestamo);
            holder.tituloLibroPrestamo = fila.findViewById(R.id.tituloLibroPrestamo);
            holder.inicioPrestamo = fila.findViewById(R.id.inicioPrestamo);
            holder.finPrestamo = fila.findViewById(R.id.finPrestamo);

            fila.setTag(holder);
        } else {
            holder = (AdaptadorListaPrestamos.ViewHolder) fila.getTag();
        }
        if (prestamos[position].getFinalizado() == 1) {
            holder.imgPrestamo.setImageResource(R.drawable.prestamo_finalizado);
        } else {
            holder.imgPrestamo.setImageResource(R.drawable.prestamo);
        }
        holder.tituloLibroPrestamo.setText(prestamos[position].getLibro());
        holder.inicioPrestamo.setText(prestamos[position].getFechaInicio());
        holder.finPrestamo.setText(prestamos[position].getFechaFin());
        return (fila);
    }
}

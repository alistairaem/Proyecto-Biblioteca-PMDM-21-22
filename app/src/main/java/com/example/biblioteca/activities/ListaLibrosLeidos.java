package com.example.biblioteca.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.biblioteca.R;
import com.example.biblioteca.adaptadores.AdaptadorListaLibros;
import com.example.biblioteca.entities.Libro;
import com.example.biblioteca.sqlite.DatabaseHelper;

public class ListaLibrosLeidos extends AppCompatActivity {
    private LinearLayout fila;
    private ListView lvLibros;
    private Libro[] libros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_libros_leidos);
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.terciario));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);


        cargaVariables();


        AdaptadorListaLibros adaptador = new AdaptadorListaLibros(this, R.layout.layout_lista_libros,
                libros);
        lvLibros.setAdapter(adaptador);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.finSesion:
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.finApp:
                //Finaliza app
                finishAffinity();
                return true;
            case R.id.añadeLibro:
                // Start activity for result
                Intent i = new Intent(this, NuevoLibro.class);
                startActivityForResult(i, 1);

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }


    private void cargaVariables(){
        DatabaseHelper db = new DatabaseHelper(this);
        lvLibros = findViewById(R.id.lvLibros);
        fila = findViewById(R.id.filaLibro);
        libros = db.getLibrosLeidos();
    }
}
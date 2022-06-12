package com.example.biblioteca.activities;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.biblioteca.R;
import com.example.biblioteca.adaptadores.AdaptadorListaLibros;
import com.example.biblioteca.entities.Libro;
import com.example.biblioteca.sqlite.DatabaseHelper;

public class ListaLibros extends AppCompatActivity {
    private boolean usuario;
    private LinearLayout fila;
    private ListView lvLibros;
    private Libro[] libros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_libros);
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.terciario));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);


        usuario = getIntent().getBooleanExtra("usuario", true);

        cargaVariables();

        if (usuario) {
            registerForContextMenu(lvLibros);
        }

        AdaptadorListaLibros adaptador = new AdaptadorListaLibros(this, R.layout.layout_lista_libros,
                libros);
        lvLibros.setAdapter(adaptador);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (usuario) {
            getMenuInflater().inflate(R.menu.menu, menu);
        } else {
            getMenuInflater().inflate(R.menu.libro_admin, menu);
        }
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

    // Menu contextual para cada libro
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        String elemento = libros[info.position].getTitulo();
        menu.setHeaderTitle(elemento);
        inflater.inflate(R.menu.libro_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Intent intent = new Intent();
        DatabaseHelper db = new DatabaseHelper(this);
        int posicion = info.position;
        switch (item.getItemId()) {
            case R.id.marcaLeido:
                db.marcarLeido(libros[posicion].getId());
                intent.setClass(this, ListaLibrosLeidos.class);
                startActivity(intent);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // Recarga la lista
                Intent intent = new Intent(this, ListaLibros.class);
                Toast.makeText(this, getString(R.string.libroAñadido), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, getString(R.string.cancelado), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void cargaVariables() {
        DatabaseHelper db = new DatabaseHelper(this);
        lvLibros = findViewById(R.id.lvLibros);
        fila = findViewById(R.id.filaLibro);
        libros = db.getAllLibros();
    }
}
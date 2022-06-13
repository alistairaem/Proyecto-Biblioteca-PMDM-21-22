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
import com.example.biblioteca.adaptadores.AdaptadorListaPrestamos;
import com.example.biblioteca.entities.Libro;
import com.example.biblioteca.entities.Prestamo;
import com.example.biblioteca.sqlite.DatabaseHelper;

public class ListaPrestamos extends AppCompatActivity {
    private boolean usuario;
    private LinearLayout fila;
    private ListView lvPrestamos;
    private Prestamo[] prestamos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_prestamos);
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.terciario));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        cargaVariables();


        usuario = getIntent().getBooleanExtra("usuario", true);
        if (!usuario) {
            registerForContextMenu(lvPrestamos);
        }

        AdaptadorListaPrestamos adaptador = new AdaptadorListaPrestamos(this, R.layout.layout_lista_prestamos,
                prestamos);
        lvPrestamos.setAdapter(adaptador);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!usuario) {
            getMenuInflater().inflate(R.menu.prestamo_admin, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu, menu);
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
            case R.id.añadePrestamo:
                // Start activity for result
                Intent i = new Intent(this, NuevoPrestamo.class);
                startActivityForResult(i, 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        String elemento = prestamos[info.position].getLibro();
        menu.setHeaderTitle(elemento);
        inflater.inflate(R.menu.prestamo_contextual, menu);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Intent intent = new Intent();
        DatabaseHelper db = new DatabaseHelper(this);
        int posicion = info.position;
        switch (item.getItemId()) {
            case R.id.finalizarPrestamo:
                db.finalizarPrestamo(prestamos[posicion].getId());
                finish();
                intent.setClass(this, ListaPrestamos.class);
                intent.putExtra("usuario", false);
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
                finish();
                Intent intent = new Intent(this, ListaPrestamos.class);
                Toast.makeText(this, getString(R.string.prestamoAñadido), Toast.LENGTH_SHORT).show();
                intent.putExtra("usuario", false);
                startActivity(intent);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, getString(R.string.cancelado), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void cargaVariables() {
        DatabaseHelper db = new DatabaseHelper(this);
        lvPrestamos = findViewById(R.id.lvPrestamos);
        fila = findViewById(R.id.filaLibro);
        prestamos = db.getAllPrestamos();
    }
}
package com.example.biblioteca.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.biblioteca.R;

public class MainAdmin extends AppCompatActivity {

    LinearLayout contacto;
    LinearLayout listaLibros;
    LinearLayout librosAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.terciario));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        if(savedInstanceState == null){
            bienvenida();
        }
        cargaVariables();

        creaNotificacionBarra();

        contacto.setOnClickListener(v -> {
            Intent intent = new Intent(MainAdmin.this, Contacto.class);
            startActivity(intent);
        });

        listaLibros.setOnClickListener(v -> {
            Intent intent = new Intent(MainAdmin.this, ListaLibros.class);
            intent.putExtra("usuario", false);
            startActivity(intent);
        });

        librosAdmin.setOnClickListener(v -> {
            Intent intent = new Intent(MainAdmin.this, ListaLibros.class);
            intent.putExtra("usuario", false);
            startActivity(intent);
        });
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
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void bienvenida() {
        String mensaje = getResources().getString(R.string.bienvenida) + " Admin";
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void creaNotificacionBarra(){
        if (android.os.Build.VERSION.SDK_INT < 26) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.biblioteca)
                    .setContentTitle(getString(R.string.loginRealizado))
                    .setContentText(getString(R.string.mensajeLogin) + " Admin")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            muestraNotificacionBarra(builder);

        }else {
            creaCanalNotificacion();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "NotificationChannel")
                    .setSmallIcon(R.drawable.biblioteca)
                    .setContentTitle(getString(R.string.loginRealizado))
                    .setContentText(getString(R.string.mensajeLogin) + " Admin")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            muestraNotificacionBarra(builder);
        }
    }

    private void muestraNotificacionBarra(NotificationCompat.Builder builder){
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());

    }

    private void creaCanalNotificacion(){
        if (android.os.Build.VERSION.SDK_INT >= 26) {
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            NotificationChannel channel = new NotificationChannel("NotificationChannel", "NotificationChannel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("NotificationChannel");
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void cargaVariables() {
        contacto = findViewById(R.id.contactoAdmin);
        listaLibros = findViewById(R.id.coleccionAdmin);
        librosAdmin = findViewById(R.id.librosAdmin);
    }
}
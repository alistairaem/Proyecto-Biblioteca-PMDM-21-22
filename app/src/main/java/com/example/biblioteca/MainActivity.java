package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtEmail;
    EditText txtContraseña;
    Button btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmail = findViewById(R.id.txtEmail);
        txtContraseña = findViewById(R.id.txtContraseña);
        btnIniciar = findViewById(R.id.btnIniciar);

        btnIniciar.setBackgroundColor(getResources().getColor(R.color.terciario));

        btnIniciar.setOnClickListener(v -> {
            if (txtEmail.getText().toString().equals("admin@admin.com") && txtContraseña.getText().toString().equals("admin")) {
                Toast.makeText(this, "Bienvenido admin", Toast.LENGTH_SHORT).show();
            } else if (txtEmail.getText().toString().equals("user@user.com") && txtContraseña.getText().toString().equals("user")){
                Toast.makeText(this, "Bienvenido user", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
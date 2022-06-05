package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtEmail;
    EditText txtContraseña;
    Button btnIniciar;
    CheckBox chkRecordar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        cargaVariables();
        btnIniciar.setBackgroundColor(getResources().getColor(R.color.terciario));
        cargaEmail();


        btnIniciar.setOnClickListener(v -> {
            realizaLogin();
        });
    }

    private void realizaLogin(){
        Intent intent = new Intent();
        if (txtEmail.getText().toString().equals("admin@admin.com") && txtContraseña.getText().toString().equals("admin")) {
            guardaEmail(txtEmail.getText().toString());
            intent.setClass(MainActivity.this, MainAdmin.class);
            startActivity(intent);
        } else if (txtEmail.getText().toString().equals("user@user.com") && txtContraseña.getText().toString().equals("user")){
            guardaEmail(txtEmail.getText().toString());
            intent.setClass(MainActivity.this, MainUser.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    private void cargaEmail() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getBoolean("recordar", false) == true) {
            String email = prefs.getString("email", "Email");
            txtEmail.setText(email);
        }
    }

    private void guardaEmail(String email){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        if (chkRecordar.isChecked()) {
            editor.putBoolean("recordar", true);
            editor.putString("email", email);
            editor.commit();
        }else{
            editor.putBoolean("recordar", false);
            editor.commit();
        }
    }

    private void cargaVariables(){
        txtEmail = findViewById(R.id.txtEmail);
        txtContraseña = findViewById(R.id.txtContraseña);
        btnIniciar = findViewById(R.id.btnIniciar);
        chkRecordar = findViewById(R.id.chkRecordar);
    }
}
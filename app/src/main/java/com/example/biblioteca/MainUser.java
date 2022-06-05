package com.example.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

public class MainUser extends AppCompatActivity {
    private int contador = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.terciario));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        if(savedInstanceState == null){
            bienvenida();
        }



    }


    private void bienvenida() {
        String mensaje = getResources().getString(R.string.bienvenida) + " User";
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
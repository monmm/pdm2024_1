package com.example.tarea2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.core.view.GravityCompat;


public class HomeActivity extends DrawerMenuActivity {

    private TextView titulo;
    private String usuario;
    private String telefono;

    private Button profileButton;
    private Button groomButton;
    private Button shopButton;
    private Button pharmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_title);
        titulo = getSupportActionBar().getCustomView().findViewById(R.id.action_bar_title);
        titulo.setText("Inicio");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Recuperar el usuario desde SharedPreferences
        SharedPreferences prefs = getSharedPreferences("Nombre_Preferencias", MODE_PRIVATE);
        usuario = prefs.getString("usuario", "valor_por_defecto");
        telefono = prefs.getString("telefono", "valor_por_defecto");

        profileButton = findViewById(R.id.prof);
        groomButton = findViewById(R.id.groom);
        shopButton = findViewById(R.id.shop);
        pharmButton = findViewById(R.id.drug);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        groomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Coming soon...", Toast.LENGTH_SHORT).show();
            }
        });
        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Coming soon...", Toast.LENGTH_SHORT).show();
            }
        });
        pharmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Coming soon...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected int obtenerIdVistaPrincipal() {
        return R.layout.home;
    }
}

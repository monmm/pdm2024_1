package com.example.tarea2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;


public class HomeActivity extends DrawerMenuActivity {

    private TextView titulo;
    private String usuario;

    private Button profileButton;
    private Button groomButton;
    private Button shopButton;
    private Button pharmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_title);
        titulo = getSupportActionBar().getCustomView().findViewById(R.id.action_bar_title);
        titulo.setText("Inicio");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        usuario = getIntent().getStringExtra("USR");

        profileButton = findViewById(R.id.prof);
        groomButton = findViewById(R.id.groom);
        shopButton = findViewById(R.id.shop);
        pharmButton = findViewById(R.id.drug);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                Log.v("MyActivity", "Valor de USR: " + usuario);
                intent.putExtra("USR", usuario);
                startActivity(intent);
            }
        });
        groomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //iniciar grooming
                Intent intent = new Intent(HomeActivity.this, GroomingActivity.class);
                startActivity(intent);
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

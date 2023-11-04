package com.example.tarea2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public abstract class DrawerMenuActivity extends AppCompatActivity {

    protected DrawerLayout drawerLayout;

    protected ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater();
        View mainMenu = inflater.inflate(R.layout.drawer_menu, null, false);
        ViewStub activityContent = mainMenu.findViewById(R.id.contenido_vista);
        activityContent.setLayoutResource(obtenerIdVistaPrincipal());

        setContentView(mainMenu);

        drawerLayout = findViewById(R.id.drawer_main_menu);
        NavigationView navigationView = findViewById(R.id.menu_hamburguesa);

        navigationView.bringToFront();
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                Intent opcionElegida;
                if (item.getItemId() == R.id.home) {
                    opcionElegida = new Intent(DrawerMenuActivity.this, HomeActivity.class);
                } else if (item.getItemId() == R.id.family) {
                    opcionElegida = new Intent(DrawerMenuActivity.this, HomeActivity.class);
                }
                else if (item.getItemId() == R.id.groom) {
                    opcionElegida = new Intent(DrawerMenuActivity.this, HomeActivity.class);
                } else if (item.getItemId() == R.id.shop) {
                    opcionElegida = new Intent(DrawerMenuActivity.this, HomeActivity.class);
                } else if (item.getItemId() == R.id.pharm) {
                    opcionElegida = new Intent(DrawerMenuActivity.this, HomeActivity.class);
                } else {
                    return false;
                }
                startActivity(opcionElegida);
                return false;
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.item1){
            Toast.makeText(this,"Iniciaremos sesión pronto...", Toast.LENGTH_LONG).show();
        }else if(id == R.id.item2) {
            Toast.makeText(this, "Ayuda en el servicio de Petty, pronto nos comunicaremos contigo", Toast.LENGTH_LONG).show();
        }else if(id == R.id.item3) {
            Toast.makeText(this, "Creadores: Monica Miranda Mijangos, Gretel Penélope Cortés Macias", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Verificar si la ActionBar no es nula antes de llamar a 'show()'
        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }
    }
    protected abstract int obtenerIdVistaPrincipal();


}

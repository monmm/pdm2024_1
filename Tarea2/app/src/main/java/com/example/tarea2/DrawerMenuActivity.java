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
        // Infla el contenido del ViewStub
        View contentView = activityContent.inflate();
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
                String mensaje = null;
                Intent opcionElegida = null;

                if (item.getItemId() == R.id.home) {
                    opcionElegida = new Intent(DrawerMenuActivity.this, HomeActivity.class);
                } else if (item.getItemId() == R.id.family) {
                    opcionElegida = new Intent(DrawerMenuActivity.this, ProfileActivity.class);
                } else if (item.getItemId() == R.id.groom) {
                    mensaje = "Seleccionaste Groom";
                } else if (item.getItemId() == R.id.shop) {
                    mensaje = "Seleccionaste Shop";
                } else if (item.getItemId() == R.id.pharm) {
                    mensaje = "Seleccionaste Pharm";
                } else {
                    return false;
                }
                // Eliminar después
                if (mensaje != null) {
                    Toast.makeText(DrawerMenuActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                    try {
                        Thread.sleep(2000);
                        item.setChecked(false);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (opcionElegida != null) {
                    startActivity(opcionElegida);
                }
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
            Toast.makeText(this,"Cerrando sesión...", Toast.LENGTH_LONG).show();
            try {
                Thread.sleep(3000);
                Intent intent = new Intent(DrawerMenuActivity.this, MainActivity.class);
                startActivity(intent);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }else if(id == R.id.item2) {
            Toast.makeText(this, "Pronto nos comunicaremos contigo...", Toast.LENGTH_LONG).show();
        }else if(id == R.id.item3) {
            Toast.makeText(this, "Creadores: @monmm y @cortezgretel", Toast.LENGTH_LONG).show();
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

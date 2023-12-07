package com.example.tarea2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import com.example.tarea2.bdd.MascotaBDD;
import com.example.tarea2.modelos.Mascota;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private TextView titulo;
    private String usuario;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_title);
        titulo = getSupportActionBar().getCustomView().findViewById(R.id.action_bar_title);
        titulo.setText("Perfil");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ListView list = (ListView) findViewById(R.id.chapterList);

        usuario = getIntent().getStringExtra("USR");
        //Mascota m1 = new Mascota("Manzanita", "H", "23/10/2010", "Criolla", "Monica Miranda Mijangos", "525526839159", "GAM", "07400");

        MascotaBDD mascotaBdd = new MascotaBDD(this);
        mascotaBdd.openForRead();
        //mascotaBdd.insertMascota(m1);

        ArrayList<Mascota> chapterList = mascotaBdd.getAllMascotas(usuario);
        mascotaBdd.close();
        if (chapterList == null) {
            Toast.makeText(this, "La lista está vacía", Toast.LENGTH_SHORT).show();
        }
        ArrayAdapter<Mascota> adapter = new ArrayAdapter<Mascota>( this, android.R.layout.simple_list_item_1, chapterList );
        list.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}

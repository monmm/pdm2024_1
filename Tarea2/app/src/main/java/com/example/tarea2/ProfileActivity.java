package com.example.tarea2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tarea2.bdd.MascotaBDD;
import com.example.tarea2.modelos.Mascota;

import java.util.ArrayList;

public class ProfileActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        ListView list = (ListView) findViewById(R.id.chapterList);

        Mascota m1 = new Mascota("Manzanita", "H", "23/10/2010", "Criolla", "Mónica Miranda Mijangos", "525526839159", "GAM", "07400");


        MascotaBDD mascotaBdd = new MascotaBDD(this);
        mascotaBdd.openForWrite();
        mascotaBdd.insertMascota(m1);

        ArrayList<Mascota> chapterList = mascotaBdd.getAllMascotas();
        mascotaBdd.close();
        ArrayAdapter<Mascota> adapter = new ArrayAdapter<Mascota>( this, android.R.layout.simple_list_item_1, chapterList );
        list.setAdapter(adapter);
    }
}

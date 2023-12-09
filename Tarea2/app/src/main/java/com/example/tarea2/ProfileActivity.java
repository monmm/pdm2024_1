package com.example.tarea2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tarea2.bdd.MascotaBDD;
import com.example.tarea2.modelos.ElementoLista;
import com.example.tarea2.modelos.Mascota;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private TextView dueno;
    private TextView telefono;
    private TextView direccion;

    private ImageView edit;
    private ImageView delete;

    private String usuario;
    private String tel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        getSupportActionBar().setTitle("Perfil");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dueno = findViewById(R.id.dueno);
        telefono = findViewById(R.id.telefono);
        direccion = findViewById(R.id.direccion);


        ListView list = (ListView) findViewById(R.id.chapterList);

        // Recuperar el usuario desde SharedPreferences
        SharedPreferences prefs = getSharedPreferences("Nombre_Preferencias", MODE_PRIVATE);
        usuario = prefs.getString("usuario", "valor_por_defecto");
        tel = prefs.getString("telefono", "valor_por_defecto");
        //Mascota m1 = new Mascota("Manzanita", "H", "23/10/2010", "Criolla", "Monica Miranda Mijangos", "525526839159", "GAM", "07400");

        MascotaBDD mascotaBdd = new MascotaBDD(this);
        mascotaBdd.openForRead();
        //mascotaBdd.insertMascota(m1);
        String nombre = usuario.split(" ")[0];
        String d = mascotaBdd.getDirDueno(usuario, tel);
        dueno.setText("Hola " + nombre + "!");
        telefono.setText("Telefono: " + tel);
        direccion.setText("Dirección: " + d);

        List<ElementoLista> listaTemas = new ArrayList<>();
        ArrayList<Mascota> chapterList = mascotaBdd.getAllMascotas(usuario);
        mascotaBdd.close();
        if (chapterList == null) {
            Toast.makeText(this, "La lista está vacía", Toast.LENGTH_SHORT).show();
        }
        for (int i = 0; i < chapterList.size(); i++) {
            listaTemas.add(new ElementoLista(chapterList.get(i)));
        }
        CustomListAdapter customListAdapter = new CustomListAdapter(getApplicationContext(), listaTemas);
        list.setAdapter(customListAdapter);

        customListAdapter.setOnIconClickListener(new CustomListAdapter.OnIconClickListener() {
            @Override
            public void onEditClick(int position) {
                Intent n = new Intent(ProfileActivity.this, EdicionActivity.class);
                n.putExtra("mascota", listaTemas.get(position).getMascota());
                startActivity(n);
            }

            @Override
            public void onDeleteClick(int position) {
                ElementoLista elemento = listaTemas.get(position);
                int id = elemento.getMascota().getId();
                String nombre = elemento.getMascota().getNombre();
                mascotaBdd.openForWrite();
                mascotaBdd.removeMascota(id, nombre);
                mascotaBdd.close();
                Toast.makeText(ProfileActivity.this, "Eliminación exitosa!!", Toast.LENGTH_LONG).show();
                try {
                    Thread.sleep(3000);
                    Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}

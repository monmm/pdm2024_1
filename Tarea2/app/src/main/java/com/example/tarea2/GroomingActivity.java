package com.example.tarea2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class GroomingActivity extends AppCompatActivity {

    private Spinner spinnerGroomingType;
    private ImageView imageViewSelectedGrooming;
    private Button buttonShowMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grooming);
        // Ocultar el ActionBar
        getSupportActionBar().hide();

        spinnerGroomingType = findViewById(R.id.spinnerGroomingType);
        imageViewSelectedGrooming = (ImageView)findViewById(R.id.imageViewSelectedGrooming);
        buttonShowMessage = findViewById(R.id.buttonShowMessage);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.grooming_types,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGroomingType.setAdapter(adapter);

        // Manejar la selección del Spinner
        spinnerGroomingType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Obtener la opción seleccionada
                String selectedGroomingType = parentView.getItemAtPosition(position).toString();
                // Mostrar la imagen correspondiente
                showImageForGroomingType(selectedGroomingType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No hacer nada si no hay nada seleccionado
            }
        });

        // Manejar el clic del botón
        buttonShowMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener la opción seleccionada
                String selectedGroomingType = spinnerGroomingType.getSelectedItem().toString();

                // Mostrar un mensaje
                showMessage(selectedGroomingType);
            }
        });
    }

    private void showImageForGroomingType(String groomingType) {
        // Mostrar la imagen correspondiente según el tipo de grooming
        if ("Corte de pelo".equals(groomingType)) {
            imageViewSelectedGrooming.setImageResource(R.drawable.ic_haircut);
        } else if ("Baño y secado".equals(groomingType)) {
            imageViewSelectedGrooming.setImageResource(R.drawable.ic_bath);
        } else if ("Limpieza de orejas".equals(groomingType)) {
            imageViewSelectedGrooming.setImageResource(R.drawable.ic_ear_cleaning);
        }
    }

    private void showMessage(String groomingType) {
        // Mostrar un mensaje según el tipo de grooming seleccionado
        String message = "Usted tiene una cita agendada para: " + groomingType;
        // Puedes usar Log.d para imprimir el mensaje en el Logcat para propósitos de depuración
        // Log.d("GroomingActivity", message);
        // Mostrar el mensaje de alguna manera, por ejemplo, mediante un Toast
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

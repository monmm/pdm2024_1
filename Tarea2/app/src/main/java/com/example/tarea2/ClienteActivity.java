package com.example.tarea2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;

public class ClienteActivity extends AppCompatActivity {

    // Agregamos nuestros componentes
    Button boton1;
    TextView respuesta;
    RadioButton r1, r2;
    CheckBox cb1, cb2;
    TextInputLayout nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Asociamos componentes con sus respectivos IDs
        boton1 = findViewById(R.id.button1);
        respuesta = findViewById(R.id.respuesta);
        r1 = findViewById(R.id.rdb1);
        r2 = findViewById(R.id.rdb2);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        nombre = findViewById(R.id.nombrePersona);

        // Configuramos el evento clic para el botón
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!r1.isChecked() && !r2.isChecked() && !cb1.isChecked() && !cb2.isChecked()) {
                    respuesta.setText("No ha seleccionado nada");
                }
            }
        });
    }
}

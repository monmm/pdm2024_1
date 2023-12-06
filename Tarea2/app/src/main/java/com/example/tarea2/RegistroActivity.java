package com.example.tarea2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegistroActivity extends AppCompatActivity {

    private Button registrarButton;
    private Button cancelarButton;

    private EditText mascota;
    private EditText nacimiento;
    private EditText dueno;
    private EditText telefono;

    RadioGroup radioGroup;
    RadioButton machoRadioButton;
    RadioButton hembraRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ocultar el ActionBar
        getSupportActionBar().hide();
        // Vista
        setContentView(R.layout.registro);

        mascota = findViewById(R.id.mascota_input);
        nacimiento = findViewById(R.id.nacimiento_input);
        dueno = findViewById(R.id.dueno_input);
        telefono = findViewById(R.id.tel_input);
        registrarButton = findViewById(R.id.registrar);
        cancelarButton = findViewById(R.id.cancel);

        // Acción al hacer clic en el botón de cliente
        cancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad para clientes
                Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        registrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = mascota.getText().toString().trim();
                String n = nacimiento.getText().toString().trim();
                String d = dueno.getText().toString().trim();
                String t = telefono.getText().toString().trim();
                if ( m.isEmpty() || n.isEmpty() || d.isEmpty() || t.isEmpty()) {
                    Toast.makeText(RegistroActivity.this, "Favor de llenar los campos marcados", Toast.LENGTH_LONG).show();
                    mascota.setError("Campo obligatorio");
                    nacimiento.setError("Campo obligatorio");
                    dueno.setError("Campo obligatorio");
                    telefono.setError("Campo obligatorio");
                } else {
                    Toast.makeText(RegistroActivity.this, "Registro exitoso!!", Toast.LENGTH_LONG).show();
                    try {
                        Thread.sleep(3000);
                        Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                        startActivity(intent);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        radioGroup = findViewById(R.id.radio_group_sexo);
        machoRadioButton = findViewById(R.id.macho);
        hembraRadioButton = findViewById(R.id.hembra);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.macho) {
                    machoRadioButton.setChecked(true);
                    hembraRadioButton.setChecked(false);
                } else if (checkedId == R.id.hembra) {
                    machoRadioButton.setChecked(false);
                    hembraRadioButton.setChecked(true);
                }
            }
        });
    }
}

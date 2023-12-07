package com.example.tarea2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tarea2.bdd.MascotaBDD;
import com.example.tarea2.modelos.Mascota;

public class RegistroActivity extends AppCompatActivity {

    private Button registrarButton;
    private Button cancelarButton;

    private EditText mascota;
    private String sexo;
    private EditText nacimiento;
    private EditText raza;
    private EditText dueno;
    private EditText telefono;
    private EditText direccion;
    private EditText cp;

    RadioGroup radioGroup;
    RadioButton machoRadioButton;
    RadioButton hembraRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ocultar el ActionBar
        getSupportActionBar().hide();
        setContentView(R.layout.registro);

        mascota = findViewById(R.id.mascota_input);
        nacimiento = findViewById(R.id.nacimiento_input);
        raza = findViewById(R.id.raza_input);
        dueno = findViewById(R.id.dueno_input);
        telefono = findViewById(R.id.tel_input);
        direccion = findViewById(R.id.dir_input);
        cp = findViewById(R.id.cp_input);

        registrarButton = findViewById(R.id.registrar);
        cancelarButton = findViewById(R.id.cancel);

        cancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        MascotaBDD mascotaBdd = new MascotaBDD(this);

        registrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = mascota.getText().toString().trim();
                String n = nacimiento.getText().toString().trim();
                String r = raza.getText().toString().trim();
                String d = dueno.getText().toString().trim();
                String t = telefono.getText().toString().trim();
                String dir = direccion.getText().toString().trim();
                String c = cp.getText().toString().trim();
                if ( m.isEmpty() || n.isEmpty() || d.isEmpty() || t.isEmpty()) {
                    Toast.makeText(RegistroActivity.this, "Favor de llenar los campos marcados", Toast.LENGTH_LONG).show();
                    mascota.setError("Campo obligatorio");
                    nacimiento.setError("Campo obligatorio");
                    dueno.setError("Campo obligatorio");
                    telefono.setError("Campo obligatorio");
                } else {
                    if (sexo == null) { sexo = ""; }
                    Mascota registro = new Mascota(m, sexo, n, r, d, t, dir, c);
                    mascotaBdd.openForWrite();
                    mascotaBdd.insertMascota(registro);
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
                    sexo = "M";
                } else if (checkedId == R.id.hembra) {
                    machoRadioButton.setChecked(false);
                    hembraRadioButton.setChecked(true);
                    sexo = "H";
                }
            }
        });
    }
}

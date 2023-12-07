package com.example.tarea2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tarea2.bdd.MascotaBDD;
import com.example.tarea2.modelos.Mascota;

public class EdicionActivity extends AppCompatActivity {

    private Mascota data;
    private Button editarButton;
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
        setContentView(R.layout.edicion);

        data = (Mascota) getIntent().getSerializableExtra("mascota");

        mascota = findViewById(R.id.mascota_input);
        mascota.setText(data.getNombre());

        nacimiento = findViewById(R.id.nacimiento_input);
        nacimiento.setText(data.getNacimiento());

        raza = findViewById(R.id.raza_input);
        raza.setText(data.getRaza());

        dueno = findViewById(R.id.dueno_input);
        dueno.setText(data.getDueno());

        telefono = findViewById(R.id.tel_input);
        telefono.setText(data.getTelefono());

        direccion = findViewById(R.id.dir_input);
        direccion.setText(data.getDireccion());

        cp = findViewById(R.id.cp_input);
        cp.setText(data.getCp());

        machoRadioButton = findViewById(R.id.macho);
        hembraRadioButton = findViewById(R.id.hembra);

        sexo = data.getSexo();
        if (sexo.equals("M")) {
            machoRadioButton.setChecked(true);
            hembraRadioButton.setChecked(false);
        } else if (sexo.equals("H")) {
            hembraRadioButton.setChecked(true);
            machoRadioButton.setChecked(false);
        }

        editarButton = findViewById(R.id.editar);
        cancelarButton = findViewById(R.id.cancel);

        cancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EdicionActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        MascotaBDD mascotaBdd = new MascotaBDD(this);

        editarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = data.getId();
                String m = mascota.getText().toString().trim();
                String n = nacimiento.getText().toString().trim();
                String r = raza.getText().toString().trim();
                String d = dueno.getText().toString().trim();
                String t = telefono.getText().toString().trim();
                String dir = direccion.getText().toString().trim();
                String c = cp.getText().toString().trim();
                if ( m.isEmpty() || n.isEmpty() || d.isEmpty() || t.isEmpty()) {
                    Toast.makeText(EdicionActivity.this, "Favor de llenar los campos marcados", Toast.LENGTH_LONG).show();
                    mascota.setError("Campo obligatorio");
                    nacimiento.setError("Campo obligatorio");
                    dueno.setError("Campo obligatorio");
                    telefono.setError("Campo obligatorio");
                } else {
                    if (sexo == null) { sexo = ""; }
                    Mascota registro = new Mascota(m, sexo, n, r, d, t, dir, c);
                    mascotaBdd.openForWrite();
                    mascotaBdd.updateMascota(id, registro);
                    mascotaBdd.close();
                    Toast.makeText(EdicionActivity.this, "Edición exitosa!!", Toast.LENGTH_LONG).show();
                    try {
                        Thread.sleep(3000);
                        Intent intent = new Intent(EdicionActivity.this, ProfileActivity.class);
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

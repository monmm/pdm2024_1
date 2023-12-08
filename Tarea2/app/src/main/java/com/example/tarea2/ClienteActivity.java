package com.example.tarea2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.tarea2.bdd.MascotaBDD;
import com.google.android.material.textfield.TextInputEditText;

public class ClienteActivity extends AppCompatActivity {

    // Agregamos nuestros componentes
    RadioGroup grupo1;
    RadioGroup grupo2;
    Button boton1;
    ImageButton back;
    TextView respuesta;
    TextInputEditText nombre;
    RadioButton r1, r2;
    CheckBox cb1, cb2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Ocultar el ActionBar
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        // Asociamos componentes con sus respectivos IDs
        boton1 = findViewById(R.id.button1);
        back = findViewById(R.id.back);
        respuesta = findViewById(R.id.respuesta);
        nombre = findViewById(R.id.nombrePersona);
        r1 = findViewById(R.id.rdb1);
        r2 = findViewById(R.id.rdb2);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        grupo1=findViewById(R.id.radioGroup);
        grupo2=findViewById(R.id.radioGroup2);

        ImageView imageView = findViewById(R.id.logo);

        imageView.setImageResource(R.drawable.logo);
        boton1 = (Button)findViewById(R.id.button1);
        back = (ImageButton) findViewById(R.id.back);
        respuesta = (TextView)findViewById(R.id.respuesta);
        nombre = (TextInputEditText) findViewById(R.id.nombrePersona);
        r1=(RadioButton)findViewById(R.id.rdb1);
        r2=(RadioButton)findViewById(R.id.rdb2);
        cb1=(CheckBox)findViewById(R.id.cb1);
        cb2=(CheckBox)findViewById(R.id.cb2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClienteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        MascotaBDD mascota = new MascotaBDD(this);

        // Configuramos el evento clic para el botón
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = nombre.getText().toString().trim();
                Log.v("MyActivity", "Valor de USR: " + usuario);

                if (usuario.isEmpty() || !(r1.isChecked() || r2.isChecked())) {
                    respuesta.setText("Favor de llenar los campos");
                } else if (r1.isChecked() && (!cb1.isChecked() && !cb2.isChecked())) {
                    respuesta.setText("Favor de seleccionar un tipo");
                } else {
                    mascota.openForRead();
                    if (mascota.existeDueno(usuario)) {
                        Intent intent = new Intent(ClienteActivity.this, HomeActivity.class);
                        intent.putExtra("USR", usuario);
                        startActivity(intent);
                    } else {
                        respuesta.setText("Favor de registrarse");
                    }
                }

            }

        });

        grupo1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged (RadioGroup group,int checkedId){
                if (checkedId == R.id.cb1) {
                    cb1.setChecked(true);
                    cb2.setChecked(false);
                } else if (checkedId == R.id.cb2) {
                    cb1.setChecked(false);
                    cb2.setChecked(true);
                }
            }
        });

        grupo2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged (RadioGroup group,int checkedId){
                if (checkedId == R.id.rdb1) {
                    r1.setChecked(true);
                    r2.setChecked(false);
                } else if (checkedId == R.id.rdb2) {
                    r1.setChecked(false);
                    r2.setChecked(true);
                }
            }
        });
    }

}

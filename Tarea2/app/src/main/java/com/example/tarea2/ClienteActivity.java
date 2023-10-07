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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class ClienteActivity extends AppCompatActivity {

    //agregamos nuestros componentes
    Button boton1;
    TextView respuesta;
    RadioButton r1, r2;
    CheckBox cb1, cb2;

    TextInputLayout nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        ImageView imageView = findViewById(R.id.logo);
        TextView companyNameTextView = findViewById(R.id.nombre);

        imageView.setImageResource(R.drawable.logo);
        companyNameTextView.setText("PETTY");

        boton1 = (Button)findViewById(R.id.button1);
        respuesta = (TextView)findViewById(R.id.respuesta);
        r1=(RadioButton)findViewById(R.id.rdb1);
        r2=(RadioButton)findViewById(R.id.rdb2);
        cb1=(CheckBox)findViewById(R.id.cb1);
        cb2=(CheckBox)findViewById(R.id.cb2);
        nombre=(TextInputLayout)findViewById(R.id.nombrePersona);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!r1.isChecked()&& !r2.isChecked() && !cb1.isChecked() && !cb2.isChecked()){
                    respuesta.setText("No ha seleccionado nada");
                }
            }
        });
*/
    }
}

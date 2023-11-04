package com.example.tarea2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ClienteActivity extends AppCompatActivity {

    // Agregamos nuestros componentes
    RadioGroup grupo1;
    RadioGroup grupo2;
    Button boton1;
    TextView respuesta;
    RadioButton r1, r2;
    CheckBox cb1, cb2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Petty Cliente");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        // Asociamos componentes con sus respectivos IDs
        boton1 = findViewById(R.id.button1);
        respuesta = findViewById(R.id.respuesta);
        r1 = findViewById(R.id.rdb1);
        r2 = findViewById(R.id.rdb2);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        grupo1=findViewById(R.id.radioGroup);
        grupo2=findViewById(R.id.radioGroup2);

        ImageView imageView = findViewById(R.id.logo);

        imageView.setImageResource(R.drawable.logo);
        boton1 = (Button)findViewById(R.id.button1);
        respuesta = (TextView)findViewById(R.id.respuesta);
        r1=(RadioButton)findViewById(R.id.rdb1);
        r2=(RadioButton)findViewById(R.id.rdb2);
        cb1=(CheckBox)findViewById(R.id.cb1);
        cb2=(CheckBox)findViewById(R.id.cb2);


        // Configuramos el evento clic para el botón
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!r1.isChecked() && !r2.isChecked() && !cb1.isChecked() && !cb2.isChecked()) {
                    respuesta.setText("No ha seleccionado nada");
                }
                Intent intent = new Intent(ClienteActivity.this, HomeActivity.class);
                startActivity(intent);
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

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }



    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.item1){
            Toast.makeText(this,"Iniciaremos sesión pronto...", Toast.LENGTH_LONG).show();
        }else if(id == R.id.item2) {
            Toast.makeText(this, "Ayuda en el servicio de Petty, pronto nos comunicaremos contigo", Toast.LENGTH_LONG).show();
        }else if(id == R.id.item3) {
            Toast.makeText(this, "Creadores: Monica Miranda Mijangos, Gretel Penélope Cortés Macias", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Verificar si la ActionBar no es nula antes de llamar a 'show()'
        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }
    }

}

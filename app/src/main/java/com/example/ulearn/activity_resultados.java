package com.example.ulearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class activity_resultados extends AppCompatActivity {
    TextView txtResCorrectas, txtResPuntaje, txtUsuario;
    FloatingActionButton btnInicio;
    int variable_correctos, variable_puntaje;
    String nomJuego="";

    private DatabaseReference databaseReference;

    TextView txtTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        txtTexto = findViewById(R.id.txtPalabrasCorrectas);


        Bundle datos = this.getIntent().getExtras();

        txtUsuario = findViewById(R.id.txtUsuario);

        txtResCorrectas = findViewById(R.id.txtCantidadCorrectas);
        txtResPuntaje = findViewById(R.id.txtCantidadPuntaje);

        btnInicio = findViewById(R.id.btnhome);

        variable_correctos = datos.getInt("variable_correctos");
        variable_puntaje = datos.getInt("variable_puntajes");
        nomJuego = datos.getString("variable_nomJuego");

        txtResPuntaje.setText(""+variable_puntaje);
        txtResCorrectas.setText(""+variable_correctos);

        if(nomJuego.equals("Puzzle de Numeros")){
            txtTexto.setText("Movimientos");
        }else if(nomJuego.equals("Memoria")){
            txtTexto.setText("Movimientos");
        }else if(nomJuego.equals("Pupiletra")){
            txtTexto.setText("Tiempo");
            String variable_tiempo  = datos.getString("variable_correctos");
            txtResCorrectas.setText(""+variable_tiempo);
        }



        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Obtener los datos en el celular con sharePreferences
        SharedPreferences preferencias = getSharedPreferences("Dato",Context.MODE_PRIVATE);
        String ValorCorreo = preferencias.getString("MiCorreo", "No hay dato");
        String valorNombre = preferencias.getString("MiNombre", "No hay dato");
        String valorIdusuario = preferencias.getString("MiId", "No hay dato");

        txtUsuario.setText(""+valorNombre);


        //Guardar datos en Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();
        guardarPuntaje(valorIdusuario, valorNombre,ValorCorreo ,nomJuego, variable_puntaje);


    }


    private void guardarPuntaje(String idUsuario, String nombreUsuario, String correoUsuario, String nombreJuego, int puntaje){
        Puntaje nuevopuntaje = new Puntaje(idUsuario, nombreUsuario, correoUsuario,nombreJuego, puntaje);
        databaseReference.child("Ranking").child(UUID.randomUUID().toString()).setValue(nuevopuntaje);

    }
}
package com.example.ulearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class activity_resultados extends AppCompatActivity {
    TextView txtResCorrectas, txtResPuntaje, txtUsuario;
    FloatingActionButton btnInicio;


    int variable_correctos, variable_puntaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);


        Bundle datos = this.getIntent().getExtras();

        txtUsuario = findViewById(R.id.txtUsuario);

        txtResCorrectas = findViewById(R.id.txtCantidadCorrectas);
        txtResPuntaje = findViewById(R.id.txtCantidadPuntaje);

        btnInicio = findViewById(R.id.btnhome);

        variable_correctos = datos.getInt("variable_correctos");
        variable_puntaje = datos.getInt("variable_puntajes");

        txtResCorrectas.setText(""+variable_correctos);
        txtResPuntaje.setText(""+variable_puntaje);

        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
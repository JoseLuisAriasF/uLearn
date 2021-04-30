package com.example.ulearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class activity_resultados extends AppCompatActivity {
    TextView txtResCorrectas, txtResPuntaje;
    FloatingActionButton btnInicio;

    int variable_correctos, variable_puntaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        Bundle datos = this.getIntent().getExtras();

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
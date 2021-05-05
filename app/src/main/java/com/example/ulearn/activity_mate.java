package com.example.ulearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class activity_mate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mate);
    }
    public void juegoSumaResta(View view){
        Intent juegoSumaResta=new Intent(this,activity_sumayresta.class);
        startActivity(juegoSumaResta);
    }

    public void Siguientestroop(View view){
        Intent juego3=new Intent(this,activity_stroop.class);
        startActivity(juego3);
    }

    public void juegoMultDiv(View view){
        Intent juegoMultDiv=new Intent(this,activity_multydiv.class);
        startActivity(juegoMultDiv);
    }

    public void juegoPuzzle(View view){
        Intent juegoPuzzle=new Intent(this,activity_puzzleNumeros.class);
        startActivity(juegoPuzzle);
    }
}
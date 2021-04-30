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
    public void Siguientejuego1(View view){
        Intent juego1=new Intent(this,activity_sumayresta.class);
        startActivity(juego1);
    }

    public void Siguientestroop(View view){
        Intent juego3=new Intent(this,activity_stroop.class);
        startActivity(juego3);
    }
}
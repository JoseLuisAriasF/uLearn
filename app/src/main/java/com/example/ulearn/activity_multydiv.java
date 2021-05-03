package com.example.ulearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class activity_multydiv extends AppCompatActivity {

    TextView primerNumero, segundoNumero, signo;
    Button respUno, respDos, respTres, respCuatro;

    int numeroUno, numeroDos, respuesta, indice;
    String signoAleatorio;

    boolean flag = true;
    char[] arraySigno = {'x', '÷'};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multydiv);

        primerNumero = (TextView)findViewById(R.id.primerNumero);
        segundoNumero = (TextView)findViewById(R.id.segundoNumero);
        signo = (TextView) findViewById(R.id.signo);
        respUno = (Button) findViewById(R.id.primerResultado);
        respDos  = (Button) findViewById(R.id.segundoResultado);
        respTres = (Button) findViewById(R.id.tercerResultado);
        respCuatro = (Button) findViewById(R.id.cuartoResultado);
    }


    public int aleatorioNumeroUno(){
        int numero = (int) (Math.random() * 20) + 1;
        return numero;
    }


    public int aleatorioNumeroDos(){
        int numero = (int) (Math.random() * 20) + 1;
        return numero;
    }

    public int aleatorioSigno(){
        int numero = (int) (Math.random() * 2) ;
        return numero;
    }



}
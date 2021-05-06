package com.example.ulearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class activity_Pupiletra extends AppCompatActivity {

    private Button[][] buttons;
    private RelativeLayout group;
    private int [] tiles;
    private TextView txtNumero1, txtNumero2, txtRespuesta;
    private int numero1, numero2, respuesta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__pupiletra);

        cargarVistas();
        cargarNumeros();
        cargarDatosEnVistas();
    }


    private void cargarVistas() {


        txtNumero1 = findViewById(R.id.txtNumero1);
        txtNumero2 = findViewById(R.id.txtNumero2);
        txtRespuesta = findViewById(R.id.txtRespuesta);
        group = findViewById(R.id.groupPupiletra);

        buttons = new Button[3][3];

        for (int i = 0; i < group.getChildCount(); i++) {
            buttons[i / 3][i % 3] = (Button) group.getChildAt(i);
        }

        numero1 = generarNumero1();
        numero2 = generarNumero2();
        respuesta = obtenerRespuesta(numero1,numero2);

        int [] numeros = {numero1, numero2, respuesta};

        txtNumero1.setText("" + numero1);
        txtNumero2.setText("" + numero2);
        txtRespuesta.setText("" + respuesta);



    }

    private int generarIndice(){
        int indice = (int) (Math.random() * 9);
        return indice;
    }

    private int generarAleatorios(){
        int numero = (int) (Math.random() * 21);
        return numero;
    }

    private int generarNumero1(){
        int numero = (int) (Math.random() * 11);
        return numero;
    }

    private int generarNumero2(){
        int numero = (int) (Math.random() * 11);
        return numero;
    }

    private int obtenerRespuesta(int numero1, int numero2){
        int respuesta = numero1 + numero2;
        return respuesta;
    }

    private void cargarNumeros(){
        tiles = new int [9];

        for(int i = 0; i < group.getChildCount(); i++){
            tiles[i] = generarAleatorios();
        }
    }

    private void  cargarDatosEnVistas(){
        int resultado;
        int numero = (int) (Math.random() * 2);
        int [] aleatorioFilasColumnas = {0,1};

        resultado = aleatorioFilasColumnas[numero];

        for(int i = 0; i < group.getChildCount(); i++){
            buttons[i/3][i%3].setText(String.valueOf(tiles[i]));
        }

        buttons[0][2].setText(String.valueOf(numero1));

        if(resultado == 0){

            buttons[0][1].setText(String.valueOf(numero2));
            buttons[0][0].setText(String.valueOf(respuesta));

        }else if(resultado == 1){
            buttons[1][2].setText(String.valueOf(numero2));
            buttons[2][2].setText(String.valueOf(respuesta));
        }





    }
}


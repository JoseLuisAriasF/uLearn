package com.example.ulearn;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class activity_memoria extends AppCompatActivity {

    Button imb00, imb01, imb02,imb03, imb04, imb05,imb06, imb07, imb08,imb09, imb10, imb11, imb12, imb13, imb14,imb15;
    Button [] tablero = new Button[16];

    Button btnreiniciar, btnsalir;
    TextView txtpuntaje;

    int puntaje, aciertos;
    int [] operacion_resultados;
    int fondo;

    ArrayList<Integer> arrayDesordenado;
    ImageButton primero;

    int numoprimero, numsegundo;
    boolean bloquear=false;
    final Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoria);

        init();

    }

    private void init(){
        cargarTablero();
        cargarBotones();
        cargarPuntaje();
        cargarFondo_Numeros();
        arrayDesordenado = barajar(operacion_resultados.length);

        for(int i = 0; i < tablero.length; i++){
            //tablero[i].setText("" + operacion_resultados[arrayDesordenado.get(i)]);
            tablero[i].setBackgroundColor(Color.GREEN);

        }


    }

    private void cargarTablero(){
        imb00 = findViewById(R.id.boton00);
        imb01 = findViewById(R.id.boton01);
        imb02 = findViewById(R.id.boton02);
        imb03 = findViewById(R.id.boton03);
        imb04 = findViewById(R.id.boton04);
        imb05 = findViewById(R.id.boton05);
        imb06 = findViewById(R.id.boton06);
        imb07 = findViewById(R.id.boton07);
        imb08 = findViewById(R.id.boton08);
        imb09 = findViewById(R.id.boton09);
        imb10 = findViewById(R.id.boton10);
        imb11 = findViewById(R.id.boton11);
        imb12 = findViewById(R.id.boton12);
        imb13 = findViewById(R.id.boton13);
        imb14 = findViewById(R.id.boton14);
        imb15 = findViewById(R.id.boton15);

        tablero[0] = imb00;
        tablero[1] = imb01;
        tablero[2] = imb02;
        tablero[3] = imb03;
        tablero[4] = imb04;
        tablero[5] = imb05;
        tablero[6] = imb06;
        tablero[7] = imb07;
        tablero[8] = imb08;
        tablero[9] = imb09;
        tablero[10] = imb10;
        tablero[11] = imb11;
        tablero[12] = imb12;
        tablero[13] = imb13;
        tablero[14] = imb14;
        tablero[15] = imb15;
    }
    private void cargarBotones(){
        btnreiniciar = findViewById(R.id.btnReiniciar);
        btnsalir = findViewById(R.id.btnSalir);

        btnreiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });


        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void cargarPuntaje(){
        txtpuntaje = findViewById(R.id.txtPuntaje);
        puntaje = 0;
        aciertos = 0;
        txtpuntaje.setText(""+puntaje);
    }
    private void cargarFondo_Numeros(){
        operacion_resultados = new int[]{85,71,65,34,40,7,30,12};
        fondo = R.drawable.fondo;
    }
    private ArrayList<Integer> barajar(int longitud){
        ArrayList<Integer> resultado = new ArrayList<Integer>();
        for(int i=0; i<longitud*2; i++){
            resultado.add(i%longitud);
        }
        Collections.shuffle(resultado);
        //System.out.println(Arrays.toString(resultado.toArray()));
        return resultado;
    }

}
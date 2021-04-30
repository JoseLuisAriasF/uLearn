package com.example.ulearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class activity_stroop extends AppCompatActivity {
    private String arregloNombres[]=new String[4];
    private int arregloColores[]=new int[4];

    LinearLayout barraSuperior;
    TextView txtCorrectas, txtIntentos, txtPalabra, txtPuntaje;
    ProgressBar pTiempo;
    Button btnBien, btnMal;
    int colorR, palabraR;
    long tiempoPalabra, tiempoJuego;
    int correctos, puntaje, intentos;
    int [] milisegundos = {0, 60000};

    boolean bandera, bandera1;
    private Handler miHandler = new Handler();
    int finalizaJuego = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stroop);
        txtCorrectas = findViewById(R.id.txtCorrectas);
        txtIntentos = findViewById(R.id.txtIntentos);

        txtPalabra = findViewById(R.id.txtPalabra);
        txtPuntaje = findViewById(R.id.txtPuntaje);
        pTiempo = findViewById(R.id.pTiempo);
        btnBien  = findViewById(R.id.btnBien);
        btnMal = findViewById(R.id.btnMal);

        barraSuperior = findViewById(R.id.barraSuperiorId);


        inicializarArreglos();
        definirAleatorios();
        inicializarValores();
        asignarValores();
        iniciarJuego();
    }
    private void inicializarArreglos() {

        arregloNombres[0]="AMARILLO";
        arregloNombres[1]="AZUL";
        arregloNombres[2]="ROJO";
        arregloNombres[3]="VERDE";

        arregloColores[0]=getResources().getColor(R.color.amarillo);
        arregloColores[1]=getResources().getColor(R.color.azul);
        arregloColores[2]=getResources().getColor(R.color.rojo);
        arregloColores[3]=getResources().getColor(R.color.verde);

    }

    private void definirAleatorios() {
        Random r=new Random();
        palabraR = r.nextInt(4);
        colorR = r.nextInt(4);

        txtPalabra.setText(arregloNombres[palabraR]);
        txtPalabra.setTextColor(arregloColores[colorR]);

    }


    private void inicializarValores() {
        tiempoPalabra = 1000;
        tiempoJuego = 10000;
        correctos = 0;
        intentos = 3;
        puntaje = 0;

        bandera = true;
        bandera1 = true;


        milisegundos[1] = (int)tiempoJuego;
        pTiempo.setMax((int)tiempoJuego);
        pTiempo.setProgress((int)tiempoJuego);

    }

    private void asignarValores() {

        txtCorrectas.setText(Integer.toString(correctos));
        txtIntentos.setText(Integer.toString(intentos));
        txtPuntaje.setText(Integer.toString(puntaje));

    }

    private void iniciarJuego(){
        final Thread miHilo=new Thread(){
            @Override
            public void run(){
                try {

                    while(bandera){
                        Thread.sleep(1);
                        ejecutarHiloJuego();
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        };
        miHilo.start();
    }


    private void ejecutarHiloJuego(){
        miHandler.post(new Runnable() {
            @Override
            public void run() {
                if(bandera1){
                    if(milisegundos[0]==tiempoPalabra){
                        milisegundos[0]=0;
                        definirAleatorios();
                        asignarValores();
                        terminarJuego();
                    }

                    milisegundos[0]++;
                    milisegundos[1]--;

                    pTiempo.setProgress(milisegundos[1]);

                    terminarJuego();
                }
            }
        });

    }

    private void terminarJuego(){
        if(finalizaJuego==0 && (intentos == 0 || milisegundos[1] == 0)){
            finalizaJuego = 1;
            bandera = false;
            bandera1 = false;
            Intent miIntent = new Intent(activity_stroop.this,activity_resultados.class);
            miIntent.putExtra("variable_correctos", correctos);
            miIntent.putExtra("variable_puntajes", puntaje);
            startActivity(miIntent);
            finish();
        }
    }


    public void onClick(View v){

        switch (v.getId()){
            case  R.id.btnBien:
                validarColores(palabraR, colorR, 1);
                break;
            case  R.id.btnMal:
                validarColores(palabraR, colorR, 2);
                break;
        }
    }

    private void validarColores(int aleatorio1, int aleatorio2, int evento) {

        if(evento == 1){
            if(aleatorio1 == aleatorio2){
                puntaje+=10;
                correctos++;
            }else{
                intentos--;
            }
        }else{
            if(aleatorio1 != aleatorio2){
                puntaje+=10;
                correctos++;
            }else{
                intentos--;
            }
        }

        terminarJuego();
        definirAleatorios();
        asignarValores();
        milisegundos[0]=0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bandera = false;
        bandera1 = false;
    }
}
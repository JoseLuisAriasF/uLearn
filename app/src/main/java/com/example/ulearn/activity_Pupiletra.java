package com.example.ulearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class activity_Pupiletra extends AppCompatActivity {


    private int emptyX = 3;
    private int emptyY = 3;
    private int indiceFilas=0, indiceColumnas;
    private int contadorX=0, contadorY=0;
    private int valorButton=0;

    private TextView textTiempo;
    private Button buttonSalir, buttonReiniciar;
    private Button[][] buttons;
    private RelativeLayout group;
    private int [] tiles;
    private int Count=0, pulsaciones=4;
    private TextView txtNumero1, txtNumero2, txtRespuesta;
    private int numero1, numero2, respuesta;

    private Timer timer;
    private int timeCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__pupiletra);

        cargarVistas();
        cargarNumero1_Numero2();
        cargarNumeros();
        cargarDatosEnVistas();
    }

    private void cargarTimer(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeCount++;
                setTime(timeCount);
            }
        }, 1000,1000);
    }

    private void setTime(int timeCount){
        int second = timeCount  % 60;
        int hour = timeCount / 3600;
        int minute = (timeCount - hour * 3600) / 60;
        textTiempo.setText(String.format("%02d:%02d:%02d", hour, minute, second));
    }


    private void cargarVistas() {

        textTiempo = findViewById(R.id.textoTiempo);
        buttonSalir = findViewById(R.id.buttonSalir);
        buttonReiniciar = findViewById(R.id.buttonReiniciar);
        txtNumero1 = findViewById(R.id.txtNumero1);
        txtNumero2 = findViewById(R.id.txtNumero2);
        txtRespuesta = findViewById(R.id.txtRespuesta);
        group = findViewById(R.id.groupPupiletra);

        buttons = new Button[5][5];

        cargarTimer();

        for (int i = 0; i < group.getChildCount(); i++) {
            buttons[i / 5][i % 5] = (Button) group.getChildAt(i);
        }


        buttonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarNumeros();
                cargarNumero1_Numero2();
                cargarDatosEnVistas();
            }
        });


    }

    private void cargarNumero1_Numero2(){
        numero1 = generarNumero1();
        numero2 = generarNumero2();
        respuesta = obtenerRespuesta(numero1,numero2);

        int [] numeros = {numero1, numero2, respuesta};

        txtNumero1.setText("?");
        txtNumero2.setText("?");
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
        Count=0;
        pulsaciones=4;
        tiles = new int [25];

        for(int i = 0; i < group.getChildCount(); i++){
            tiles[i] = generarAleatorios();
        }
    }

    private void  cargarDatosEnVistas(){

        emptyX = 3;
        emptyY = 3;

        int resultado;
        int numero = (int) (Math.random() * 2);
        int [] aleatorioFilasColumnas = {0,1};

        resultado = aleatorioFilasColumnas[numero];

        for(int i = 0; i < group.getChildCount(); i++){
            buttons[i/5][i%5].setText(String.valueOf(tiles[i]));
            buttons[i/5][i%5].setBackgroundResource(android.R.drawable.btn_default);
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

    public void buttonComprobar(View view){

        Button button = (Button) view;
        int x = button.getTag().toString().charAt(0)-'0';
        int y = button.getTag().toString().charAt(1)-'0';


        if(pulsaciones == 4){
            indiceFilas = x;
            indiceColumnas = y;
            txtNumero1.setText(button.getText());
        }

         if(pulsaciones == 3){
            txtNumero2.setText(button.getText());
        }



        if(x == indiceFilas || y == indiceColumnas){
            button.setBackgroundColor(ContextCompat.getColor(this,R.color.amarillo));
            pulsaciones -= 1;
        }

        if(pulsaciones > 1){
            valorButton += Integer.parseInt((String) button.getText());
        }


        System.out.println(pulsaciones);

        if(valorButton == respuesta && pulsaciones == 1){
            Toast.makeText(getApplicationContext(),"FELICIDADES",Toast.LENGTH_SHORT).show();
            valorButton=0;
        }


        if(pulsaciones == 0){
            for(int i = 0; i < group.getChildCount(); i++){
                buttons[i/5][i%5].setBackgroundResource(android.R.drawable.btn_default);
            }
            pulsaciones=4;
            valorButton=0;
        }
    }
}


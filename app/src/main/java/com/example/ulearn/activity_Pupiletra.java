package com.example.ulearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;

public class activity_Pupiletra extends AppCompatActivity {


    private int indiceFilas=0, indiceColumnas;
    private int valorButton=0;

    private TextView textTiempo;
    private Button buttonSalir, buttonReiniciar;
    private Button[][] buttons;
    private RelativeLayout group;
    private int [] tiles;
    private int Count=0, pulsaciones=3;
    private TextView txtNumero1, txtNumero2, txtRespuesta, txtCantidadJuego;
    private int numero1, numero2, respuesta;

    private Timer timer;
    private int timeCount = 0;
    private int valorFINAL = 0;
    private int  CantidadJuegos = 6;

    private final static String nombreJuego = "Pupiletra";

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
        txtCantidadJuego = findViewById(R.id.txtCantidadJuego);
        txtRespuesta = findViewById(R.id.txtRespuesta);

        group = findViewById(R.id.groupPupiletra);
        txtCantidadJuego.setText("" + CantidadJuegos);

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
                timeCount=-1;
                CantidadJuegos = 6;
                txtCantidadJuego.setText(CantidadJuegos);
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

        txtNumero1.setText("?");
        txtNumero2.setText("?");
        txtRespuesta.setText("" + respuesta);

    }

    private int generarAleatorios(){
        int numero = (int) (Math.random() * 21)+1;
        return numero;
    }

    private int generarNumero1(){
        int numero = (int) (Math.random() * 11)+1;
        return numero;
    }

    private int generarNumero2(){
        int numero = (int) (Math.random() * 11)+1;
        return numero;
    }

    private int obtenerRespuesta(int numero1, int numero2){
        int respuesta = numero1 + numero2;
        return respuesta;
    }

    private void cargarNumeros(){
        Count=0;
        pulsaciones=3;
        tiles = new int [25];

        for(int i = 0; i < group.getChildCount(); i++){
            tiles[i] = generarAleatorios();
        }
    }

    private void  cargarDatosEnVistas(){

        boolean flag=false;
        int comprobarIZQUIERDA = 0;
        int comprobarDERECHA = 0;
        int comprobarARRIBA = 0;
        int comprobarABAJO = 0;

        int aleatorioFILA = (int) (Math.random() * 5);
        int aleatorioCOLUMNA = (int) (Math.random() * 5);

        for(int i = 0; i < group.getChildCount(); i++){
            buttons[i/5][i%5].setText(String.valueOf(tiles[i]));
            buttons[i/5][i%5].setBackgroundResource(android.R.drawable.btn_default);
        }

        buttons[aleatorioFILA][aleatorioCOLUMNA].setText(String.valueOf(numero1));

        while(flag!=true){

            int elegirVALOR = (int) (Math.random() * 4);

            if(elegirVALOR == 0){

                comprobarDERECHA = aleatorioCOLUMNA+2;

                if(comprobarDERECHA<=4){
                    buttons[aleatorioFILA][aleatorioCOLUMNA+1].setText(String.valueOf(numero2));
                    buttons[aleatorioFILA][aleatorioCOLUMNA+2].setText(String.valueOf(respuesta));
                    flag = true;
                }

            } else if(elegirVALOR == 1){

                comprobarIZQUIERDA = aleatorioCOLUMNA-2;

                if(comprobarIZQUIERDA>=0){
                    buttons[aleatorioFILA][aleatorioCOLUMNA-1].setText(String.valueOf(numero2));
                    buttons[aleatorioFILA][aleatorioCOLUMNA-2].setText(String.valueOf(respuesta));
                    flag = true;
                }

            }else if(elegirVALOR == 3){

                comprobarARRIBA= aleatorioFILA-2;

                if(comprobarARRIBA>=0){
                    buttons[aleatorioFILA-1][aleatorioCOLUMNA].setText(String.valueOf(numero2));
                    buttons[aleatorioFILA-2][aleatorioCOLUMNA].setText(String.valueOf(respuesta));
                    flag = true;
                }


            }else if(elegirVALOR == 4){
                comprobarABAJO= aleatorioFILA+2;

                if(comprobarABAJO<=4){
                    buttons[aleatorioFILA+1][aleatorioCOLUMNA].setText(String.valueOf(numero2));
                    buttons[aleatorioFILA+2][aleatorioCOLUMNA].setText(String.valueOf(respuesta));
                    flag = true;
                }
            }
        }
    }

    public void buttonComprobar(View view){

        Button button = (Button) view;
        int x = button.getTag().toString().charAt(0)-'0';
        int y = button.getTag().toString().charAt(1)-'0';

        if(pulsaciones == 3){
            indiceFilas = x;
            indiceColumnas = y;
            button.setBackgroundColor(ContextCompat.getColor(this,R.color.amarillo));
            Toast.makeText(getApplicationContext(),"TIMER: " + timeCount,Toast.LENGTH_SHORT).show();
            txtNumero1.setText(button.getText());
            pulsaciones -= 1;
        }else if(pulsaciones == 2){
            button.setBackgroundColor(ContextCompat.getColor(this,R.color.amarillo));
            txtNumero2.setText(button.getText());
            pulsaciones -= 1;
        }else if(pulsaciones == 1){
            button.setBackgroundColor(ContextCompat.getColor(this,R.color.amarillo));
            valorFINAL  = Integer.parseInt((String) button.getText());
            pulsaciones -= 1;
        }

        /*if(x == indiceFilas || y == indiceColumnas){
            button.setBackgroundColor(ContextCompat.getColor(this,R.color.amarillo));
            pulsaciones -= 1;
        }*/

        if(pulsaciones > 0){
            valorButton += Integer.parseInt((String) button.getText());
        }

        if(valorButton == respuesta && pulsaciones == 0){
            for(int i = 0; i < group.getChildCount(); i++){
                buttons[i/5][i%5].setBackgroundResource(android.R.drawable.btn_default);
            }
            txtNumero2.setText("?");
            txtNumero1.setText("?");
            pulsaciones=3;
            valorButton=0;
            CantidadJuegos -= 1;
            txtCantidadJuego.setText("" + CantidadJuegos);
            cargarNumeros();
            cargarNumero1_Numero2();
            cargarDatosEnVistas();
            Toast.makeText(getApplicationContext(),"Felicidades, respuesta correcta",Toast.LENGTH_SHORT).show();
        }

        if(pulsaciones == 0 && valorButton   != respuesta){
            for(int i = 0; i < group.getChildCount(); i++){
                buttons[i/5][i%5].setBackgroundResource(android.R.drawable.btn_default);
            }
            txtNumero2.setText("?");
            txtNumero1.setText("?");
            pulsaciones=3;
            valorButton=0;
            Toast.makeText(getApplicationContext(),"Respuesta incorrecta, inténtalo de nuevo",Toast.LENGTH_SHORT).show();
        }

        if(CantidadJuegos == 0){

            timer.cancel();
            String tiempo = textTiempo.getText().toString();
            int puntaje=0;

            if(timeCount<=60){
                puntaje=200;
            }else{
                puntaje=100;
            }


            Intent miIntent = new Intent(activity_Pupiletra.this,activity_resultados.class);
            miIntent.putExtra("variable_nomJuego",nombreJuego);
            miIntent.putExtra("variable_correctos",tiempo);
            miIntent.putExtra("variable_puntajes", puntaje);
            startActivity(miIntent);
            finish();

        }
    }
}


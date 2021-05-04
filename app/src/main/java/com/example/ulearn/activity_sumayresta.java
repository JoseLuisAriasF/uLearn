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
import android.widget.Toast;

public class activity_sumayresta extends AppCompatActivity {
    TextView primerNumero, segundoNumero, signo;
    Button respUno, respDos, respTres, respCuatro;

    ProgressBar pTiempo;
    TextView txtCorrectas, txtIntentos, txtPuntaje;
    int [] milisegundos = {0, 60000};
    long tiempoJuego;
    boolean bandera, bandera1;
    private Handler miHandler = new Handler();
    int finalizaJuego = 0;
    int correctos, puntaje, intentos;

    int numeroUno, numeroDos, respuesta, indice;
    String signoAleatorio;
    boolean flag = true, verificar;
    char[] arraySigno = {'+', '-'};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumayresta);

        txtCorrectas = findViewById(R.id.txtCorrectas);
        txtIntentos = findViewById(R.id.txtIntentos);
        txtPuntaje = findViewById(R.id.txtPuntaje);
        pTiempo = findViewById(R.id.pTiempo);

        primerNumero = (TextView)findViewById(R.id.primerNumero);
        segundoNumero = (TextView)findViewById(R.id.segundoNumero);
        signo = (TextView) findViewById(R.id.signo);
        respUno = (Button) findViewById(R.id.primerResultado);
        respDos  = (Button) findViewById(R.id.segundoResultado);
        respTres = (Button) findViewById(R.id.tercerResultado);
        respCuatro = (Button) findViewById(R.id.cuartoResultado);

        inicializarValores();
        asignarValores();
        iniciarJuego();
        Operaciones();

    }

    private void inicializarValores() {
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
            Intent miIntent = new Intent(activity_sumayresta.this,activity_resultados.class);
            miIntent.putExtra("variable_correctos", correctos);
            miIntent.putExtra("variable_puntajes", puntaje);
            startActivity(miIntent);
            finish();
        }
    }


    public void Operaciones(){

        verificar = false;

        numeroUno = aleatorioNumeroUno();
        numeroDos = aleatorioNumeroDos();
        signoAleatorio = String.valueOf(arraySigno[aleatorioSigno()]);
        indice = aleatorioPosicion();

        while (flag){
            if(signoAleatorio.equals("+")){
                respuesta = numeroUno + numeroDos;
                flag = false;
            }else if(signoAleatorio.equals("-")){

                if(numeroUno < numeroDos){
                    numeroUno = aleatorioNumeroUno();
                    numeroDos = aleatorioNumeroDos();
                }else{
                    flag=false;
                }
                respuesta = numeroUno - numeroDos;
            }
        }

        switch (indice){
            case 0:
                respUno.setText("" + respuesta);
                break;
            case 1:
                respDos.setText("" + respuesta);
                break;
            case 2:
                respTres.setText("" + respuesta);
                break;
            case 3:
                respCuatro.setText("" + respuesta);
                break;
        }

        if(respUno.getText().equals(String.valueOf(respuesta))){

            int valor2, valor3, valor4;
            valor2 = aleatorioRespuesta();
            valor3 = aleatorioRespuesta();
            valor4 = aleatorioRespuesta();

            while(verificar==false){
                if(valor2 != respuesta && valor2 != valor3 && valor2 != valor4 &&
                        valor3 != respuesta && valor3 != valor4 && valor4 != respuesta){
                    verificar = true;
                }else{
                    valor2 = aleatorioRespuesta();
                    valor3 = aleatorioRespuesta();
                    valor4 = aleatorioRespuesta();
                }
            }


            respDos.setText(""+valor2);
            respTres.setText(""+valor3);
            respCuatro.setText(""+valor4);


        }else if(respDos.getText().equals(String.valueOf(respuesta))){
            int valor1, valor3, valor4;
            valor1 = aleatorioRespuesta();
            valor3 = aleatorioRespuesta();
            valor4 = aleatorioRespuesta();

            while(verificar==false){
                if(valor1 != respuesta && valor1 != valor3 && valor1 != valor4 &&
                        valor3 != respuesta && valor3 != valor4 && valor4 != respuesta){
                    verificar = true;
                }else{
                    valor1 = aleatorioRespuesta();
                    valor3 = aleatorioRespuesta();
                    valor4 = aleatorioRespuesta();
                }
            }



            respTres.setText(""+valor3);
            respUno.setText(""+valor1);
            respCuatro.setText(""+valor4);


        }else if(respTres.getText().equals(String.valueOf(respuesta))){
            int valor2, valor1, valor4;
            valor2 = aleatorioRespuesta();
            valor1 = aleatorioRespuesta();
            valor4 = aleatorioRespuesta();

            while(verificar==false){
                if(valor2 != respuesta && valor2 != valor1 && valor2 != valor4 &&
                        valor1 != respuesta && valor1 != valor4 && valor4 != respuesta){
                    verificar = true;
                }else{
                    valor2 = aleatorioRespuesta();
                    valor1 = aleatorioRespuesta();
                    valor4 = aleatorioRespuesta();
                }
            }


            respDos.setText(""+valor2);
            respUno.setText(""+valor1);
            respCuatro.setText(""+valor4);


        }else if(respCuatro.getText().equals(String.valueOf(respuesta))){
            int valor2, valor3, valor1;
            valor2 = aleatorioRespuesta();
            valor3 = aleatorioRespuesta();
            valor1 = aleatorioRespuesta();

            while(verificar==false){
                if(valor2 != respuesta && valor2 != valor3 && valor2 != valor1 &&
                        valor3 != respuesta && valor3 != valor1 && valor1 != respuesta){
                    verificar = true;
                }else{
                    valor2 = aleatorioRespuesta();
                    valor3 = aleatorioRespuesta();
                    valor1 = aleatorioRespuesta();
                }
            }


            respDos.setText(""+valor2);
            respTres.setText(""+valor3);
            respUno.setText(""+valor1);

        }

        primerNumero.setText("" + numeroUno);
        segundoNumero.setText("" + numeroDos);
        signo.setText("" + signoAleatorio);

        flag=true;
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

    public int aleatorioPosicion(){
        int numero = (int) (Math.random() * 4) ;
        return numero;
    }

    public int aleatorioRespuesta(){
        int numero = (int) (Math.random() * 41) ;
        return numero;
    }


    public void operacionUno(View view){

        int numero = Integer.parseInt((String) respUno.getText());

        if(respuesta == numero) {

            Operaciones();
            puntaje+=10;
            correctos++;

        }else{
            intentos--;
            Toast.makeText(getApplicationContext(),"Respuesta incorrecta",Toast.LENGTH_SHORT).show();
        }

        asignarValores();
    }

    public void operacionDos(View view){

        int numero = Integer.parseInt((String) respDos.getText());

        if(respuesta == numero) {

            Operaciones();
            puntaje+=10;
            correctos++;

        }else{
            intentos--;
            Toast.makeText(getApplicationContext(),"Respuesta incorrecta",Toast.LENGTH_SHORT).show();
        }

        asignarValores();
    }

    public void operacionTres(View view){

        int numero = Integer.parseInt((String) respTres.getText());

        if(respuesta == numero) {

            Operaciones();
            puntaje+=10;
            correctos++;

        }else{
            intentos--;
            Toast.makeText(getApplicationContext(),"Respuesta incorrecta",Toast.LENGTH_SHORT).show();
        }

        asignarValores();
    }

    public void operacionCuatro(View view){

        int numero = Integer.parseInt((String) respCuatro.getText());

        if(respuesta == numero) {

            Operaciones();
            puntaje+=10;
            correctos++;

        }else{
            intentos--;
            Toast.makeText(getApplicationContext(),"Respuesta incorrecta",Toast.LENGTH_SHORT).show();
        }

        asignarValores();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bandera = false;
        bandera1 = false;
    }
}
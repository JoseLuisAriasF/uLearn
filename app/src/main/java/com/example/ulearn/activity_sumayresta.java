package com.example.ulearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class activity_sumayresta extends AppCompatActivity {
    TextView primerNumero, segundoNumero, signo;
    Button respUno, respDos, respTres, respCuatro;

    int numeroUno, numeroDos, respuesta, indice;
    String signoAleatorio;
    boolean flag = true;
    char[] arraySigno = {'+', '-'};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumayresta);
        primerNumero = (TextView)findViewById(R.id.primerNumero);
        segundoNumero = (TextView)findViewById(R.id.segundoNumero);
        signo = (TextView) findViewById(R.id.signo);
        respUno = (Button) findViewById(R.id.primerResultado);
        respDos  = (Button) findViewById(R.id.segundoResultado);
        respTres = (Button) findViewById(R.id.tercerResultado);
        respCuatro = (Button) findViewById(R.id.cuartoResultado);

        Operaciones();

    }
    public void Operaciones(){

        numeroUno = aleatorioNumeroUno();
        numeroDos = aleatorioNumeroDos();
        signoAleatorio = String.valueOf(arraySigno[aleatorioSigno()]);
        indice = aleatorioPosicion();

        while (flag){
            if(numeroUno < numeroDos){
                numeroUno = aleatorioNumeroUno();
                numeroDos = aleatorioNumeroDos();
            }else{
                break;
            }
        }

        if(signoAleatorio.equals("+")){
            respuesta = numeroUno + numeroDos;
        }else if(signoAleatorio.equals("-")){
            respuesta = numeroUno - numeroDos;
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
            respDos.setText(""+aleatorioRespuesta());

            respTres.setText(""+aleatorioRespuesta());

            respCuatro.setText(""+aleatorioRespuesta());


        }else if(respDos.getText().equals(String.valueOf(respuesta))){
            respUno.setText(""+aleatorioRespuesta());

            respTres.setText(""+aleatorioRespuesta());

            respCuatro.setText(""+aleatorioRespuesta());


        }else if(respTres.getText().equals(String.valueOf(respuesta))){
            respUno.setText(""+aleatorioRespuesta());

            respDos.setText(""+aleatorioRespuesta());

            respCuatro.setText(""+aleatorioRespuesta());


        }else if(respCuatro.getText().equals(String.valueOf(respuesta))){
            respUno.setText(""+aleatorioRespuesta());

            respDos.setText(""+aleatorioRespuesta());

            respTres.setText(""+aleatorioRespuesta());

        }

        primerNumero.setText("" + numeroUno);
        segundoNumero.setText("" + numeroDos);
        signo.setText("" + signoAleatorio);
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

        }else{
            Toast.makeText(getApplicationContext(),"HAS xddd",Toast.LENGTH_SHORT).show();
        }
    }

    public void operacionDos(View view){

        int numero = Integer.parseInt((String) respDos.getText());

        if(respuesta == numero) {

            Operaciones();

        }else{
            Toast.makeText(getApplicationContext(),"HAS xxxx",Toast.LENGTH_SHORT).show();
        }
    }

    public void operacionTres(View view){

        int numero = Integer.parseInt((String) respTres.getText());

        if(respuesta == numero) {

            Operaciones();

        }else{
            Toast.makeText(getApplicationContext(),"HAS sdsd",Toast.LENGTH_SHORT).show();
        }
    }

    public void operacionCuatro(View view){

        int numero = Integer.parseInt((String) respCuatro.getText());

        if(respuesta == numero) {

            Operaciones();

        }else{
            Toast.makeText(getApplicationContext(),"HAS PEsdsdsRDIDO",Toast.LENGTH_SHORT).show();
        }
    }
}
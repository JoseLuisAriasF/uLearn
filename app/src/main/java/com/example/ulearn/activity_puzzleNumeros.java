package com.example.ulearn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class activity_puzzleNumeros extends AppCompatActivity {

    private int emptyX = 2;
    private int emptyY = 2;
    private RelativeLayout group;
    private Button [][] buttons;
    private Button btnRestart, btnExit;
    private int [] tiles;
    private TextView textMov, textTime;
    private int MovCount = 0;
    private Timer timer;
    private int timeCount = 0;

    Handler handler = new Handler();
    Runnable runnable;
    int delay = 240000;

    int movimientos, puntaje;
    private final static String nombreJuego = "Puzzle de Numeros";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_numeros);

        cargarVistas();
        cargarNumeros();
        generarNumeros();
        cargarDatosEnVistas();

        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                //handler.postDelayed(runnable, delay);

                movimientos = MovCount;
                puntaje = 0;

                Toast.makeText(getApplicationContext(),"Se terminó el tiempo, has perdido", Toast.LENGTH_SHORT).show();
                timer.cancel();

                Intent miIntent = new Intent(activity_puzzleNumeros.this,activity_resultados.class);
                miIntent.putExtra("variable_nomJuego", nombreJuego);
                miIntent.putExtra("variable_correctos", movimientos);
                miIntent.putExtra("variable_puntajes", puntaje);
                startActivity(miIntent);
                finish();
            }
        }, delay);
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
        textTime.setText(String.format("%02d:%02d:%02d", hour, minute, second));

    }

    private void cargarVistas(){

        textMov = findViewById(R.id.txtMovimientos);
        textTime = findViewById(R.id.txtTiempo);
        group=findViewById(R.id.group);
        btnRestart = findViewById(R.id.btnNuevo);
        btnExit = findViewById(R.id.btnSalir);


        cargarTimer();
        buttons = new Button[3][3];

        for(int i = 0; i < group.getChildCount(); i++){
            buttons[i/3][i%3] = (Button) group.getChildAt(i);
        }

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               generarNumeros();
               cargarDatosEnVistas();
                timeCount = 0;
            }
        });
    }

    private void cargarNumeros(){
        tiles = new int [9];
        for(int i = 0; i < group.getChildCount() - 1 ; i ++){
            tiles[i] = i + 1;
        }
    }

    private void generarNumeros(){
        int n = 8;
        Random random = new Random();
        while(n > 1){
            int randomNum = random.nextInt(n--);
            int temp  = tiles[randomNum];
            tiles[randomNum] = tiles[n];
            tiles[n] = temp;
        }

        if(!Flag())
            generarNumeros();
    }

    private boolean Flag(){
        int count = 0;
        for(int i = 0; i < 8; i ++){
            for(int j = 0; j < i; j++){
                if(tiles[j] > tiles[i]){
                    count++;
                }
            }
        }
        return count % 2 == 0;
    }

    private void  cargarDatosEnVistas(){
        emptyX = 2;
        emptyY = 2;

        for(int i = 0; i < group.getChildCount() -1; i++){
            buttons[i/3][i%3].setText(String.valueOf(tiles[i]));
            buttons[i/3][i%3].setBackgroundResource(android.R.drawable.btn_default);
        }
        buttons[emptyX][emptyY].setText("");
        buttons[emptyX][emptyY].setBackgroundColor(ContextCompat.getColor(this,R.color.colorFreeButton));
    }

    public void buttonClick(View view){
        Button button = (Button) view;
        int x = button.getTag().toString().charAt(0)-'0';
        int y = button.getTag().toString().charAt(1)-'0';

        if((Math.abs(emptyX-x) == 1 && emptyY == y) || (Math.abs(emptyY-y) == 1 && emptyX == x)){
            buttons[emptyX][emptyY].setText(button.getText().toString());
            buttons[emptyX][emptyY].setBackgroundResource(android.R.drawable.btn_default);
            button.setText("");
            button.setBackgroundColor(ContextCompat.getColor(this,R.color.colorFreeButton));
            emptyX=x;
            emptyY=y;
            MovCount++;
            textMov.setText(""+ MovCount);
            checkWin();
        }
    }

    private void checkWin(){
        boolean Win = false;

        if(emptyX == 2 && emptyY == 2){
            for(int i = 0; i < group.getChildCount() - 1; i++){
                if(buttons[i/3][i%3].getText().toString().equals(String.valueOf(i+1))){
                    Win = true;
                }else{
                    Win = false;
                    break;
                }
            }
        }

        if(Win){
            Toast.makeText(this,"Felicidades, has ganado", Toast.LENGTH_SHORT).show();
            for(int i = 0; i < group.getChildCount(); i++){
                buttons[i/3][i%3].setClickable(false);
            }

            timer.cancel();

            movimientos = MovCount;

            if(movimientos <= 200){
                puntaje = 200;
            }else if(puntaje <= 400){
                puntaje = 100;
            }else {
                puntaje = 50;
            }

            Intent miIntent = new Intent(activity_puzzleNumeros.this,activity_resultados.class);
            miIntent.putExtra("variable_nomJuego", nombreJuego);
            miIntent.putExtra("variable_correctos", movimientos);
            miIntent.putExtra("variable_puntajes", puntaje);
            startActivity(miIntent);
            finish();
        }
    }

    public AlertDialog dialogHelp(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater imagen_alert = LayoutInflater.from(activity_puzzleNumeros.this);
        final View Img = imagen_alert.inflate(R.layout.imghelp,null);
        builder.setView(Img);
        builder.setTitle("Ayuda").setMessage("Se debe ordenar los numeros del 1 al 8. De la siguiente manera:")
        .setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder.create();
    }

    public void Help(View view){
        dialogHelp().show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);

    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
    }
}
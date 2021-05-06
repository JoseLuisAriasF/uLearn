package com.example.ulearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class activity_extras extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extras);
    }

    public void juegoStroop(View view){
        Intent juegoStroop=new Intent(this,activity_stroop.class);
        startActivity(juegoStroop);
    }

}
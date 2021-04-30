package com.example.ulearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class activity_edadhijo extends AppCompatActivity {
    private Spinner SninnerHead;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edadhijo);


    }

    public void SiguienteLogin(View view){
        Intent siguiente=new Intent(this,activity_login.class);
        startActivity(siguiente);
    }


}
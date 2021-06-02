package com.example.ulearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import kotlinx.coroutines.AwaitKt;

public class activity_register extends AppCompatActivity {

    private EditText edNombre, edCorreo, edPassword;
    private Button btnRegistrar;

    private String nombre = "";
    private String correo = "";
    private String password = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edNombre = findViewById(R.id.inputNombre);
        edCorreo = findViewById(R.id.inputCorreo);
        edPassword = findViewById(R.id.inputPassword);
        btnRegistrar = findViewById(R.id.btnregistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = edNombre.getText().toString();
                correo = edNombre.getText().toString();
                password = edNombre.getText().toString();

                if(!nombre.isEmpty() && !correo.isEmpty() && !password.isEmpty()){
                    if(password.length() >= 6){
                        registrarUsuario();
                    }else{
                        Toast.makeText(getApplicationContext(), "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                    }


                }else{
                    Toast.makeText(getApplicationContext(), "Debe completar los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    private void registrarUsuario() {
    }

}
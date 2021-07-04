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
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import kotlinx.coroutines.AwaitKt;

public class    activity_register extends AppCompatActivity {

    private EditText edNombre, edCorreo, edPassword;
    private Button btnRegistrar;

    private String nombre = "";
    private String correo = "";
    private String password = "";


    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        edNombre = findViewById(R.id.inputNombre);
        edCorreo = findViewById(R.id.inputCorreo);
        edPassword = findViewById(R.id.inputPassword);
        btnRegistrar = findViewById(R.id.btnregistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = edNombre.getText().toString();
                correo = edCorreo.getText().toString();
                password = edPassword.getText().toString();

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

        mAuth.createUserWithEmailAndPassword(correo,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    String id = mAuth.getCurrentUser().getUid();

                    Map<String, Object> map = new HashMap<>();
                    map.put("id", id);
                    map.put("nombre", nombre);
                    map.put("correo", correo);
                    map.put("password", password);


                    mDatabase.child("Usuario").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                startActivity(new Intent(getApplicationContext(),activity_login.class));
                            }else{
                                Toast.makeText(activity_register.this, "No se pudo crear los datos", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else{
                    Toast.makeText(activity_register.this, "No se puede registrar este usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}
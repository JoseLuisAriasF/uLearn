package com.example.ulearn;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class activity_splash1 extends AppCompatActivity {
    Animation topanim;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash1);
        //Animations
        topanim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        logo=findViewById(R.id.iv_logo);
        logo.setAnimation(topanim);


        espera5s();

    }

    private void displayData() {

        Intent loginIntent = new Intent (this, activity_splash.class);
        startActivity(loginIntent);
        //  Log.i("User Logged In", "False");


    }
    private void espera5s(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                displayData();
            }
        }, 1500);
    }
}
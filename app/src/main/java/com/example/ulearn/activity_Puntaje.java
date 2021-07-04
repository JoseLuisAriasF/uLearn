package com.example.ulearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class activity_Puntaje extends AppCompatActivity {

    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntaje);


        mDatabase = FirebaseDatabase.getInstance().getReference();




    }


    private void getRankingInfo(){

        ListView mListView = findViewById(R.id.listView);
        ArrayList<Puntaje> puntajeList = new ArrayList<>();

        mDatabase.child("Ranking").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {

                for(final DataSnapshot snapshot : dataSnapshot.getChildren()){

                    mDatabase.child("Ranking").child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                            Puntaje punt = snapshot.getValue(Puntaje.class);
                            puntajeList.add(punt);


                            //PuntajeListAdapter adapter = new PuntajeListAdapter(this,R.layout.adapter_view_layout, punt);
                            //mListView.setAdapter(adapter);

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

}
package com.example.ulearn;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class PuntajeListAdapter extends ArrayAdapter<Puntaje> {

    private static final String TAG = "PuntajeListAdapter";

    private Context mContext;


    public PuntajeListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Puntaje> objects) {
        super(context, resource, textViewResourceId, objects);
    }
}

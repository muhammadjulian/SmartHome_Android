package com.example.smart;

import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Lamp extends AppCompatActivity {
    Button b_on;
    Button b_off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp);

        final ImageView image = (ImageView) findViewById(R.id.image);
        b_on = (Button)findViewById(R.id.tombol_on);
        b_off = (Button) findViewById(R.id.tombol_off);

        b_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionDrawable drawable =(TransitionDrawable) image.getDrawable();
                drawable.startTransition(1000);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("LED_STATUS");
                myRef.setValue(1);

                Toast.makeText(Lamp.this, "Lamp is ON", Toast.LENGTH_SHORT);
            }
        });

        b_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionDrawable drawable =(TransitionDrawable) image.getDrawable();
                drawable.reverseTransition(1000);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("LED_STATUS");
                myRef.setValue(0);

                Toast.makeText(Lamp.this, "Lamp is OFF", Toast.LENGTH_SHORT);
            }
        });
    }
}
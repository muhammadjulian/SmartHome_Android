package com.example.smart;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Situation extends AppCompatActivity {

    DatabaseReference databaseReference;
    TextView distance;
    TextView keadaan;
    TextView ir;

    String angka;
    int status,orang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation);

        databaseReference=FirebaseDatabase.getInstance().getReference();
        distance=(TextView)findViewById(R.id.distance);
        keadaan=(TextView)findViewById(R.id.keadaan);
        ir=(TextView)findViewById(R.id.ir);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                angka = dataSnapshot.child("distance").getValue().toString();
                status = dataSnapshot.child("keadaan").getValue().hashCode();
                orang = dataSnapshot.child("InfraRed").getValue().hashCode();

                distance.setText(angka);

                if (status == 1) {
                    keadaan.setText("Komputer Hilang");
                } else {
                    keadaan.setText("Komputer Aman");
                }

                if (orang == 1) {
                    ir.setText("Ada Orang");
                } else {
                    ir.setText("Tidak Ada Orang");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

}

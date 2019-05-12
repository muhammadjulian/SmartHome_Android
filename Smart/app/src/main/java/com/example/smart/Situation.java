package com.example.smart;

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
    String angka;
    int status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation);

        databaseReference=FirebaseDatabase.getInstance().getReference();
        distance=(TextView)findViewById(R.id.distance);
        keadaan=(TextView)findViewById(R.id.keadaan);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                angka = dataSnapshot.child("distance").getValue().toString();
                status = dataSnapshot.child("keadaan").getValue().hashCode();
                distance.setText(angka);
                if (status == 1) {
                    keadaan.setText("Komputer Hilang");
                } else {
                    keadaan.setText("Komputer Aman");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
}

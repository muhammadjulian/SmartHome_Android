package com.example.smart;

import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Utama extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    final DatabaseReference ledstatus = myRef.child("led").child("status");

    Button b_on;
    Button b_off;
    TextView textView1;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);

        //b_on = (Button)findViewById(R.id.button_on);
        //b_off = (Button)findViewById(R.id.button_off);

        //textView1 = (TextView)findViewById(R.id.textView1);
        //textView2 = (TextView)findViewById(R.id.textView2);

        final ImageView image = (ImageView) findViewById(R.id.image);
        final ToggleButton button = (ToggleButton) findViewById(R.id.tooglebutton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionDrawable drawable =(TransitionDrawable) image.getDrawable();
                if(button.isChecked()){
                    drawable.startTransition(1000);
                } else {
                    drawable.reverseTransition(1000);
                }
            }
        });

        ledstatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("file", "Value is: " + value);
                textView1.setText(value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("file", "Failed to read value.", error.toException());
            }
        });

       // b_on.setOnClickListener(new View.OnClickListener() {
         //   @Override
         //   public void onClick(View view) {
        //        ledstatus.setValue("ON");
       //     }
      //  });

      //  b_off.setOnClickListener(new View.OnClickListener() {
     //       @Override
     //       public void onClick(View view) {
     //           ledstatus.setValue("OFF");
     //       }
    //    });



    }
}
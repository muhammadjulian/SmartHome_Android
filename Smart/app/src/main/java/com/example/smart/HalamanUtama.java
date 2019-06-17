package com.example.smart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;


public class HalamanUtama extends AppCompatActivity {

    GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_utama);

        final ImageView lamp=(ImageView) findViewById(R.id.tombol_lamp);
        final ImageView situation=(ImageView) findViewById(R.id.tombol_situation);

        gridLayout=(GridLayout)findViewById(R.id.mainGrid);

        setSingleEvent(gridLayout);

        lamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lampIntent = new Intent(HalamanUtama.this,Lamp.class);
                HalamanUtama.this.startActivity(lampIntent);
            }
        });
        situation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent situationIntent = new Intent(HalamanUtama.this,Situation.class);
                HalamanUtama.this.startActivity(situationIntent);
            }
        });
    }

    // we are setting onClickListener for each element
    private void setSingleEvent(GridLayout gridLayout) {
        for(int i = 0; i<gridLayout.getChildCount();i++){
            CardView cardView=(CardView)gridLayout.getChildAt(i);
            final int finalI= i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(HalamanUtama.this,"Clicked at index "+ finalI,
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private boolean mOrderMessage;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_lamp:
                Intent intent = new Intent(HalamanUtama.this, Lamp.class);
                intent.putExtra(EXTRA_MESSAGE, mOrderMessage);
                startActivity(intent);
                displayToast(getString(R.string.action_lamp));
                return true;
            case R.id.action_situation:
                Intent intent2 = new Intent(HalamanUtama.this, Situation.class);
                intent2.putExtra(EXTRA_MESSAGE, mOrderMessage);
                startActivity(intent2);
                displayToast(getString(R.string.action_situation));
                return true;
            default:
        }

        return super.onOptionsItemSelected(item);
    }

    public void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}

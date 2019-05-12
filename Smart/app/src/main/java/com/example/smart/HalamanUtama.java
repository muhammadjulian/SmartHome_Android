package com.example.smart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class HalamanUtama extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_utama);

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

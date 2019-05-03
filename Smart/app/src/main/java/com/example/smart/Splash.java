package com.example.smart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    private TextView tvSplash;
    private ImageView ivSplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tvSplash = (TextView) findViewById(R.id.tvSplash);
        ivSplash = (ImageView) findViewById(R.id.ivSplash);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.transition);
        tvSplash.startAnimation(animation);
        ivSplash.startAnimation(animation);
        final Intent i = new Intent(this,Login.class);
        Thread timer =new Thread(){
            public void run() {
                try {
                    sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }

}

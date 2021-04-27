package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private int Sleep_Timer = 3;

    private static final String TAG = "slashscreen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();
    }

    private void setupUI()
    {

    }

    private class LogoLauncher extends Thread{
        public void run(){
            try{
                sleep(1000 * Sleep_Timer);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            Intent intent = new Intent(MainActivity.this,login.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
    }

}
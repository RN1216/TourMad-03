package com.example.final_project_tourmate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class SplashScreenActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private  int progressTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN) ;
        setContentView(R.layout.activity_splash_screen);
        
        progressBar = findViewById(R.id.progressBar);

        Thread thread =new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startApp();
            }
        });

        thread.start();
    }



    private void doWork()  {

        for (progressTime=20; progressTime<=100;progressTime=progressTime+20){

        try {
            Thread.sleep(1000);
            progressBar.setProgress(progressTime);
        }catch (InterruptedException e){

            e.printStackTrace();
        }
    }

    }

    private void startApp() {

        Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
        startActivity(intent);
        finish();

    }

}

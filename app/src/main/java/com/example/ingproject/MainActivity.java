package com.example.ingproject;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ingproject.Views.PostsView;
import com.spark.submitbutton.SubmitButton;


public class MainActivity extends AppCompatActivity {
    SubmitButton goPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goPosts = findViewById(R.id.goPosts);

        goPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Thread.sleep(3500);

                            Intent intent = new Intent(MainActivity.this, Activity.class);
                            startActivity(intent);
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();

            }
        });

    }


}

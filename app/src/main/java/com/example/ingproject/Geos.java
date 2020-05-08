package com.example.ingproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ingproject.Adapters.PhotoAdapter;
import com.example.ingproject.Adapters.UserAdapter;
import com.example.ingproject.Models.Photo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Geos extends AppCompatActivity {
    public Button button;
    public ListView listView;
    private UserAdapter userAdapter;
    private JsonPlaceholderAPI api;
    private PhotoAdapter photoAdapter;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        listView = findViewById(R.id.photoView);
        imageView=findViewById(R.id.photo);
        button = findViewById(R.id.backToUsers);
        final Animation animTranslate2 = AnimationUtils.loadAnimation(this, R.anim.animation1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animTranslate2);
                goToUsers();
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JsonPlaceholderAPI.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(JsonPlaceholderAPI.class);

        getGeos();

    }


    private void getGeos(){


    }





    public void goToUsers(){
        Intent intent = new Intent(this, Users.class);
        startActivity(intent);
    }
}

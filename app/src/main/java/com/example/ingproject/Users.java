package com.example.ingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.ingproject.Adapters.PostAdapter;
import com.example.ingproject.Adapters.UserAdapter;
import com.example.ingproject.Models.User;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Users extends AppCompatActivity implements View.OnClickListener {

    public Button button;
    public static ListView listView;
    private UserAdapter userAdapter;
    private JsonPlaceholderAPI api;
    private Context context;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent= getIntent();
        Bundle pos = intent.getExtras();
init();
        assert pos != null;
        getUsers(pos.getInt("positionUser"));

    }

    public void getUsers(int position){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JsonPlaceholderAPI.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(JsonPlaceholderAPI.class);
                Call<User[]> usercall = api.getUser(position);
                usercall.enqueue(new Callback<User[]>() {
                    @Override
                    public void onResponse(Call<User[]> call, Response<User[]> response) {
                        assert response.body() != null;
                        User[] userArray = response.body();
                        userAdapter = new UserAdapter(getBaseContext(), userArray);
                        listView.setAdapter(userAdapter);
                    }

                    @Override
                    public void onFailure(Call<User[]> call, Throwable t) {

                    }

                });


            }





    public void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void init(){
        setContentView(R.layout.activity_users);
        listView = findViewById(R.id.userView);
        button = findViewById(R.id.backToPosts);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        });

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}


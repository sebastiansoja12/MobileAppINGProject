package com.example.ingproject.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ingproject.Adapters.UserAdapter;
import com.example.ingproject.API.JsonPlaceholderAPI;
import com.example.ingproject.Models.User;
import com.example.ingproject.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Users extends AppCompatActivity implements View.OnClickListener {

    public Button button;
    public static ListView listView;
    private UserAdapter userAdapter;
    private JsonPlaceholderAPI api;
    private TextView textView;
    private Context context;
    private int position;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent= getIntent();
        Bundle pos = intent.getExtras();
        init();



        assert pos != null;
        final Animation animTranslate = AnimationUtils.loadAnimation(this, R.anim.animation1);
       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animTranslate);
                goToPosts();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });*/
        getUsers(pos.getInt("positionUser"));



    }

    public void getUsers(int position){
        final ProgressDialog nDialog;
        nDialog = new ProgressDialog(this);
        nDialog.setMessage("Loading..");
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(true);
        nDialog.show();
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
                        nDialog.dismiss();
                        listView.setAdapter(userAdapter);
                    }

                    @Override
                    public void onFailure(Call<User[]> call, Throwable t) {

                    }

                });


            }





    public void goToPosts(){
        Intent intent = new Intent(this, PostsView.class);
        startActivity(intent);
    }
    public void init(){
        setContentView(R.layout.activity_users);
        listView = findViewById(R.id.userView);
        textView = findViewById(R.id.usr);
        //button = findViewById(R.id.button);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

  /*@Override
   public void finish() {
       super.finish();
       overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
   }*/

}


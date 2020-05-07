package com.example.ingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.ingproject.Adapters.CommentAdapter;
import com.example.ingproject.Models.Comment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class comments extends AppCompatActivity {
    private JsonPlaceholderAPI api;
    private Button back;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        Intent intent= getIntent();
        Bundle pos = intent.getExtras();
        assert pos != null;


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToPosts();
            }
        });

        getComments(pos.getInt("positionComment"));

    }

   private void getComments(int position){
       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(JsonPlaceholderAPI.URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build();

       api = retrofit.create(JsonPlaceholderAPI.class);
Call<Comment[]> commentcall = api.getComments(position);

        commentcall.enqueue(new Callback<Comment[]>() {
            @Override
            public void onResponse(Call<Comment[]> call, Response<Comment[]> response) {
                Comment[] commentArray = response.body();
                CommentAdapter commentAdapter = new CommentAdapter(comments.this,commentArray);
                listView.setAdapter(commentAdapter);
            }

            @Override
            public void onFailure(Call<Comment[]> call, Throwable t) {

            }


        });
    }
    private void init(){
        setContentView(R.layout.activity_comments);
        back = findViewById(R.id.backButton);
        listView = findViewById(R.id.commentView);
    }
    public void backToPosts(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

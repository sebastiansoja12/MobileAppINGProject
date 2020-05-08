package com.example.ingproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ingproject.Adapters.AlbumAdapter;
import com.example.ingproject.Adapters.PostAdapter;
import com.example.ingproject.Adapters.UserAdapter;
import com.example.ingproject.Models.Comment;
import com.example.ingproject.Models.Post;
import com.example.ingproject.Models.User;
import com.example.ingproject.Models.Album;


import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsView extends AppCompatActivity implements View.OnClickListener {

    public static ListView listView;
    private PostAdapter postAdapter;
    private UserAdapter userAdapter;
    private Context context;
    static JSONObject dataobj2;
    private JsonPlaceholderAPI api;
    public static Comment[] commentArray;
    public static User[] userArray;
    private Button next;
    private Button back;
    private TextView pageView;
    public int page = 1;
    public static Post[] postArray, postPagination;
    LayoutInflater layoutInflater;
    private Button button;
    public int z =10;
    public int x=z-10;



    @SuppressLint({"InflateParams", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_view);

        listView = findViewById(R.id.lv);
        pageView = findViewById(R.id.pageNumber);
        next = findViewById(R.id.nextButton);
        back = findViewById(R.id.backButton);
        next.setOnClickListener(this);
        back.setOnClickListener(this);
        if(page ==1){
            back.setVisibility(View.INVISIBLE);
        }else if(page ==10){
            next.setVisibility(View.INVISIBLE);
        }
        layoutInflater = LayoutInflater.from(getApplicationContext());

        pageView.setText("PAGE " + page);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JsonPlaceholderAPI.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(JsonPlaceholderAPI.class);


        getPosts(z);
        getUsers();
        getAlbums();


    }


    private void getUsers(){
        Call<User[]> usercall = api.getUsers();

        usercall.enqueue(new Callback<User[]>() {
            @Override
            public void onResponse(Call<User[]> call, Response<User[]> response) {
                assert response.body() != null;
                userArray = response.body();

                userAdapter = new UserAdapter(PostsView.this, userArray);
            }

            @Override
            public void onFailure(Call<User[]> call, Throwable t) {

            }


        });

    }
    private void getPosts(final int z){

        Call<Post[]> postcall = api.getPosts();


        postcall.enqueue(new Callback<Post[]>() {
            @Override
            public void onResponse(Call<Post[]> call, Response<Post[]> response) {
                postArray = response.body();
                assert postArray != null;
                postPagination=new Post[z];
                for (int i=0;i<z;i++){
                    postPagination[i]=postArray[i];
                    postAdapter = new PostAdapter(PostsView.this, postPagination);
                    listView.setAdapter(postAdapter);
                }

            }
            @Override
            public void onFailure(Call<Post[]> call, Throwable t) {

            }


        });
    }

    private void getAlbums(){
        Call<Album[]> albumcall = api.getAlbums();

        albumcall.enqueue(new Callback<Album[]>() {
            @Override
            public void onResponse(Call<Album[]> call, Response<Album[]> response) {
                Album[] albumarray = response.body();
                AlbumAdapter albumAdapter = new AlbumAdapter(PostsView.this, albumarray);
            }

            @Override
            public void onFailure(Call<Album[]> call, Throwable t) {

            }
        });
    }



    public void onClick(View v){
        switch (v.getId()){
            case R.id.nextButton:
                page++;
                z+=10;

                getPosts(z);
                pageView.setText("PAGE " + page);

                if(page>1){
                    back.setVisibility(View.VISIBLE);
                }
                if(page == 10){
                    next.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.backButton:
                page--;
                z-=10;

                getPosts(z);
                pageView.setText("PAGE " + page);
                if(page == 10){
                    next.setVisibility(View.INVISIBLE);
                }else if(page==1){
                    back.setVisibility(View.INVISIBLE);

                } else{
                    next.setVisibility(View.VISIBLE);
                }
                break;


        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}

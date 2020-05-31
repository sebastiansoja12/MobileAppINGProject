package com.example.ingproject.Intractors;

import com.example.ingproject.Contracts.PostContract;
import com.example.ingproject.API.JsonPlaceholderAPI;
import com.example.ingproject.Models.Post;
import com.example.ingproject.Models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetPostIntractor implements PostContract.GetPostIntractor {
    JsonPlaceholderAPI api;
    Retrofit retrofit;
    @Override
    public void getNoticeArray(final OnFinishedListener onFinishedListener) {
        retrofit = new Retrofit.Builder()
                .baseUrl(JsonPlaceholderAPI.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(JsonPlaceholderAPI.class);
        Call<User[]> usercall = api.getUsers();

        usercall.enqueue(new Callback<User[]>() {
            @Override
            public void onResponse(Call<User[]> call, Response<User[]> response) {

                onFinishedListener.onFinishedUser(response.body());

            }
            @Override
            public void onFailure(Call<User[]> call, Throwable t) {
                onFinishedListener.onFailure(t);

            }


        });
        Call<Post[]> postcall = api.getPosts();

        postcall.enqueue(new Callback<Post[]>() {
            @Override
            public void onResponse(Call<Post[]> call, Response<Post[]> response) {
                onFinishedListener.onFinished(response.body());

            }
            @Override
            public void onFailure(Call<Post[]> call, Throwable t) {
                onFinishedListener.onFailure(t);

            }


        });




    }




    public void Api(){

    }
}

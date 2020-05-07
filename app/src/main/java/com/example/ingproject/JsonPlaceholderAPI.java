package com.example.ingproject;

import com.example.ingproject.Models.Comment;
import com.example.ingproject.Models.Photo;
import com.example.ingproject.Models.Post;
import com.example.ingproject.Models.User;
import com.example.ingproject.Models.Album;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceholderAPI {

String URL = "https://jsonplaceholder.typicode.com/";
/*
  @GET("posts")
    Call<String> getPosts();
   @GET("users")
    Call<String> getUsers();
    @GET("posts")
    Call<String> getAll();

 */
@GET("posts")
Call<Post[]> getPosts();

@GET("users")
Call<User[]> getUsers();

@GET("users")
Call<User[]> getUser(@Query("id") Integer id);

@GET("comments")
Call<Comment[]> getComments(@Query("postId") Integer postId);

@GET("photos")
Call<Photo[]> getPhotos(@Query("albumId") Integer albumId);

    @GET("albums")
    Call<Album[]> getAlbums();

}




package com.example.ingproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ingproject.Adapters.PhotoAdapter;
import com.example.ingproject.Adapters.UserAdapter;
import com.example.ingproject.Models.Photo;
import com.example.ingproject.Models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



    public class Photos extends AppCompatActivity {

        public Button button;
        public  ListView listView;
        private UserAdapter userAdapter;
        private JsonPlaceholderAPI api;
        private PhotoAdapter photoAdapter;
        private ImageView imageView;
        private String username;
        private TextView textView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_photos);

          init();
            Intent intent= getIntent();
            Bundle pos = intent.getExtras();
            assert pos != null;

             username= pos.getString("userUsername");
           textView.setText(username + "'s Photos");
            getPhotos(pos.getInt("positionPhoto"));

        }


        private void getPhotos(int position){
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


            Call<Photo[]> photocall = api.getPhotos(position);

            photocall.enqueue(new Callback<Photo[]>() {
                @Override
                public void onResponse(Call<Photo[]> call, Response<Photo[]> response) {
                    nDialog.dismiss();
                    Photo[] photoArray= response.body();
                    photoAdapter= new PhotoAdapter(Photos.this, photoArray);
                    listView.setAdapter(photoAdapter);


                }

                @Override
                public void onFailure(Call<Photo[]> call, Throwable t) {

                }
            });

        }




private void init(){
    setContentView(R.layout.activity_photos);
   textView=findViewById(R.id.phot);
    listView = findViewById(R.id.photoView);
    imageView=findViewById(R.id.photo);
    final Animation animTranslate3 = AnimationUtils.loadAnimation(this, R.anim.animation1);
}
        public void goToUsers(){
            Intent intent = new Intent(this, Users.class);
            startActivity(intent);
        }
    }

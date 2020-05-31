package com.example.ingproject.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ingproject.Adapters.CommentAdapter;
import com.example.ingproject.API.JsonPlaceholderAPI;
import com.example.ingproject.Models.Comment;
import com.example.ingproject.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Comments extends AppCompatActivity {
    private JsonPlaceholderAPI api;
    private Button back;
    private ListView listView;
    private TextView textView;
    private Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        Intent intent= getIntent();
        Bundle pos = intent.getExtras();
        assert pos != null;

        final Animation animTranslate1 = AnimationUtils.loadAnimation(this, R.anim.animation1);
       /* back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animTranslate1);
                //backToPosts();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });*/
      id=pos.getInt("positionComment");
      textView.setText("Comments of post with id " + id );
        getComments(pos.getInt("positionComment"));

    }

   private void getComments(int position){
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
        Call<Comment[]> commentcall = api.getComments(position);

        commentcall.enqueue(new Callback<Comment[]>() {
            @Override
            public void onResponse(Call<Comment[]> call, Response<Comment[]> response) {
                nDialog.dismiss();
                Comment[] commentArray = response.body();
                CommentAdapter commentAdapter = new CommentAdapter(Comments.this,commentArray);
                listView.setAdapter(commentAdapter);
            }

            @Override
            public void onFailure(Call<Comment[]> call, Throwable t) {

            }


        });
    }
    private void init(){
        setContentView(R.layout.activity_comments);
        listView = findViewById(R.id.commentView);
        textView=findViewById(R.id.tops);
    }
   /* public void backToPosts(){
        Intent intent = new Intent(this, PostsView.class);
        startActivity(intent);
    }*/
   /*@Override
   public void finish() {
       super.finish();
       overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
   }*/
}

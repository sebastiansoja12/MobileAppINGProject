package com.example.ingproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ingproject.API.JsonPlaceholderAPI;
import com.example.ingproject.Adapters.PostAdapter;
import com.example.ingproject.Adapters.UserAdapter;
import com.example.ingproject.Contracts.PostContract;
import com.example.ingproject.Intractors.GetPostIntractor;
import com.example.ingproject.Models.Post;
import com.example.ingproject.Models.User;
import com.example.ingproject.Presenters.PostPresenter;

import retrofit2.Retrofit;

import static com.example.ingproject.Views.PostsView.postArray;
import static com.example.ingproject.Views.PostsView.postPagination;

public class Activity extends AppCompatActivity implements PostContract.MainView {

    private ListView listView;
    public int page = 1;
    private TextView pageView;
   private Button next,back;
    private PostContract.presenter presenter, presenter2;
 private Post[] p = new Post[]{};
    JsonPlaceholderAPI api;
    Retrofit retrofit;
    private User[] user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page++;
               setDataToListView(p);
                pageView.setText("PAGE " + page);

                if(page>1){
                    back.setVisibility(View.VISIBLE);
                }
                if(page == 10){
                    next.setVisibility(View.INVISIBLE);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page--;
                setDataToListView(p);

                pageView.setText("PAGE " + page);
                if(page == 10){
                    next.setVisibility(View.INVISIBLE);
                }else if(page==1){
                    back.setVisibility(View.INVISIBLE);

                } else{
                    next.setVisibility(View.VISIBLE);
                }
            }
        });
        if(page ==1){
            back.setVisibility(View.INVISIBLE);
        }else if(page ==10){
            next.setVisibility(View.INVISIBLE);
        }
        presenter = new PostPresenter(this, new GetPostIntractor());
        presenter.requestDataFromServer();

    }

    // Initializing layout, listview and buttons
    private void init() {
        setContentView(R.layout.activity_posts_view);
        listView = findViewById(R.id.lv);
        pageView = findViewById(R.id.pageNumber);
        next = findViewById(R.id.nextButton);
        back = findViewById(R.id.backButton);
    }


    @Override
    public void showProgress() {

        setContentView(R.layout.activity_main);


    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setDataToListView(User[] user) {
        UserAdapter userAdapter = new UserAdapter(Activity.this, user);
            // listView.setAdapter(userAdapter);

    }


    @Override
    public void setDataToListView(Post[] post) {

        postPagination=new Post[10];
        for (int i=(page-1)*10;i<(page*10);i++){
            postPagination[i-((page-1)*10)]=post[i];
        }
        PostAdapter  postAdapter = new PostAdapter(Activity.this, postPagination);
       listView.setAdapter(postAdapter);


    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(this,
                "Something went wrong...Error message: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}

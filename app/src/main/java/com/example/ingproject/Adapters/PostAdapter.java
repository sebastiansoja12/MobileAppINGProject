package com.example.ingproject.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ingproject.Models.Post;
import com.example.ingproject.PostsView;
import com.example.ingproject.R;
import com.example.ingproject.Users;
import com.example.ingproject.ViewHolder.ViewHolder;
import com.example.ingproject.comments;

public class PostAdapter extends BaseAdapter implements View.OnClickListener{


    public static Context context;
    public static Post[] post;
    public TextView id;
    @SuppressLint("StaticFieldLeak")
    public static TextView username;
    public TextView comment;
    public TextView title;
    public TextView body;
    PostsView posts = new PostsView();
    @SuppressLint("StaticFieldLeak")
    static ViewHolder holder;

    public PostAdapter(Context context, Post[] post) {
        this.context = context;
        this.post = post;
    }


    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getCount() {
        return post.length;
    }

    @Override
    public Object getItem(int position) {
        return post[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            convertView = inflater.inflate(R.layout.retro_lv, null, true);

            holder.username = (TextView) convertView.findViewById(R.id.username);
            holder.username.setTextColor(Color.BLUE);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.body = (TextView) convertView.findViewById(R.id.body);
            holder.comment = (TextView) convertView.findViewById(R.id.comment);
            holder.comment.setTextColor(Color.RED);

            convertView.setTag(holder);
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder) convertView.getTag();
        }
        UserAdapter userAdapter = new UserAdapter(UserAdapter.context, PostsView.userArray);


            for (int i=0;i<userAdapter.getCount();i++) {
                if (post[position].getUserId()== UserAdapter.user[i].getId()) {
                    holder.username.setText(" " + UserAdapter.user[i].getUsername());
                    holder.title.setText(" " + post[position].getTitle());
                    holder.body.setText(" " + post[position].getBody());
                    holder.comment.setText("Comments: 5" );
                }
           }

        holder.username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   Intent intent = new Intent(context, Users.class);
                   intent.putExtra("positionUser", post[position].getUserId());
                   context.startActivity(intent);


            }
        });
        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, comments.class);
                intent.putExtra("positionComment", post[position].getId());
                context.startActivity(intent);
            }
        });



        return convertView;
    }


   /* private class ViewHolder {

        protected TextView id, username, comment, title, body;

    }*/

   @SuppressLint("SetTextI18n")
   public void onClick(View v){
       Intent intent;
       if (v.getId() == R.id.comment) {
           intent = new Intent(context, comments.class);
           context.startActivity(intent);
       }
   }
}
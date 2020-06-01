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

import com.example.ingproject.Contracts.PostContract;
import com.example.ingproject.Models.User;
import com.example.ingproject.Views.Comments;
import com.example.ingproject.Models.Post;
import com.example.ingproject.Views.PostsView;
import com.example.ingproject.R;
import com.example.ingproject.Views.Users;

public class PostAdapter extends UserAdapter {

    private PostContract.MainView mainView;
    private PostContract.GetPostIntractor getNoticeIntractor;
    public static Context context;
    public static Post[] post;

    static ViewHolder holder;


    public PostAdapter(Context context, Post[] post) {
        super(context, user);
        PostAdapter.context = context;
        PostAdapter.post = post;
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

        for (User value : user) {
            if (post[position].getUserId() == value.getId()) {
                holder.username.setText(" " + value.getUsername() + "\n");
                holder.title.setText(" " + post[position].getTitle() + "\n");
                holder.body.setText(" " + post[position].getBody() + "\n");
                holder.comment.setText("Click here to view comments" + "\n");
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
                Intent intent = new Intent(context, Comments.class);
                intent.putExtra("positionComment", post[position].getId());

                context.startActivity(intent);
            }
        });
        return convertView;
    }



    private class ViewHolder {

        protected TextView id, username, comment, title, body;

    }

}
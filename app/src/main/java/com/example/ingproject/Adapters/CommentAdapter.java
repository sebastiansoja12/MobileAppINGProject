package com.example.ingproject.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ingproject.Models.Comment;
import com.example.ingproject.R;
import com.example.ingproject.ViewHolder.ViewHolder;

public class CommentAdapter extends BaseAdapter {
    public static Context context;
    public static Comment[] comment;
    public CommentAdapter(Context context,  Comment[] comment){
        this.context=context;
        this.comment=comment;
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
        return comment.length;
    }

    @Override
    public Object getItem(int position) {
        return comment[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView( int position, View convertView, ViewGroup parent) {
             ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.retro_comment, null, true);
            holder.id = (TextView) convertView.findViewById(R.id.email);
            holder.title = (TextView) convertView.findViewById(R.id.name);
            holder.body = (TextView) convertView.findViewById(R.id.body);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.id.setText("E-mail: " + comment[position].getEmail());
        holder.title.setText("Name: " + comment[position].getName());
        holder.body.setText("Body: " + comment[position].getBody());



        return convertView;
    }

    /*private class ViewHolder {

        protected TextView username, id, title, body;
    }*/

}

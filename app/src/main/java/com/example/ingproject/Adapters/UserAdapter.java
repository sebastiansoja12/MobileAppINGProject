package com.example.ingproject.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ingproject.Geos;
import com.example.ingproject.Photos;
import com.example.ingproject.R;
import com.example.ingproject.Models.User;
import com.example.ingproject.Users;
import com.example.ingproject.activity_maps;

public class UserAdapter extends BaseAdapter implements View.OnClickListener {

    @SuppressLint("StaticFieldLeak")
    static  Context context;
    public static  User[] user;
    public static int positionUser;
    public TextView textView;
    public static  ViewHolder holder;
    private static View.OnContextClickListener OnContextClickListener;

    public UserAdapter(Context context, User[] user) {

        UserAdapter.context = context;
        UserAdapter.user = user;
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
        return user.length;
    }

    @Override
    public Object getItem(int position) {
        return user[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"SetTextI18n", "CutPasteId", "InflateParams"})
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                   .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            convertView = inflater.inflate(R.layout.retro_user, null, true);

            holder.username = (TextView) convertView.findViewById(R.id.username);
            holder.email = (TextView) convertView.findViewById(R.id.email);
            holder.website = (TextView) convertView.findViewById(R.id.website);
            holder.street = (TextView) convertView.findViewById(R.id.street);
            holder.city = (TextView) convertView.findViewById(R.id.city);
            holder.zipcode = (TextView) convertView.findViewById(R.id.zipcode);

            holder.geo = (TextView) convertView.findViewById(R.id.geo);
            holder.geo.setTextColor(Color.BLUE);
            holder.geo.setOnClickListener( this);


            holder.photos = (TextView) convertView.findViewById(R.id.photos);
            holder.photos.setTextColor(Color.RED);
            holder.photos.setOnClickListener(this);






            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }


        holder.username.setText(user[position].getUsername() + "\n");
        holder.email.setText(user[position].getEmail() + "\n");
        holder.website.setText(user[position].getWebsite() + "\n");
        holder.street.setText(user[position].getAddress().getStreet() + "\n");
        holder.city.setText(user[position].getAddress().getCity()+ "\n");
        holder.zipcode.setText(user[position].getAddress().getZipcode()+ "\n");
        holder.geo.setText(user[position].getAddress().getGeo().getLat() +  "; " +user[position].getAddress().getGeo().getLng()+ "\n");
        holder.photos.setText( user[position].getUsername() + "'s photos");



        holder.photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(context, Photos.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("positionPhoto", user[position].getId());
            intent.putExtra("userUsername", user[position].getUsername());
            context.startActivity(intent);
            }
        });

        holder.geo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, activity_maps.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("lat", user[position].getAddress().getGeo().getLat());
                intent.putExtra("lng", user[position].getAddress().getGeo().getLng());
                intent.putExtra("username", user[position].getUsername());

                context.startActivity(intent);
            }
        });
        return convertView;
    }

    private class ViewHolder {

        protected TextView username,user1, id, title, body, email, phone, website,street, city, zipcode, geo, photos;
    }
    public void onClick(View v){
        Intent intent;
        View convertView = null;
        switch (v.getId()){
            case R.id.photos:
                intent = new Intent(context, Photos.class);
                context.startActivity(intent);

                break;

            case R.id.geo:
                intent = new Intent(context, Geos.class);
                context.startActivity(intent);
                break;
        }
    }

}
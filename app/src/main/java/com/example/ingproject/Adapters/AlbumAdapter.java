package com.example.ingproject.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ingproject.Models.Album;
import com.example.ingproject.Models.Photo;
import com.example.ingproject.R;
import com.squareup.picasso.Picasso;

public class AlbumAdapter extends BaseAdapter {
    @SuppressLint("StaticFieldLeak")
    private Context context;
    public static Album[] album;

    public AlbumAdapter(Context context, Album[] album) {

        this.context = context;
        this.album = album;
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
        return album.length;
    }

    @Override
    public Object getItem(int position) {
        return album[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @SuppressLint({"SetTextI18n", "CutPasteId", "InflateParams", "WrongViewCast"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            convertView = inflater.inflate(R.layout.retro_photo, null, true);


            convertView.setTag(holder);
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }


    private class ViewHolder {

        protected TextView id, photo, title, albumId, url;

    }

}

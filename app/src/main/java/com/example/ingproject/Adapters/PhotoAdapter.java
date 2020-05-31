package com.example.ingproject.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ingproject.Models.Photo;
import com.example.ingproject.Views.PostsView;
import com.example.ingproject.R;
import com.squareup.picasso.Picasso;

public class PhotoAdapter extends AlbumAdapter {
    @SuppressLint("StaticFieldLeak")
    private Context context;
   private Photo[] photo;

    public PhotoAdapter(Context context, Photo[] photo) {
        super(context, album);

        this.context = context;
        this.photo = photo;
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
        return photo.length;
    }

    @Override
    public Object getItem(int position) {
        return photo[position];
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

            holder.iv = (ImageView) convertView.findViewById(R.id.photo);


            convertView.setTag(holder);
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder) convertView.getTag();
        }



        for (int i=0;i<UserAdapter.user.length;i++){
            if(photo[i].getAlbumId() == album[i].getId()){
                Picasso.get().load(photo[position].getUrl()).into(holder.iv);
            }

        }




        return convertView;
    }

    private class ViewHolder {

        protected TextView id, photo, title, albumId, url;
        protected ImageView iv;

    }

}
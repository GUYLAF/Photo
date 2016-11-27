package com.guylaf.photo;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.guylaf.photos.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guyla on 22/11/2016.
 */

public class ImageAdapter extends BaseAdapter {
    private List<Photo> list = new ArrayList<>();
    private Context context;

    public ImageAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<Photo> list) {
        this.list = list;
    }

    public Photo getList(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Photo getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater
                    .from(context)
                    .inflate(R.layout.activity_image, parent, false);
        }
        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText(list.get(position).getTitle());

        TextView url = (TextView) convertView.findViewById(R.id.URL);
        url.setText(list.get(position).getUrl());

        ImageView image = (ImageView) convertView.findViewById(R.id.img);
        Picasso.with(parent.getContext()).load(list.get(position).getUrl()).resize(250,250).into(image);

        FloatingActionButton buttonSupp = (FloatingActionButton) convertView.findViewById(R.id.supp);
        buttonSupp.setFocusable(false);
        buttonSupp.setFocusableInTouchMode(false);

        buttonSupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(list.get(position));
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

}

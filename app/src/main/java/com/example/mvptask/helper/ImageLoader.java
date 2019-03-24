package com.example.mvptask.helper;

import android.widget.ImageView;

import com.example.mvptask.R;
import com.squareup.picasso.Picasso;

public class ImageLoader {
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .into(view);
    }
}

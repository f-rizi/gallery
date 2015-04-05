package com.example.fatemeh.gallery.adapters;

import android.content.Context;

import com.example.fatemeh.gallery.R;
import com.example.fatemeh.gallery.models.Image;

import java.util.List;

/**
 * Created by fatemeh on 05/04/15, 17:51.
 */
public class StaggeredGridListAdapter extends ImagesAdapter {

    public StaggeredGridListAdapter(Context context, List<Image> images) {
        super(context, images);
    }

    protected int getItemLayoutID() {
        return R.layout.item_staggered_list_layout;
    }

}

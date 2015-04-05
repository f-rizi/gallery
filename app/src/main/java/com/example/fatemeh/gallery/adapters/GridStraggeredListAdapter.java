package com.example.fatemeh.gallery.adapters;

import android.content.Context;

import com.example.fatemeh.gallery.R;
import com.example.fatemeh.gallery.models.Image;

import java.util.List;

/**
 * Created by fatemeh on 03/04/15.
 */

public class GridStraggeredListAdapter extends ImagesAdapter {

    public GridStraggeredListAdapter(Context context, List<Image> images) {
        super(context, images);
    }

    protected int getItemLayoutID() {
        return R.layout.item_image_list_layout2;
    }

}

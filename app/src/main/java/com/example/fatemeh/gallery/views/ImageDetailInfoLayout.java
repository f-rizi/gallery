package com.example.fatemeh.gallery.views;

import android.content.Context;
import android.util.AttributeSet;

import com.example.fatemeh.gallery.R;

/**
 * Created by fatemeh on 01/04/15.
 */
public class ImageDetailInfoLayout extends AboutItemLayout{

    public ImageDetailInfoLayout(Context context) {
        super(context);
    }

    public ImageDetailInfoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageDetailInfoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected int getLayoutId() {
        return R.layout.item_image_detail_layout;
    }
}

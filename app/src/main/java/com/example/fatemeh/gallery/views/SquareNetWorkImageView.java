package com.example.fatemeh.gallery.views;

import android.content.Context;
import android.util.AttributeSet;

import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by fatemeh on 05/04/15.
 */
public class SquareNetWorkImageView extends NetworkImageView {
    public SquareNetWorkImageView(Context context) {
        super(context);
    }

    public SquareNetWorkImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareNetWorkImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }
}

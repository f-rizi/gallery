package com.example.fatemeh.gallery.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fatemeh.gallery.R;

/**
 * Created by fatemeh on 31/03/15.
 */

public class AboutItemLayout extends LinearLayout {

    private final TextView titleTextView;
    private final TextView contentTextView;

    public AboutItemLayout(Context context) {
        this(context, null);
    }

    public AboutItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AboutItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(getLayoutId(), this, true);

        titleTextView = (TextView) findViewById(R.id.title);
        contentTextView = (TextView) findViewById(R.id.content);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.AboutItemLayout);

        final String title = array.getString(R.styleable.AboutItemLayout_title);

        if(!TextUtils.isEmpty(title)) {
            titleTextView.setText(title);
        }

        array.recycle();
    }

    protected int getLayoutId() {
        return R.layout.item_about_layout;
    }

    public void setContentText(CharSequence text) {
        contentTextView.setText(text);
    }
}

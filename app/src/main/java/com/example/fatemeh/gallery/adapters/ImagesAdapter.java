package com.example.fatemeh.gallery.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.fatemeh.gallery.R;
import com.example.fatemeh.gallery.helpers.GalleryApplication;
import com.example.fatemeh.gallery.models.Image;

import java.util.List;

/**
 * Created by fatemeh on 01/04/15.
 */

public class ImagesAdapter extends
        RecyclerView.Adapter<ImagesAdapter.ImageViewHolder> {

    private onItemInteractionListener2 listener;

    private Context context;
    private List<Image> images;
    private ImageLoader imageLoader;

    public ImagesAdapter(Context context, List<Image> images) {
        this.context = context;
        this.images = images;

        imageLoader = GalleryApplication.
                getInstance().getImageLoader();
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                getItemLayoutID(), parent, false);

        return new ImageViewHolder(itemView);
    }

    protected int getItemLayoutID() {
        return R.layout.item_list_layout;
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {
        final Image image = images.get(position);

        int linkLength = image.getLink().length();
        String extension = image.getLink().substring(linkLength - 4, linkLength);
        String link = image.getLink().substring(0, linkLength - 4);
        link = link + "m" + extension;

        if (!TextUtils.isEmpty(image.getDescription())) {
            holder.descriptionTextView.setText(image.getDescription());
        } else {
            holder.descriptionTextView.setText(R.string.image_no_Description);
        }

        holder.networkImageView.setDefaultImageResId(R.drawable.placeholder);
        holder.networkImageView.setImageUrl(link, imageLoader);

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = ((BitmapDrawable)
                        holder.networkImageView.getDrawable()).getBitmap();

                listener.onItemClick(image, bitmap);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        protected RelativeLayout itemLayout;
        protected NetworkImageView networkImageView;
        protected TextView descriptionTextView;

        public ImageViewHolder(View itemView) {
            super(itemView);

            itemLayout = (RelativeLayout) itemView.findViewById(R.id.item);
            networkImageView = (NetworkImageView) itemView.findViewById(R.id.image);
            descriptionTextView = (TextView) itemView.findViewById(R.id.description);
        }
    }

    public void setOnListener(onItemInteractionListener2 listener) {
        this.listener = listener;
    }

    public interface onItemInteractionListener2 {
        void onItemClick(Image image, Bitmap bitmap);
    }
}

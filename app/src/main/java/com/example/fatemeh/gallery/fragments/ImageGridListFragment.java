package com.example.fatemeh.gallery.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.example.fatemeh.gallery.R;
import com.example.fatemeh.gallery.activities.MainActivity;
import com.example.fatemeh.gallery.adapters.GridListAdapter;
import com.example.fatemeh.gallery.adapters.ImagesAdapter;
import com.example.fatemeh.gallery.helpers.HidingScrollListener;
import com.example.fatemeh.gallery.models.Image;

import java.util.ArrayList;
import java.util.List;

public class ImageGridListFragment extends BaseListFragment {

    public static ImageGridListFragment newInstance(List<Image> imageList) {
        ImageGridListFragment fragment = new ImageGridListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(IMAGE_LIST_KEY, (ArrayList) imageList);
        fragment.setArguments(args);
        return fragment;
    }

    public ImageGridListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public int getLayoutId() {
        return R.layout.fragment_image_gride_list_fragemnt;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        GridLayoutManager layoutManager =
                new GridLayoutManager(getActivity(), 2);

        return layoutManager;
    }

    public ImagesAdapter getImageAdapter() {
        GridListAdapter imagesAdapter = new GridListAdapter(getActivity(), imageList);

        return imagesAdapter;
    }
}

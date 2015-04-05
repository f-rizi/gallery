package com.example.fatemeh.gallery.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fatemeh.gallery.R;
import com.example.fatemeh.gallery.activities.MainActivity;
import com.example.fatemeh.gallery.adapters.ImagesAdapter;
import com.example.fatemeh.gallery.helpers.HidingScrollListener;
import com.example.fatemeh.gallery.models.Image;

import java.util.ArrayList;
import java.util.List;

public class ImageListFragment extends BaseListFragment {

    public static ImageListFragment newInstance(List<Image> imageList) {
        ImageListFragment fragment = new ImageListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(IMAGE_LIST_KEY, (ArrayList) imageList);
        fragment.setArguments(args);
        return fragment;
    }

    public ImageListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public int getLayoutId() {
        return R.layout.fragment_image_list;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        return layoutManager;
    }

    public ImagesAdapter getImageAdapter() {
        ImagesAdapter imagesAdapter = new ImagesAdapter(getActivity(), imageList);

        return imagesAdapter;
    }
}

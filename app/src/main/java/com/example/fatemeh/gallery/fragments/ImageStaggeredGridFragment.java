package com.example.fatemeh.gallery.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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

public class ImageStaggeredGridFragment extends BaseListFragment {

    public static ImageStaggeredGridFragment newInstance(List<Image> imageList) {
        ImageStaggeredGridFragment fragment = new ImageStaggeredGridFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(IMAGE_LIST_KEY, (ArrayList) imageList);
        fragment.setArguments(args);
        return fragment;
    }

    public ImageStaggeredGridFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public int getLayoutId() {
        return R.layout.fragment_image_staggered_grid;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        return layoutManager;
    }

    public ImagesAdapter getImageAdapter() {
        ImagesAdapter imagesAdapter = new ImagesAdapter(getActivity(), imageList);

        return imagesAdapter;
    }
}

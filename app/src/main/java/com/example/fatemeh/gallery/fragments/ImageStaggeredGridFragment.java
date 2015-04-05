package com.example.fatemeh.gallery.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.fatemeh.gallery.R;
import com.example.fatemeh.gallery.adapters.ImagesAdapter;
import com.example.fatemeh.gallery.adapters.StaggeredGridListAdapter;
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
        StaggeredGridListAdapter imagesAdapter =
                new StaggeredGridListAdapter(getActivity(), imageList);

        return imagesAdapter;
    }
}

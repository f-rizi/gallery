package com.example.fatemeh.gallery.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Parcelable;
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

public abstract class BaseListFragment extends Fragment {

    private OnFragmentInteractionListener listener;

    public static final String IMAGE_LIST_KEY = "imageList";

    List<Image> imageList;

    protected ImagesAdapter imagesAdapter;

    public BaseListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null) {
            imageList = savedInstanceState.getParcelableArrayList(IMAGE_LIST_KEY);

        } else {
            Bundle bundle = getArguments();
            imageList = bundle.getParcelableArrayList(IMAGE_LIST_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);

        RecyclerView imageRecyclerView = (RecyclerView)
                view.findViewById(R.id.images);

        imageRecyclerView.setLayoutManager(getLayoutManager());

        imagesAdapter = getImageAdapter();
        imagesAdapter.setOnListener((ImagesAdapter.onItemInteractionListener)listener);
        imageRecyclerView.setAdapter(imagesAdapter);

        imageRecyclerView.setOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                ((MainActivity) listener).hideToolbar();
            }

            @Override
            public void onShow() {
                ((MainActivity) listener).showToolbar();
            }
        });

        return view;
    }

    abstract int getLayoutId();

    public abstract RecyclerView.LayoutManager getLayoutManager();

    public abstract ImagesAdapter getImageAdapter();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnFragmentInteractionListener {
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        outState.putParcelableArrayList(IMAGE_LIST_KEY, (ArrayList<? extends Parcelable>) imageList);
//    }
}

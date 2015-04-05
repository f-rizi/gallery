package com.example.fatemeh.gallery.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.fatemeh.gallery.R;
import com.example.fatemeh.gallery.adapters.ImagesAdapter;
import com.example.fatemeh.gallery.fragments.AboutFragment;
import com.example.fatemeh.gallery.fragments.BaseListFragment;
import com.example.fatemeh.gallery.fragments.ImageGridListFragment;
import com.example.fatemeh.gallery.fragments.ImageListFragment;
import com.example.fatemeh.gallery.fragments.ImageStaggeredGridFragment;
import com.example.fatemeh.gallery.helpers.GalleryApplication;
import com.example.fatemeh.gallery.models.Image;
import com.example.fatemeh.gallery.network.NetworkConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import de.greenrobot.event.EventBus;


public class MainActivity extends ActionBarActivity implements
        BaseListFragment.OnFragmentInteractionListener,
        ImagesAdapter.onItemInteractionListener {

    public static final String IMAGES_KEY = "images";

    private static final String DEFAULT_SECTION = "hot";
    private static final String DEFAULT_WINDOW = "day";
    private static final String DEFAULT_SORT_BY = "viral";
    private static final String DEFAULT_LIST_TYPE = "list";

    private static final int SET_SETTINGS = 1;

    private Toolbar toolbar;

    private boolean currentViralShown;
    private String currentImagesSection;
    private String currentImagesWindow;
    private String currentSortBy;
    private String currentFragmentType;
    private String oldFragmentType;

    private List<Image> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceManager.setDefaultValues(this, R.xml.settings, false);
        setPreferenceValues();

        setToolbar();

        if (savedInstanceState != null) {
            imageList = savedInstanceState.
                    getParcelableArrayList(IMAGES_KEY);
            showImagesOnUI();

        } else {
            sendRequest();
        }
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    private void setPreferenceValues() {
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(this);

        oldFragmentType = currentFragmentType;

        currentViralShown = preferences.getBoolean(
                getString(R.string.key_show_viral_images_preference), true);

        currentImagesSection = preferences.getString(getString(
                        R.string.key_section_preference), DEFAULT_SECTION);
        currentImagesWindow = preferences.getString(
                getString(R.string.key_window_preference), DEFAULT_WINDOW);

        currentSortBy = preferences.getString(getString(
                        R.string.key_sort_by_preference), DEFAULT_SORT_BY);
        currentFragmentType = preferences.getString(
                getString(R.string.key_list_type_preference), DEFAULT_LIST_TYPE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivityForResult(intent, SET_SETTINGS);
            return true;

        } else if (id == R.id.about) {
            FragmentManager fragmentManager = getFragmentManager();
            AboutFragment aboutFragment = AboutFragment.newInstance();
            aboutFragment.show(fragmentManager, AboutFragment.TAG);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case (SET_SETTINGS): {
                if (resultCode == Activity.RESULT_OK) {
                    handleResult();
                }
                break;
            }
            default:
                throw new NoSuchElementException();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(IMAGES_KEY,
                (ArrayList<? extends Parcelable>) imageList);
    }

    private void handleResult() {
        setPreferenceValues();
        sendRequest();
    }


    private boolean shouldChangeFragment() {
        if (!currentFragmentType.equals(oldFragmentType)) {
            oldFragmentType = currentFragmentType;
            return true;
        }

        return false;
    }

    private void sendRequest() {
        enableSpinner();

        String url = createEndPoint();

        JsonObjectRequest jsonObjectRequest = new
                JsonObjectRequest(Request.Method.GET,
                        url, null, successListener(), errorListener()) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put(NetworkConstants.AUTHORIZATION,
                                NetworkConstants.getAuthorizationHeader());

                        return params;
                    }
                };

        GalleryApplication.getInstance().addToRequestQueue(jsonObjectRequest, "ImagesRequest");
    }

    private String createEndPoint() {
        String url = NetworkConstants.GALLERY_URL;
        url = String.format(url, currentImagesSection,
                currentSortBy, currentImagesWindow, 1, currentViralShown);

        return url;
    }

    private Response.Listener<JSONObject> successListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    disableSpinner();
                    JSONArray imagesJsonArray = response.getJSONArray("data");
                    imageList = Image.getImagesFromJasonArray(imagesJsonArray);
                    showImagesOnUI();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void hideToolbar() {
        toolbar.animate().translationY(
                -toolbar.getHeight()).setInterpolator(
                new AccelerateInterpolator(2));
    }

    public void showToolbar() {
        toolbar.animate().translationY(0).setInterpolator(
                new DecelerateInterpolator(2));
    }

    private Response.ErrorListener errorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                disableSpinner();
                Toast.makeText(getBaseContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void showImagesOnUI() {
        if (shouldChangeFragment()) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment fragment = null;

            if (currentFragmentType.equals("List")) {
                fragment = ImageListFragment.newInstance(imageList);

            } else if (currentFragmentType.equals("Grid")) {
                fragment = ImageGridListFragment.newInstance(imageList);

            } else if (currentFragmentType.equals("Staggered Grid")) {
                fragment = ImageStaggeredGridFragment.newInstance(imageList);
            }

            fragmentTransaction.replace(R.id.fragment_place_holder, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onImageClick(Uri uri) {
    }

    @Override
    public void onItemClick(Image image) {
        Intent intent = new Intent(this, ImageActivity.class);
        intent.putExtra(ImageActivity.IMAGE_LINK_KEY, image);
        startActivity(intent);
    }

    private void enableSpinner() {
        findViewById(R.id.loading).setVisibility(View.VISIBLE);
    }

    private void disableSpinner() {
        findViewById(R.id.loading).setVisibility(View.GONE);
    }
}

package com.example.fatemeh.gallery.fragments;

import android.app.Dialog;
import android.app.DialogFragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import android.os.Bundle;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import android.widget.TextView;

import com.example.fatemeh.gallery.R;
import com.example.fatemeh.gallery.views.AboutItemLayout;

public class AboutFragment extends DialogFragment {

    public static final String TAG = AboutFragment.class.getSimpleName();

    private String authorName;
    private String authorEmail;
    private String versionName;
    private String applicationName;

    private long lastUpdateTime;

    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();
        return fragment;
    }

    public AboutFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            applicationName = getString(R.string.app_name);

            String packageName = getActivity().getPackageName();

            PackageManager packageManager = getActivity().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);

            authorName = getString(R.string.about_author_name);
            authorEmail = getString(R.string.about_author_email);

            versionName = packageInfo.versionName;
            lastUpdateTime = packageInfo.lastUpdateTime;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        TextView packageNameTextView = (TextView) view.findViewById(R.id.application_name);
        packageNameTextView.setText(applicationName);

        TextView authorNameTextView = (TextView) view.findViewById(R.id.author_name);
        authorNameTextView.setText(authorName);

        TextView authorEmailTextView = (TextView) view.findViewById(R.id.author_email);
        authorEmailTextView.setText(authorEmail);

        String lastUpdate = DateFormat.format("dd/MM/yyyy", lastUpdateTime).toString();

        AboutItemLayout versionCodeLayout = (AboutItemLayout) view.findViewById(R.id.version_code1);
        versionCodeLayout.setContentText(versionName);

        AboutItemLayout lastUpdateLayout = (AboutItemLayout) view.findViewById(R.id.last_update1);
        lastUpdateLayout.setContentText(lastUpdate);

        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}

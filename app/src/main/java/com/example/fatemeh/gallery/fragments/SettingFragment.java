package com.example.fatemeh.gallery.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.fatemeh.gallery.R;

public class SettingFragment
        extends PreferenceFragment {

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    public SettingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}

package com.crTech.prakashmarble.ui.Activity.ui.User;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crTech.prakashmarble.R;

public class ChangePassFragment extends Fragment {

    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       v = inflater.inflate(R.layout.fragment_change_pass, container, false);

       return v;
    }
}
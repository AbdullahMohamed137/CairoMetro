package com.example.cairometro.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cairometro.ArImageActivity;
import com.example.cairometro.EngImageActivity;
import com.example.cairometro.R;
import com.otaliastudios.zoom.ZoomLayout;

public class MapFragment extends Fragment {

    ZoomLayout zoomLayout, arZoomLayout;
    ImageView mapMetro, arMapMetro;
    Intent intent;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        zoomLayout = view.findViewById(R.id.zoom_layout);
        mapMetro = view.findViewById(R.id.metro_image);
        arZoomLayout = view.findViewById(R.id.ar_zoom_layout);
        arMapMetro = view.findViewById(R.id.ar_metro_image);


        mapMetro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(requireContext(), EngImageActivity.class);
                startActivity(intent);
            }
        });

        arMapMetro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(requireContext(), ArImageActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
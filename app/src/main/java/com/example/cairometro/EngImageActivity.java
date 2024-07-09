package com.example.cairometro;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.otaliastudios.zoom.ZoomLayout;

public class EngImageActivity extends AppCompatActivity {
    ImageView imageView ;
    ZoomLayout zoomLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_image_eng);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        zoomLayout = findViewById(R.id.zoom_layout);
        imageView = findViewById(R.id.imageView);


        imageView.setImageResource(R.drawable.metro);


        // Example: Zoom in/out programmatically
        zoomLayout.zoomTo(2.0f, true); // Zoom in to 2x
        zoomLayout.zoomTo(1.0f, true); // Zoom back to original size

        // Example: Set double tap to zoom enabled
      //  zoomLayout.setDoubleTapToZoomEnabled(true);

        // Example: Set maximum zoom scale
        zoomLayout.setMaxZoom(4.0f);

        // Example: Set zoom listener
//        zoomLayout.addZoomListener(new ZoomLayout.ZoomListener() {
//            @Override
//            public void onZoom(float scale, float x, float y) {
//                // Handle zoom events
//            }
//        });
    }
}
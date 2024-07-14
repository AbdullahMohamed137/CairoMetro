package com.example.cairometro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ResultActivity extends AppCompatActivity {
    TextView resultTxt, startTxt, endTxt, timeTxt, stationTxt, priceTxt;
    List<List<String>> routs;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

        resultTxt = findViewById(R.id.resultTxt);
        resultTxt.setMovementMethod(new ScrollingMovementMethod());
        startTxt = findViewById(R.id.startTxt);
        endTxt = findViewById(R.id.endTxt);
        timeTxt = findViewById(R.id.timeTxtResult);
        stationTxt = findViewById(R.id.stationsTxtResult);
        priceTxt = findViewById(R.id.priceTxtResult);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        String start = getIntent().getStringExtra("start");
        String end = getIntent().getStringExtra("end");

        if (start.length() >= 17) {
            startTxt.setText(start.substring(0, start.length() - 3) + "...");
        } else {
            startTxt.setText(start);
        }
        if (end.length() >= 17) {
            endTxt.setText(end.substring(0, end.length() - 3) + "...");
        } else {
            endTxt.setText(end);
        }


        Graph<String> cairoMetro = (Graph<String>) getIntent().getSerializableExtra("cairoMetro");
        routs = cairoMetro.findAllPaths(start, end);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Collections.sort(routs, Comparator.comparingInt(List::size));
        }

        byte ticketPrice = 0;
        if (!routs.isEmpty()) {
            ticketPrice = ticketPrice(routs.get(0).size());
        }
        List<String> route = routs.get(0);
        timeTxt.setText(route.size() * 2 + " minutes");
        stationTxt.setText(String.valueOf(route.size()));
        priceTxt.setText(String.valueOf(ticketPrice));
        for (int i = 0; i < routs.size(); i++) {
            route = routs.get(i);
            resultTxt.append("-------------------------\n");
            resultTxt.append("Route option [" + (i + 1) + "]\n");
            resultTxt.append("-------------------------\n");
            resultTxt.append("Count= " + route.size() + "\n");
            resultTxt.append("Time= " + route.size() * 2 + " minutes" + "\n");
            resultTxt.append("Ticket= " + ticketPrice + "\n");
            resultTxt.append("Routs= " + String.join(" -> ", route) + "\n");
        }

    }

    public static byte ticketPrice(int count) {
        byte ticketPrice = 6;

        if (count >= 23)
            ticketPrice = 15;
        else if (count >= 16) {
            ticketPrice = 12;
        } else if (count >= 9) {
            ticketPrice = 8;
        }
        return ticketPrice;
    }

    public void back(View view) {
        //Intent intent = new Intent(this, MainActivity.class);
        finish();
    }
}
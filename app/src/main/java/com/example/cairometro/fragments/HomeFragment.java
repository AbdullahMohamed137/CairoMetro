package com.example.cairometro.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cairometro.Graph;
import com.example.cairometro.R;
import com.example.cairometro.ResultActivity;

import java.util.ArrayList;
import java.util.Arrays;


public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        cairoMetro = new Graph<>();
        cairoMetro.buildGraph();

        startStation = view.findViewById(R.id.start_station);
        arrivalStation = view.findViewById(R.id.arrival_station);
        btn = view.findViewById(R.id.calBtn);


        initSpinners();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String start = startStation.getSelectedItem().toString();
                String end = arrivalStation.getSelectedItem().toString();
                if (start.equalsIgnoreCase("start") || end.equalsIgnoreCase("to") ||
                        start.equalsIgnoreCase("start") && end.equalsIgnoreCase("to")) {
                    Toast.makeText(getContext(), "please enter station", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (start.equalsIgnoreCase(end)) {
                    Toast.makeText(getContext(), "your in the same station", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(requireContext(), ResultActivity.class);
                intent.putExtra("start", start);
                intent.putExtra("end", end);
                intent.putExtra("cairoMetro", cairoMetro);
                startActivity(intent);
                saveData();

            }
        });
        return view;
    }

    public void initSpinners() {
        sharedPreferences = requireContext().getSharedPreferences("stations", MODE_PRIVATE);

        ArrayAdapter<String> adapterStart = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, startStations);
        ArrayAdapter<String> adapterEnd = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, endStations);
        startStation.setAdapter(adapterStart);
        arrivalStation.setAdapter(adapterEnd);

        String start1 = "Start";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            start1 = sharedPreferences.getString("start", "Start");
        }
        String end1 = "To";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            end1 = sharedPreferences.getString("end", "To");
        }
        startStation.setSelection(startStations.indexOf(start1));
        arrivalStation.setSelection(endStations.indexOf(end1));

    }

    public void saveData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String startSelected = startStation.getSelectedItem().toString();
        String endSelected = arrivalStation.getSelectedItem().toString();
        editor.putString("start", startSelected);
        editor.putString("end", endSelected);
        editor.apply();
    }

    private Graph<String> cairoMetro;
    private Spinner startStation, arrivalStation;
    private Button btn;
    private SharedPreferences sharedPreferences;
    private ArrayList<String> startStations = new ArrayList<>(Arrays.asList("Start",
            "helwan", "ain helwan", "helwan university", "wadi hof", "hadayek helwan", "el-maasara",
            "tora el-asmant", "kozzika", "tora el-balad", "sakanat el-maadi", "maadi", "hadayek el-maadi", "dar el-salam", "el-zahraa",
            "mar girgis", "el-malek el-saleh", "al-sayeda zeinab", "saad zaghloul", "sadat", "nasser", "orabi", "al-shohadaa", "ghamra",
            "el-demerdash", "manshiet el-sadr", "kobri el-qobba", "hammamat el-qobba", "saray el-qobba", "hadayeq el-zaitoun",
            "helmeyet el-zaitoun", "el-matareyya", "ain shams", "ezbet el-nakhl", "el-marg", "new el-marg",

            "el-mounib", "sakiat mekky", "omm el-masryeen", "el giza", "faisal", "cairo university",
            "el bohoth", "dokki", "opera", "sadat", "mohamed naguib", "attaba", "al-shohadaa", "masarra", "road el-farag", "st. teresa",
            "khalafawy", "mezallat", "kolleyyet el-zeraa", "shubra el-kheima",

            "adly mansour", "el haykestep", "omar ibn el-khattab", "qobaa",
            "hesham barakat", "el-nozha", "nadi el-shams", "alf maskan", "heliopolis square", "haroun", "al-ahram", "koleyet el-banat", "stadium",
            "fair zone", "abbassia", "abdou pasha", "el geish", "bab el shaaria", "attaba", "nasser", "maspero", "safaa hegazy", "kit kat",
            "sudan street", "imbaba", "el-bohy", "al-qawmeya al-arabiya", "ring road", "rod al-farag axis",
            "el-tawfikeya", "wadi el-nil", "gamaat el dowal al-arabiya", "bulaq el-dakroor", "cairo university"));
    private ArrayList<String> endStations = new ArrayList<>(Arrays.asList("To",
            "helwan", "ain helwan", "helwan university", "wadi hof", "hadayek helwan", "el-maasara",
            "tora el-asmant", "kozzika", "tora el-balad", "sakanat el-maadi", "maadi", "hadayek el-maadi", "dar el-salam", "el-zahraa",
            "mar girgis", "el-malek el-saleh", "al-sayeda zeinab", "saad zaghloul", "sadat", "nasser", "orabi", "al-shohadaa", "ghamra",
            "el-demerdash", "manshiet el-sadr", "kobri el-qobba", "hammamat el-qobba", "saray el-qobba", "hadayeq el-zaitoun",
            "helmeyet el-zaitoun", "el-matareyya", "ain shams", "ezbet el-nakhl", "el-marg", "new el-marg",

            "el-mounib", "sakiat mekky", "omm el-masryeen", "el giza", "faisal", "cairo university",
            "el bohoth", "dokki", "opera", "sadat", "mohamed naguib", "attaba", "al-shohadaa", "masarra", "road el-farag", "st. teresa",
            "khalafawy", "mezallat", "kolleyyet el-zeraa", "shubra el-kheima",

            "adly mansour", "el haykestep", "omar ibn el-khattab", "qobaa",
            "hesham barakat", "el-nozha", "nadi el-shams", "alf maskan", "heliopolis square", "haroun", "al-ahram", "koleyet el-banat", "stadium",
            "fair zone", "abbassia", "abdou pasha", "el geish", "bab el shaaria", "attaba", "nasser", "maspero", "safaa hegazy", "kit kat",
            "sudan street", "imbaba", "el-bohy", "al-qawmeya al-arabiya", "ring road", "rod al-farag axis",
            "el-tawfikeya", "wadi el-nil", "gamaat el dowal al-arabiya", "bulaq el-dakroor", "cairo university"));
}
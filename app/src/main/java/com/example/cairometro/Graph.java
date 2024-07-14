package com.example.cairometro;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

public class Graph<T> implements Serializable {
    private final Map<T, List<T>> adjList;

    public Graph() {
        this.adjList = new HashMap<>();
    }

    public void addNode(T node){
        //adjList.putIfAbsent(node, new ArrayList<>());

        if (!adjList.containsKey(node)) adjList.put(node, new ArrayList<>());
    }

    public void addEdge(T source, T destination, boolean bidirectional) {
//        adjList.putIfAbsent(source, new ArrayList<>());
//        adjList.putIfAbsent(destination, new ArrayList<>());
        if (!adjList.containsKey(source)) adjList.put(source, new ArrayList<>());
        if (!adjList.containsKey(destination)) adjList.put(destination, new ArrayList<>());

        if (!this.hasEdge(source, destination)) Objects.requireNonNull(adjList.get(source)).add(destination);

        if (bidirectional){
            if (!this.hasEdge(destination, source)) Objects.requireNonNull(adjList.get(destination)).add(source);
        }
    }

    public int getNodeCount()
    {
        return adjList.keySet().size();
    }

    public int getEdgesCount(boolean bidirectional)
    {
        int count = 0;
        for (T node : adjList.keySet()) {
            count += adjList.get(node).size();
        }
        if (bidirectional) {
            count = count / 2;
        }
        return count;
    }

    public boolean hasNode(T node) { return adjList.containsKey(node); }
    public boolean hasEdge(T source, T destination)
    {
        return adjList.get(source).contains(destination);
    }

    public List<T> neighbours(T source)
    {
        List<T> neighbours = new ArrayList<>();
        if(!adjList.containsKey(source)) return neighbours;

        neighbours.addAll(adjList.get(source));

        return neighbours;
    }

    @NonNull
    @Override public String toString()
    {
        StringBuilder builder = new StringBuilder();

        for (T node : adjList.keySet()) {
            builder
                    .append("[Node Value: ")
                    .append(node)
                    .append(" with Neighbors");

            for (T neighbor : Objects.requireNonNull(adjList.get(node))) {
                builder.append(" -> ").append(neighbor);
            }
            //adjList.get(node).forEach(neighbor -> builder.append(" -> ").append(neighbor));

            builder.append(" ]\n");
        }

        return builder.toString();
    }

    public List<List<T>> findAllPaths(T source, T destination) {
        // If the start node is the same as the end node, return a path that contains only the start/end node
        if (source.equals(destination)) {
            return Collections.emptyList();
        }

        List<List<T>> allPaths = new ArrayList<>();
        Stack<List<T>> stack = new Stack<>();

        // Push the source node path into the stack
        List<T> path = new ArrayList<>();
        path.add(source);
        stack.push(path);

        while (!stack.isEmpty()) {
            // Pop the top path from the stack
            path = stack.pop();
            T lastNode = path.get(path.size() - 1);

            // If the last node of the path is the destination node, add the path to allPaths
            if (lastNode.equals(destination)) {
                allPaths.add(new ArrayList<>(path));
            } else {
                // For each neighbor of the last node of the path, create a new path and push it into the stack
                for (T neighbor : Objects.requireNonNull(adjList.get(lastNode))) {
                    if (!path.contains(neighbor)) {
                        List<T> newPath = new ArrayList<>(path);
                        newPath.add(neighbor);
                        stack.push(newPath);
                    }
                }
            }
        }

        return allPaths;
    }

    public void buildGraph(){
        List<String> line1 = Arrays.asList("helwan", "ain helwan", "helwan university", "wadi hof","hadayek helwan", "el-maasara",
                "tora el-asmant", "kozzika", "tora el-balad", "sakanat el-maadi", "maadi", "hadayek el-maadi", "dar el-salam", "el-zahraa",
                "mar girgis", "el-malek el-saleh", "al-sayeda zeinab", "saad zaghloul", "sadat", "nasser", "orabi", "al-shohadaa", "ghamra",
                "el-demerdash", "manshiet el-sadr", "kobri el-qobba", "hammamat el-qobba", "saray el-qobba", "hadayeq el-zaitoun",
                "helmeyet el-zaitoun", "el-matareyya", "ain shams", "ezbet el-nakhl", "el-marg", "new el-marg");

        List<String> line2 = Arrays.asList("el-mounib", "sakiat mekky", "omm el-masryeen", "el giza", "faisal", "cairo university",
                "el bohoth", "dokki", "opera", "sadat", "mohamed naguib", "attaba", "al-shohadaa", "masarra", "road el-farag", "st. teresa",
                "khalafawy", "mezallat", "kolleyyet el-zeraa", "shubra el-kheima");

        List<String> line3 = Arrays.asList("adly mansour", "el haykestep", "omar ibn el-khattab", "qobaa",
                "hesham barakat", "el-nozha", "nadi el-shams", "alf maskan", "heliopolis square", "haroun", "al-ahram", "koleyet el-banat", "stadium",
                "fair zone", "abbassia", "abdou pasha", "el geish", "bab el shaaria", "attaba", "nasser", "maspero", "safaa hegazy", "kit kat");
        List<String> line3_Extension1 = Arrays.asList("kit kat","sudan street", "imbaba", "el-bohy", "al-qawmeya al-arabiya", "ring road", "rod al-farag axis");
        List<String> line3_Extension2 = Arrays.asList("kit kat","el-tawfikeya", "wadi el-nil","gamaat el dowal al-arabiya", "bulaq el-dakroor", "cairo university");

        // Connect stations on the same line
        connectStations(line1);
        connectStations(line2);
        connectStations(line3);
        connectStations(line3_Extension1);
        connectStations(line3_Extension2);
    }

    private void connectStations(List<String> line) {
        for (int i = 0; i < line.size() - 1; i++) {
            String from = line.get(i);
            String to = line.get(i + 1);
            this.addEdge((T) from, (T) to, true);
        }
    }
}

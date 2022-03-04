package glos.S4008324;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Airports<T> {
    // Hash Map stores graph edges
    private final Map<T, List<T>> airportMap = new HashMap<>();

    // Add new vertex to graph
    public void addAirport(T s){
        airportMap.put(s, new LinkedList<T>());
    }

    // add edge between source and destination
    public void addEdge(T source,
                        T destination,
                        boolean bidirectional){
        if (!airportMap.containsKey(source))
            addAirport(source);
        if (!airportMap.containsKey(destination))
            addAirport(destination);
        airportMap.get(source).add(destination);

        if (bidirectional) {
            airportMap.get(destination).add(source);
        }
    }
    // method to count vertices
    public void getAirportCount(){
        System.out.println("Graph has " + airportMap.keySet().size() + " airports");
    }

    // method to count edges
    public void getRouteCount(boolean bidirection){
        int count = 0;
        for (T v : airportMap.keySet()){
            count += airportMap.get(v).size();
        }
        if (bidirection) {
            count = count / 2;
        }
        System.out.println("Graph has " + count + " routes");
    }
    // method to confirm is vertex is present
    public boolean hasAirport(T s){
        boolean hasAirport;
        if (airportMap.containsKey(s)){
            System.out.println("This airline does fly from airport: " + s);
            hasAirport = true;
        } else {
            System.out.println("This airline does not fly from airport: " + s);
            hasAirport = false;
        } return hasAirport;
    }
    // method to confirm if edge is present
    public boolean hasRoute(T s, T d){
        boolean hasRoute;
        if (airportMap.get(s).contains(d)){
            // ALSO NEED TO PRINT THE WEIGHT OF EDGE (DISTANCE)
            System.out.println("This airline has a route between " + s + " and " + d);
            hasRoute = true;
        } else {
            System.out.println("This airline does not have a route between " + s + " and " + d);
            hasRoute = false;
        } return hasRoute;
    }
    // print adjacency list of each vertex
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        // iterate over keys
        for (T v : airportMap.keySet()){
            builder.append((v.toString() + ": "));
            // iterate over vertices
            for (T w : airportMap.get(v)) {
                builder.append(w.toString() + " ");
            }
            builder.append("\n");
        }
        return (builder.toString());
    }}


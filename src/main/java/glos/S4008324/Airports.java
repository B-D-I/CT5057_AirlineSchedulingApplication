package glos.S4008324;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/***
 * This class provides the data structures and methods for the creation of a graph, to hold all airports.
 * Each graph vertex will hold an airport, and each edge will be the associated route. This class contains
 * methods for adding departure and destination vertices, along with counting and confirming the vertices and edges
 * @param <T>: The graph contains one generic type.
 */
public class Airports<T> {
    // Hash Map stores graph edges
    private final Map<T, List<T>> airportMap = new HashMap<>();

    // Add new vertex to graph
    public void addAirport(T s){
        airportMap.put(s, new LinkedList<T>());
    }
    /**
     * addEdge method creates an edge between the source & destination
     * @param source: Departure Airport
     * @param destination: Destination Airport
     * @param bidirectional: Is the route bidirectional
     */
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
    /**
     * method to count the edges in the graph
     * @param bidirection: whether to count only bidirectional edges
     */
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
    /**
     * hasRoute method is used to confirm if there is a route between two airports
     * @param s: Departure Airport
     * @param d: Destination Airport
     * @return: boolean value
     */
    public boolean hasRoute(T s, T d){
        boolean hasRoute;
        if (airportMap.get(s).contains(d)){
            System.out.println("This airline has a route between " + s + " and " + d);
            hasRoute = true;
        } else {
            System.out.println("This airline does not have a route between " + s + " and " + d);
            hasRoute = false;
        } return hasRoute;
    }
    // print adjacency list of each vertex in String format
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
    }
}


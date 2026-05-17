package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedGraph<V> {
    private Map<V, Vertex<V>> vertices;

    public WeightedGraph() {
        this.vertices = new HashMap<>();
    }

    // Add a vertex by its data value
    public Vertex<V> addVertex(V data) {
        Vertex<V> vertex = new Vertex<>(data);
        vertices.put(data, vertex);
        return vertex;
    }

    // Add a directed edge from source to dest with given weight
    public void addEdge(V sourceData, V destData, double weight) {
        if (!vertices.containsKey(sourceData)) addVertex(sourceData);
        if (!vertices.containsKey(destData))   addVertex(destData);
        Vertex<V> source = vertices.get(sourceData);
        Vertex<V> dest   = vertices.get(destData);
        source.addAdjacentVertex(dest, weight);
    }

    // Add an undirected edge (both directions)
    public void addUndirectedEdge(V sourceData, V destData, double weight) {
        addEdge(sourceData, destData, weight);
        addEdge(destData, sourceData, weight);
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public List<Vertex<V>> getAllVertices() {
        return new ArrayList<>(vertices.values());
    }
}
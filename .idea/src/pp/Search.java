package pp;

import java.util.List;

public abstract class Search<V> {
    protected WeightedGraph<V> graph;

    public Search(WeightedGraph<V> graph) {
        this.graph = graph;
    }

    // Returns path from start to goal as a list of data values, or empty list if not found
    public abstract List<V> search(V start, V goal);
}
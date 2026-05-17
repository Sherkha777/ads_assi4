package pp;

import java.util.*;

public class BreadthFirstSearch<V> extends Search<V> {

    public BreadthFirstSearch(WeightedGraph<V> graph) {
        super(graph);
    }

    @Override
    public List<V> search(V start, V goal) {
        Vertex<V> startVertex = graph.getVertex(start);
        Vertex<V> goalVertex  = graph.getVertex(goal);

        if (startVertex == null || goalVertex == null) {
            return Collections.emptyList();
        }

        // Map each vertex to the vertex it was discovered from (for path reconstruction)
        Map<Vertex<V>, Vertex<V>> parentMap = new HashMap<>();
        Queue<Vertex<V>> queue = new LinkedList<>();
        Set<Vertex<V>> visited = new HashSet<>();

        queue.add(startVertex);
        visited.add(startVertex);
        parentMap.put(startVertex, null);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();

            if (current == goalVertex) {
                return reconstructPath(parentMap, startVertex, goalVertex);
            }

            for (Vertex<V> neighbor : current.getAdjacentVertices().keySet()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        return Collections.emptyList(); // no path found
    }

    private List<V> reconstructPath(Map<Vertex<V>, Vertex<V>> parentMap,
                                    Vertex<V> start, Vertex<V> goal) {
        LinkedList<V> path = new LinkedList<>();
        Vertex<V> current = goal;
        while (current != null) {
            path.addFirst(current.getData());
            current = parentMap.get(current);
        }
        return path;
    }
}
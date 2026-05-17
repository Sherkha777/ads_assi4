package pp;

import java.util.*;

public class DijkstraSearch<V> extends Search<V> {

    public DijkstraSearch(WeightedGraph<V> graph) {
        super(graph);
    }

    @Override
    public List<V> search(V start, V goal) {
        Vertex<V> startVertex = graph.getVertex(start);
        Vertex<V> goalVertex  = graph.getVertex(goal);

        if (startVertex == null || goalVertex == null) {
            return Collections.emptyList();
        }

        // dist holds the shortest known distance from start to each vertex
        Map<Vertex<V>, Double> dist      = new HashMap<>();
        Map<Vertex<V>, Vertex<V>> parent = new HashMap<>();
        Set<Vertex<V>> settled           = new HashSet<>();

        // Priority queue: [vertex, distanceSoFar] ordered by distance
        PriorityQueue<Vertex<V>> pq = new PriorityQueue<>(
                Comparator.comparingDouble(v -> dist.getOrDefault(v, Double.MAX_VALUE))
        );

        dist.put(startVertex, 0.0);
        parent.put(startVertex, null);
        pq.add(startVertex);

        while (!pq.isEmpty()) {
            Vertex<V> current = pq.poll();

            if (settled.contains(current)) continue;
            settled.add(current);

            if (current == goalVertex) {
                return reconstructPath(parent, startVertex, goalVertex);
            }

            for (Map.Entry<Vertex<V>, Double> entry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = entry.getKey();
                double edgeWeight  = entry.getValue();

                if (settled.contains(neighbor)) continue;

                double newDist = dist.getOrDefault(current, Double.MAX_VALUE) + edgeWeight;
                if (newDist < dist.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    dist.put(neighbor, newDist);
                    parent.put(neighbor, current);
                    pq.add(neighbor); // re-insert with updated priority
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

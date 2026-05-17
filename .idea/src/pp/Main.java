package pp;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        WeightedGraph<String> graph = new WeightedGraph<>();

        graph.addUndirectedEdge("A", "B", 1);
        graph.addUndirectedEdge("A", "D", 6);
        graph.addUndirectedEdge("B", "C", 2);
        graph.addUndirectedEdge("B", "E", 4);
        graph.addUndirectedEdge("C", "D", 3);
        graph.addUndirectedEdge("C", "F", 5);
        graph.addUndirectedEdge("E", "F", 1);

        // ── BFS ──────────────────────────────────────────────
        Search<String> bfs = new BreadthFirstSearch<>(graph);
        List<String> bfsPath = bfs.search("A", "F");
        System.out.println("BFS path A -> F : " + bfsPath);

        // ── Dijkstra ──────────────────────────────────────────
        Search<String> dijkstra = new DijkstraSearch<>(graph);
        List<String> dijkstraPath = dijkstra.search("A", "F");
        System.out.println("Dijkstra path A -> F : " + dijkstraPath);

        // ── Extra: no path scenario ───────────────────────────
        WeightedGraph<String> disconnected = new WeightedGraph<>();
        disconnected.addVertex("X");
        disconnected.addVertex("Y");
        Search<String> bfs2 = new BreadthFirstSearch<>(disconnected);
        System.out.println("BFS X -> Y (disconnected): " + bfs2.search("X", "Y"));
    }
}
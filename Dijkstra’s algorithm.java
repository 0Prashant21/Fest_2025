import java.util.*;

class Edge {
    int to, weight;
    Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

public class Dijkstra {
    private int n;
    private List<List<Edge>> graph;

    public Dijkstra(int n) {
        this.n = n;
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
    }

    public void addEdge(int u, int v, int w) {
        graph.get(u).add(new Edge(v, w));
    }

    public int[] shortestPaths(int src) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0], d = cur[1];

            if (d > dist[u]) continue;

            for (Edge e : graph.get(u)) {
                int v = e.to, w = e.weight;
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        Dijkstra d = new Dijkstra(5);
        d.addEdge(0, 1, 10);
        d.addEdge(0, 4, 5);
        d.addEdge(1, 2, 1);
        d.addEdge(4, 1, 3);
        d.addEdge(4, 2, 9);
        d.addEdge(4, 3, 2);
        d.addEdge(3, 2, 4);
        d.addEdge(3, 0, 7);
        d.addEdge(2, 3, 6);

        int[] dist = d.shortestPaths(0);
        System.out.println(Arrays.toString(dist));
    }
}

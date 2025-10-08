import heapq

def dijkstra(graph, start):
    # Create a priority queue to store (distance, vertex) pairs
    priority_queue = []
    heapq.heappush(priority_queue, (0, start))

    # Create a dictionary to store the shortest path to each vertex
    distances = {vertex: float('infinity') for vertex in graph}
    distances[start] = 0

    while priority_queue:
        current_distance, current_vertex = heapq.heappop(priority_queue)

        # Nodes can only be visited once with the minimum distance
        if current_distance > distances[current_vertex]:
            continue

        # Update the distance for neighboring vertices
        for neighbor, weight in graph[current_vertex].items():
            distance = current_distance + weight

            # Only consider this new path if it's better
            if distance < distances[neighbor]:
                distances[neighbor] = distance
                heapq.heappush(priority_queue, (distance, neighbor))

    return distances

# Driver code
if __name__ == "__main__":
    # Define the graph as an adjacency list
    graph = {
        'A': {'B': 1, 'C': 4},
        'B': {'A': 1, 'C': 2, 'D': 5},
        'C': {'A': 4, 'B': 2, 'D': 1},
        'D': {'B': 5, 'C': 1}
    }

    start_vertex = 'A'
    shortest_paths = dijkstra(graph, start_vertex)

    print(f"Shortest paths from {start_vertex}:")
    for vertex, distance in shortest_paths.items():
        print(f"Distance to {vertex}: {distance}")

#include <bits/stdc++.h>
using namespace std;
#define INF 999999999
#define MAXN 20
int n, adj[MAXN][MAXN], dp[1 << MAXN][MAXN], parent[1 << MAXN][MAXN];
vector<int> bestPath;
int minCost = INF;
struct Point
{
    int x, y, id;
};
vector<Point> cities;
double euclideanDistance(Point a, Point b) { return sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y)); }
void inputCoordinates()
{
    cout << "Enter number of cities: ";
    cin >> n;
    cities.resize(n);
    for (int i = 0; i < n; i++)
    {
        cout << "Enter coordinates for city " << i << ": ";
        cin >> cities[i].x >> cities[i].y;
        cities[i].id = i;
    }
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (i == j)
                adj[i][j] = 0;
            else
                adj[i][j] = (int)euclideanDistance(cities[i], cities[j]);
        }
    }
}
void inputMatrix()
{
    cout << "Enter number of cities: ";
    cin >> n;
    cout << "Enter adjacency matrix:" << endl;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> adj[i][j];
            if (i != j && adj[i][j] == 0)
                adj[i][j] = INF;
        }
    }
}
int tspDP(int mask, int pos)
{
    if (mask == (1 << n) - 1)
    {
        return adj[pos][0];
    }
    if (dp[mask][pos] != -1)
    {
        return dp[mask][pos];
    }
    int result = INF;
    int nextCity = -1;
    for (int city = 0; city < n; city++)
    {
        if ((mask & (1 << city)) == 0)
        {
            int cost = adj[pos][city] + tspDP(mask | (1 << city), city);
            if (cost < result)
            {
                result = cost;
                nextCity = city;
            }
        }
    }
    parent[mask][pos] = nextCity;
    return dp[mask][pos] = result;
}
void reconstructPath(int mask, int pos, vector<int> &path)
{
    path.push_back(pos);
    if (mask == (1 << n) - 1)
    {
        path.push_back(0);
        return;
    }
    int nextCity = parent[mask][pos];
    if (nextCity != -1)
    {
        reconstructPath(mask | (1 << nextCity), nextCity, path);
    }
}
vector<int> nearestNeighbor(int start)
{
    vector<bool> visited(n, false);
    vector<int> path;
    int current = start;
    int totalCost = 0;
    path.push_back(current);
    visited[current] = true;
    for (int i = 0; i < n - 1; i++)
    {
        int nearest = -1;
        int minDist = INF;
        for (int j = 0; j < n; j++)
        {
            if (!visited[j] && adj[current][j] < minDist)
            {
                minDist = adj[current][j];
                nearest = j;
            }
        }
        if (nearest != -1)
        {
            visited[nearest] = true;
            current = nearest;
            path.push_back(current);
            totalCost += minDist;
        }
    }
    totalCost += adj[current][start];
    path.push_back(start);
    return path;
}
int calculatePathCost(const vector<int> &path)
{
    int cost = 0;
    for (int i = 0; i < path.size() - 1; i++)
    {
        cost += adj[path[i]][path[i + 1]];
    }
    return cost;
}
bool twoOptImprove(vector<int> &path)
{
    bool improved = false;
    int pathSize = path.size() - 1;
    for (int i = 1; i < pathSize - 1; i++)
    {
        for (int j = i + 1; j < pathSize; j++)
        {
            int delta = adj[path[i - 1]][path[j]] + adj[path[i]][path[j + 1]] - adj[path[i - 1]][path[i]] - adj[path[j]][path[j + 1]];
            if (delta < 0)
            {
                reverse(path.begin() + i, path.begin() + j + 1);
                improved = true;
            }
        }
    }
    return improved;
}
vector<int> optimizePath(vector<int> initialPath)
{
    vector<int> path = initialPath;
    while (twoOptImprove(path))
    {
    }
    return path;
}
void solveTSPComplete()
{
    vector<int> bestOverall;
    int bestCost = INF;
    for (int start = 0; start < n; start++)
    {
        vector<int> nnPath = nearestNeighbor(start);
        vector<int> optimizedPath = optimizePath(nnPath);
        int cost = calculatePathCost(optimizedPath);
        if (cost < bestCost)
        {
            bestCost = cost;
            bestOverall = optimizedPath;
        }
    }
    cout << "Best path using Nearest Neighbor + 2-opt: ";
    for (int i = 0; i < bestOverall.size(); i++)
    {
        cout << bestOverall[i];
        if (i < bestOverall.size() - 1)
            cout << "->";
    }
    cout << endl
         << "Cost: " << bestCost << endl
         << endl;
    memset(dp, -1, sizeof(dp));
    memset(parent, -1, sizeof(parent));
    int dpCost = tspDP(1, 0);
    vector<int> dpPath;
    reconstructPath(1, 0, dpPath);
    cout << "Optimal path using Dynamic Programming: ";
    for (int i = 0; i < dpPath.size(); i++)
    {
        cout << dpPath[i];
        if (i < dpPath.size() - 1)
            cout << "->";
    }
    cout << endl
         << "Cost: " << dpCost << endl;
}
void bruteForceTSP()
{
    if (n > 10)
    {
        cout << "Brute force not recommended for n > 10" << endl;
        return;
    }
    vector<int> cities;
    for (int i = 1; i < n; i++)
    {
        cities.push_back(i);
    }
    vector<int> currentPath;
    currentPath.push_back(0);
    int minPathCost = INF;
    vector<int> optimalPath;
    do
    {
        currentPath.clear();
        currentPath.push_back(0);
        for (int city : cities)
        {
            currentPath.push_back(city);
        }
        currentPath.push_back(0);
        int currentCost = calculatePathCost(currentPath);
        if (currentCost < minPathCost)
        {
            minPathCost = currentCost;
            optimalPath = currentPath;
        }
    } while (next_permutation(cities.begin(), cities.end()));
    cout << "Brute force optimal path: ";
    for (int i = 0; i < optimalPath.size(); i++)
    {
        cout << optimalPath[i];
        if (i < optimalPath.size() - 1)
            cout << "->";
    }
    cout << endl
         << "Cost: " << minPathCost << endl;
}
void printMatrix()
{
    cout << "Distance matrix:" << endl;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (adj[i][j] == INF)
                cout << "INF ";
            else
                cout << adj[i][j] << " ";
        }
        cout << endl;
    }
    cout << endl;
}
int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout << "TSP Solver" << endl;
    cout << "1. Input coordinates" << endl;
    cout << "2. Input distance matrix" << endl;
    cout << "Choose input method: ";
    int choice;
    cin >> choice;
    if (choice == 1)
    {
        inputCoordinates();
    }
    else
    {
        inputMatrix();
    }
    printMatrix();
    cout << "Available algorithms:" << endl;
    cout << "1. Complete solution (NN + 2-opt + DP)" << endl;
    cout << "2. Dynamic Programming only" << endl;
    cout << "3. Nearest Neighbor only" << endl;
    cout << "4. Brute Force (small instances)" << endl;
    cout << "Choose algorithm: ";
    int algo;
    cin >> algo;
    auto start_time = chrono::high_resolution_clock::now();
    switch (algo)
    {
    case 1:
        solveTSPComplete();
        break;
    case 2:
        memset(dp, -1, sizeof(dp));
        memset(parent, -1, sizeof(parent));
        cout << "Optimal cost: " << tspDP(1, 0) << endl;
        break;
    case 3:
    {
        vector<int> path = nearestNeighbor(0);
        cout << "Nearest neighbor path: ";
        for (int i = 0; i < path.size(); i++)
        {
            cout << path[i];
            if (i < path.size() - 1)
                cout << "->";
        }
        cout << endl
             << "Cost: " << calculatePathCost(path) << endl;
        break;
    }
    case 4:
        bruteForceTSP();
        break;
    default:
        cout << "Invalid choice" << endl;
    }
    auto end_time = chrono::high_resolution_clock::now();
    auto duration = chrono::duration_cast<chrono::milliseconds>(end_time - start_time);
    cout << "Execution time: " << duration.count() << " ms" << endl;
    return 0;
}

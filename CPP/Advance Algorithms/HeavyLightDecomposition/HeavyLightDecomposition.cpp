// HeavyLightDecomposition.cpp
// Heavy Light Decomposition to support path max queries on a tree

#include <bits/stdc++.h>
using namespace std;

const int MAXN = 1e5 + 5;

vector<int> tree[MAXN];
int parent[MAXN], depth[MAXN], heavy[MAXN];
int head[MAXN], pos[MAXN];
int value[MAXN]; // node values
int cur_pos = 0;

int segtree[4 * MAXN];

// Segment Tree for max query
void build(int node, int l, int r, int arr[]) {
    if (l == r) {
        segtree[node] = arr[l];
    } else {
        int mid = (l + r) / 2;
        build(2 * node, l, mid, arr);
        build(2 * node + 1, mid + 1, r, arr);
        segtree[node] = max(segtree[2 * node], segtree[2 * node + 1]);
    }
}

int query(int node, int l, int r, int ql, int qr) {
    if (qr < l || r < ql) return INT_MIN;
    if (ql <= l && r <= qr) return segtree[node];
    int mid = (l + r) / 2;
    return max(query(2 * node, l, mid, ql, qr),
               query(2 * node + 1, mid + 1, r, ql, qr));
}

// DFS to compute size and find heavy child
int dfs(int u, int p) {
    parent[u] = p;
    depth[u] = (p == -1 ? 0 : depth[p] + 1);
    int size = 1, max_subtree = 0;
    for (int v : tree[u]) {
        if (v != p) {
            int subtree = dfs(v, u);
            size += subtree;
            if (subtree > max_subtree) {
                max_subtree = subtree;
                heavy[u] = v;
            }
        }
    }
    return size;
}

// Decompose tree into chains
void decompose(int u, int h, int arr[]) {
    head[u] = h;
    pos[u] = cur_pos;
    arr[cur_pos++] = value[u];

    if (heavy[u] != -1)
        decompose(heavy[u], h, arr);

    for (int v : tree[u]) {
        if (v != parent[u] && v != heavy[u])
            decompose(v, v, arr);
    }
}

// Query path from u to v
int query_path(int u, int v, int n) {
    int res = INT_MIN;
    while (head[u] != head[v]) {
        if (depth[head[u]] < depth[head[v]])
            swap(u, v);
        int start = pos[head[u]];
        int end = pos[u];
        res = max(res, query(1, 0, n - 1, start, end));
        u = parent[head[u]];
    }
    if (depth[u] > depth[v])
        swap(u, v);
    res = max(res, query(1, 0, n - 1, pos[u], pos[v]));
    return res;
}

int main() {
    int n;
    cout << "Enter number of nodes: ";
    cin >> n;

    // Input tree edges
    cout << "Enter " << n - 1 << " edges (u v):\n";
    for (int i = 1; i < n; i++) {
        int u, v;
        cin >> u >> v;
        tree[u].push_back(v);
        tree[v].push_back(u);
    }

    // Input values at each node
    cout << "Enter values for each node:\n";
    for (int i = 0; i < n; i++) {
        cin >> value[i];
        heavy[i] = -1; // Initialize
    }

    dfs(0, -1);

    int arr[MAXN];
    decompose(0, 0, arr);
    build(1, 0, n - 1, arr);

    // Example Query
    int u, v;
    cout << "Enter nodes to query path maximum between: ";
    cin >> u >> v;

    cout << "Maximum value on the path: " << query_path(u, v, n) << "\n";

    return 0;
}

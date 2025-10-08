// MoAlgorithm.cpp
// Problem: Count distinct elements in a range using Mo's Algorithm

#include <bits/stdc++.h>
using namespace std;

const int MAXN = 1e5 + 5;
const int BLOCK_SIZE = 320; // approx sqrt(1e5)
int arr[MAXN];
int freq[MAXN];
int answer[MAXN];
int current_answer = 0;

struct Query {
    int l, r, idx;
    bool operator<(const Query& other) const {
        int block_a = l / BLOCK_SIZE;
        int block_b = other.l / BLOCK_SIZE;
        if (block_a != block_b)
            return block_a < block_b;
        return ((block_a & 1) ? (r < other.r) : (r > other.r)); // Optimize shifting
    }
};

void add(int idx) {
    freq[arr[idx]]++;
    if (freq[arr[idx]] == 1)
        current_answer++;
}

void remove(int idx) {
    freq[arr[idx]]--;
    if (freq[arr[idx]] == 0)
        current_answer--;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, q;
    cout << "Enter array size and number of queries: ";
    cin >> n >> q;

    cout << "Enter array elements:\n";
    for (int i = 0; i < n; i++)
        cin >> arr[i];

    vector<Query> queries(q);
    cout << "Enter queries (0-based index):\n";
    for (int i = 0; i < q; i++) {
        cin >> queries[i].l >> queries[i].r;
        queries[i].idx = i;
    }

    sort(queries.begin(), queries.end());

    int mo_left = 0, mo_right = -1;

    for (const auto& query : queries) {
        while (mo_right < query.r) add(++mo_right);
        while (mo_right > query.r) remove(mo_right--);
        while (mo_left < query.l) remove(mo_left++);
        while (mo_left > query.l) add(--mo_left);

        answer[query.idx] = current_answer;
    }

    cout << "\nDistinct elements in each range:\n";
    for (int i = 0; i < q; i++) {
        cout << "Query " << i + 1 << ": " << answer[i] << " distinct elements\n";
    }

    return 0;
}

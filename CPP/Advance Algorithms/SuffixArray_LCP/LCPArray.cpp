// LCPArray.cpp
// Kasai's Algorithm to build LCP array in O(n)

#include <bits/stdc++.h>
using namespace std;

vector<int> build_lcp_array(const string &s, const vector<int> &suffix_array) {
    int n = s.size();
    vector<int> rank(n, 0);
    for (int i = 0; i < n; i++)
        rank[suffix_array[i]] = i;

    int k = 0;
    vector<int> lcp(n - 1, 0);
    for (int i = 0; i < n; i++) {
        if (rank[i] == n - 1) {
            k = 0;
            continue;
        }
        int j = suffix_array[rank[i] + 1];
        while (i + k < n && j + k < n && s[i + k] == s[j + k])
            k++;
        lcp[rank[i]] = k;
        if (k) k--;
    }
    return lcp;
}

int main() {
    string s;
    cout << "Enter string: ";
    cin >> s;
    s += "$";

    // Build suffix array
    vector<int> suffix_array = build_suffix_array(s);

    // Build LCP array
    vector<int> lcp = build_lcp_array(s, suffix_array);

    cout << "LCP Array:\n";
    for (int x : lcp) {
        cout << x << " ";
    }
    cout << "\n";

    return 0;
}

// Include this function from SuffixArray.cpp or call it directly.
vector<int> build_suffix_array(const string &s); // Prototype only for clarity

// SuffixArray.cpp
// Suffix Array construction using prefix doubling (O(n log²n))

#include <bits/stdc++.h>
using namespace std;

vector<int> build_suffix_array(const string &s) {
    int n = s.size();
    const int alphabet = 256;
    vector<int> p(n), c(n), cnt(max(alphabet, n), 0);

    // Initial sorting by the first character
    for (int i = 0; i < n; i++)
        cnt[s[i]]++;
    for (int i = 1; i < alphabet; i++)
        cnt[i] += cnt[i - 1];
    for (int i = 0; i < n; i++)
        p[--cnt[s[i]]] = i;
    
    c[p[0]] = 0;
    int classes = 1;

    for (int i = 1; i < n; i++) {
        if (s[p[i]] != s[p[i - 1]]) classes++;
        c[p[i]] = classes - 1;
    }

    // k -> 2^k length prefixes
    vector<int> pn(n), cn(n);
    for (int h = 0; (1 << h) < n; ++h) {
        for (int i = 0; i < n; i++) {
            pn[i] = (p[i] - (1 << h) + n) % n;
        }

        fill(cnt.begin(), cnt.begin() + classes, 0);
        for (int i = 0; i < n; i++)
            cnt[c[pn[i]]]++;
        for (int i = 1; i < classes; i++)
            cnt[i] += cnt[i - 1];
        for (int i = n - 1; i >= 0; i--)
            p[--cnt[c[pn[i]]]] = pn[i];

        cn[p[0]] = 0;
        classes = 1;
        for (int i = 1; i < n; i++) {
            pair<int, int> curr = {c[p[i]], c[(p[i] + (1 << h)) % n]};
            pair<int, int> prev = {c[p[i - 1]], c[(p[i - 1] + (1 << h)) % n]};
            if (curr != prev) ++classes;
            cn[p[i]] = classes - 1;
        }
        c.swap(cn);
    }

    return p;
}

int main() {
    string s;
    cout << "Enter string: ";
    cin >> s;
    s += "$"; // Sentinel character

    vector<int> suffix_array = build_suffix_array(s);

    cout << "Suffix Array:\n";
    for (int i = 0; i < suffix_array.size(); i++) {
        cout << suffix_array[i] << " ";
    }
    cout << "\n";

    return 0;
}

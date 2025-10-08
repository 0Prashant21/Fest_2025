#include <iostream>
#include <vector>
using namespace std;

int maxLoot(vector<int> &hval) {
    int n = hval.size();

    if (n == 0)
        return 0;
    if (n == 1)
        return hval[0];

    int secondLast = 0, last = hval[0];

    int res;
    for (int i = 1; i < n; i++) {
        res = max(hval[i] + secondLast, last);
        secondLast = last;
        last = res;
    }
    return res;
}

int main() {
    int n;
    cin>>n;

    vector<int> hval(n);
    for(int i=0;i<n;i++){
        cin>>hval[i];
    }
    cout << maxLoot(hval)<<endl;
    return 0;
}
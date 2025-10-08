# Mo's Algorithm - Efficient Offline Range Query Solver

Mo’s Algorithm is an optimization technique for solving range queries in **O((N + Q) × √N)** time, where:
- N = array size
- Q = number of queries

This algorithm is highly effective for static arrays and **offline queries**, where all queries are known in advance.

---

## ✅ Problem Solved Here

**Count of Distinct Elements in a Given Range [L, R]**

You are given:
- An array of N elements
- Q queries where each query asks how many **unique elements** are in the subarray from index L to R

---

## 📥 Input Format

Enter array size and number of queries:  
6 3  
Enter array elements:  
1 2 1 3 4 2  
Enter queries (0-based index):  
0 4
1 3
2 5


---

## 📤 Output Format

Distinct elements in each range:  
Query 1: 4 distinct elements  
Query 2: 3 distinct elements  
Query 3: 4 distinct elements  


---

## ⏱️ Time Complexity

- Per Query: **O(√N)**
- Total: **O((N + Q) × √N)**

---

## 🛠️ Techniques Used

- **Square root decomposition**
- Sorting queries by blocks
- Optimizing add/remove operations

---

## 📁 Files

- `MoAlgorithm.cpp` – Main logic for solving the range query using Mo’s algorithm
- `README.md` – This file with explanation, sample I/O, and complexity

---

## 📚 References

- [CP-Algorithms: Mo’s Algorithm](https://cp-algorithms.com/data_structures/sqrt_decomposition.html)
- [GeeksforGeeks Tutorial](https://www.geeksforgeeks.org/mos-algorithm-query-square-root-decomposition-set-1-introduction/)

---
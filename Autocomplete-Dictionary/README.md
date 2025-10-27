# Java Autocomplete Dictionary (Trie)

This is a high-performance, command-line Autocomplete Dictionary system implemented in Java. It uses a **Trie (Prefix Tree)** data structure to store a massive dictionary of words and provide instant autocomplete suggestions.

This project is powered by a large dictionary file (`words.txt`) and demonstrates a practical, real-world application of the Trie data structure.

## Features

* **Trie-Based:** Uses a `TrieNode` class with a `HashMap` for fast, efficient, memory-friendly prefix lookups.
* **Large Scale:** Loads a full-size dictionary (over 370,000+ words from `words.txt`) on startup.
* **Case-Insensitive:** All inserted words and prefixes are handled in lowercase.
* **Interactive:** Includes a `main` method to load the dictionary and test prefixes in real-time.
* **Robust:** Includes input validation and modern Java practices (try-with-resources).

## How to Compile and Run

You must be inside this folder in your terminal to compile and run the program.

**1. Navigate into the project folder:**

```bash
cd "Autocomplete-Dictionary-Java"
```

**2. Compile the Java code:**

```bash
javac AutocompleteDictionary.java
```

**3.Run the Program:**

```bash
java AutocompleteDictionary
```

**4. Example Session:**
The program will first load the words from `words.txt`. This may take a few seconds.

```text
Loading dictionary from 'words.txt'...
Successfully loaded 370105 words in ...ms.

Enter prefix to search (or 'q' to quit): aah
Suggestions for 'aah':
 -> aah
 -> aahed
 -> aahing
 -> aahs

Enter prefix to search (or 'q' to quit): zoo
Suggestions for 'zoo':
 -> zoo
 -> zoozoo
 -> zooblast
 -> zoochemistry
 -> zoodynamics
(and many more...)

Enter prefix to search (or 'q' to quit): q
Goodbye!
```

## How It Works

* `TrieNode`: A private inner class holds a `Map<Character, TrieNode>` for its children and a `boolean` to mark the end of a word.
* `insert(String word)`: Iterates through each character of the word, creating new nodes in the Trie as needed.
* `getSuggestions(String prefix)`:
  1.  Traverses the Trie to the node corresponding to the last character of the prefix.
  2.  If the prefix path doesn't exist, it returns an empty list.
  3.  If it does, it calls a recursive helper `findAllWords()` to explore all child paths from that node and collect all complete words.
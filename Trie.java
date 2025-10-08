class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEndOfWord = false;
}

public class Trie {
    private TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) node.children[index] = new TrieNode();
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) return false;
            node = node.children[index];
        }
        return node.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) return false;
            node = node.children[index];
        }
        return true;
    }

    private boolean delete(TrieNode node, String word, int depth) {
        if (node == null) return false;
        if (depth == word.length()) {
            if (!node.isEndOfWord) return false;
            node.isEndOfWord = false;
            for (TrieNode child : node.children) if (child != null) return false;
            return true;
        }
        int index = word.charAt(depth) - 'a';
        if (delete(node.children[index], word, depth + 1)) {
            node.children[index] = null;
            if (!node.isEndOfWord) {
                for (TrieNode child : node.children) if (child != null) return false;
                return true;
            }
        }
        return false;
    }

    public void remove(String word) {
        delete(root, word, 0);
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("bat");

        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.search("appl"));
        System.out.println(trie.startsWith("ap"));
        trie.remove("app");
        System.out.println(trie.search("app"));
        System.out.println(trie.search("apple"));
    }
}

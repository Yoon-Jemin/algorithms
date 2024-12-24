package leetcode.dfs;

public class WordDictionary {

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");

        System.out.println(wordDictionary.search("pad")); // false
        System.out.println(wordDictionary.search("bad")); // true
        System.out.println(wordDictionary.search(".ad")); // true
        System.out.println(wordDictionary.search("b..")); // true
    }

    private static class TreeNode {
        TreeNode[] children;
        boolean isEndOfWord;

        public TreeNode() {
            children = new TreeNode[26];
            isEndOfWord = false;
        }
    }

    private TreeNode root;
    public WordDictionary() {
        root = new TreeNode();
    }

    public void addWord(String word) {
        TreeNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TreeNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    public boolean search(String word) {
        return searchHelper(word, 0, root);
    }

    private boolean searchHelper(String word, int index, TreeNode node) {
        if (node == null) return false;
        if (index == word.length()) return node.isEndOfWord;

        char c = word.charAt(index);
        if (c == '.') {
            for (TreeNode child : node.children) {
                if (searchHelper(word, index + 1, child)) return true;
            }
            return false;
        } else {
            int childIndex = c - 'a';
            return searchHelper(word, index + 1, node.children[childIndex]);
        }
    }
}

package com.plf.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author panlf
 * @date 2020/09/18
 */
public class MyTrie {

    /**
     * 单词数
     */
    private int size;

    /**
     * 根节点
     */
    private TreeNode root = new TreeNode();
    ;

    private class TreeNode {
        /**
         * 是否是词尾
         */
        private boolean endWord;
        /**
         * 当前的单词
         */
        private String word;
        /**
         * 如果是词尾那当前的单词个数
         */
        private int wordNum;
        /**
         * 下一个节点列表
         */
        private TreeMap<String, TreeNode> nextNode;

        public TreeNode(boolean endWord, String word, int wordNum) {
            this.endWord = endWord;
            this.word = word;
            this.wordNum = wordNum;
            this.nextNode = new TreeMap<>();
        }

        public TreeNode() {
            this(false, null, 0);
        }
    }

    public int size() {
        return size;
    }

    /**
     * 添加单词
     *
     * @param word
     * @return
     */
    public TreeNode add(String word) {
        TreeNode current = root;
        if (isNotEmpty(word)) {
            String[] arr = word.split("");
            for (String str : arr) {
                if (current.nextNode.get(str) == null) {
                    current.nextNode.put(str, new TreeNode(false, str, 0));
                }
                current = current.nextNode.get(str);
            }
            if (!current.endWord) {
                current.endWord = true;
                size++;
            }
            current.wordNum++;
        }
        return root;
    }

    /**
     * 查询当前单词个数
     *
     * @param word
     * @return
     */
    public int getWordNum(String word) {
        int result = 0;

        TreeNode current = search(word);

        result = current.wordNum;

        return result;
    }

    /**
     * 是否存在该单词前缀
     *
     * @param prefix
     * @return
     */
    public boolean isPrefix(String prefix) {
        TreeNode current = search(prefix);
        if (current == null) {
            return false;
        }
        return true;
    }

    /**
     * 是否存在该单词
     *
     * @param word
     * @return
     */
    public boolean isWord(String word) {
        TreeNode current = search(word);
        if (current == null) {
            return false;
        }
        return current.endWord;
    }

    public List<String> getWords(TreeNode treeNode, String word) {
        List<String> list = new ArrayList<>();
        treeNode = search(word);
        if (treeNode != null) {
            if (treeNode.endWord) {
                list.add(word);
            }
            TreeMap<String, TreeNode> startNode = treeNode.nextNode;
            String s = "";
            for (Map.Entry<String, TreeNode> entry : startNode.entrySet()) {
                StringBuilder stringBuilder = new StringBuilder(word);
                TreeNode nextNode = entry.getValue();
                s = entry.getKey();
                stringBuilder.append(s);
                list.addAll(getWords(nextNode, stringBuilder.toString()));

            }
        }
        return list;
    }

    public TreeNode search(String word) {
        TreeNode current = root;
        if (isNotEmpty(word)) {
            String[] arr = word.split("");
            for (String str : arr) {
                if (current.nextNode.get(str) == null) {
                    return null;
                }
                current = current.nextNode.get(str);
            }
            return current;
        }
        return current;
    }

    public boolean isNotEmpty(String word) {
        if (word == null || word.trim().length() <= 0) {
            return false;
        }
        return true;
    }

    public boolean deleteWord(String word) {
        TreeNode multiChildNode = null;
        int multiChildNodeIndex = -1;
        TreeNode current = root;
        for (int i = 0; i < word.length(); i++) {
            TreeNode child = current.nextNode.get(String.valueOf(word.charAt(i)));
            //如果Trie中没有这个单词
            if (child == null) {
                return false;
            }
            //当前节点的子节点大于1个
            if (child.nextNode.size() > 1) {
                multiChildNodeIndex = i;
                multiChildNode = child;
            }
            current = child;
        }
        //如果单词后面还有子节点
        if (current.nextNode.size() > 0) {
            if (current.endWord) {
                current.endWord = false;
                size--;
                return true;
            }
            //不存在该单词，该单词只是前缀
            return false;
        }
        //如果单词的所有字母的都没有多个分支，删除整个单词
        if (multiChildNodeIndex == -1) {
            root.nextNode.remove(String.valueOf(word.charAt(0)));
            size--;
            return true;
        }
        //如果单词的除了最后一个字母，其他的字母有分支
        if (multiChildNodeIndex != word.length() - 1) {
            multiChildNode.nextNode.remove(String.valueOf(word.charAt(multiChildNodeIndex + 1)));
            size--;
            return true;
        }
        return false;
    }

    public void printTrieRoot(){
        printTrieRoot(root,0);
    }

    public void printTrieRoot(TreeNode node, int i) {
        if(node.nextNode==null) {
            return;
        }else {
            System.out.println();
            System.out.print("第"+(i++)+"层"+"  ");
            System.out.print(node.word+"  ");
            System.out.print(node.endWord+"  ");
            System.out.print(node.wordNum+"  ");
            TreeMap<String,TreeNode> startNode= node.nextNode;
            for (Map.Entry<String, TreeNode> entry : startNode.entrySet()) {
                printTrieRoot(entry.getValue(),i);
            }
        }
    }

    public static void main(String[] args) {
        MyTrie trie = new MyTrie();
        trie.add("apply");
        trie.add("apple");
        trie.add("app");
        trie.add("abound");
        trie.add("cult");
        trie.printTrieRoot();
        trie.deleteWord("apple");
        System.out.println("\n---------\n");
        trie.printTrieRoot();
        //System.out.println(trie.size);
        //System.out.println(trie.getWordNum("我爱你"));
        //System.out.println(trie.isPrefix("apple"));
        //System.out.println(trie.isWord("apple"));
        //System.out.println(trie.getWords(trie.root, "我傻"));
    }
}

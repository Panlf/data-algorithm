package com.plf.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 字典树又称单词查找树，Trie树，是一种树形结构，是一种哈希树的变种。
 * 典型应用是用于统计，排序和保存大量的字符串（但不仅限于字符串），所以经常被搜索引擎系统用于文本词频统计。
 * 它的优点是：利用字符串的公共前缀来节约存储空间，最大限度地减少无谓的字符串比较，查询效率比哈希表高。
 *
 * 用于统计和查询
 *
 * @author panlf
 * @date 2020-09-18
 */
public class Trie {

    private class Node {
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root = new Node();
    private int size;


    //获得Trie中存储的单词数量
    public int getSize() {
        return size;
    }

    //从Trie中添加一个新的单词word
    public void add(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (current.next.get(c) == null) {
                current.next.put(c, new Node());
            }
            current = current.next.get(c);
        }
        if (!current.isWord) {
            size++;
            current.isWord = true;
        }
    }

    //查询单词word是否在Trie中
    public boolean contains(String word) {
        Node current = search(word);
        if(current == null){
            return false;
        }
        return current.isWord;
    }

    //前缀搜索
    public boolean isPrefix(String prefix) {
        Node current = search(prefix);
        if(current == null){
            return false;
        }
        return true;
    }

    public Node search(String word){
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (current.next.get(c) == null) {
                return null;
            }
            current = current.next.get(c);
        }
        return current;
    }

    /*
     * 1，如果单词是另一个单词的前缀，只需要把该word的最后一个节点的isWord的改成false
     * 2，如果单词的所有字母的都没有多个分支，删除整个单词
     * 3，如果单词的除了最后一个字母，其他的字母有多个分支，
     */

    /**
     * 删除操作
     *
     * @param word
     * @return
     */
    public boolean remove(String word) {
        Node multiChildNode = null;
        int multiChildNodeIndex = -1;
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            Node child = current.next.get(word.charAt(i));
            //如果Trie中没有这个单词
            if (child == null) {
                return false;
            }
            //当前节点的子节点大于1个
            if (child.next.size() > 1) {
                multiChildNodeIndex = i;
                multiChildNode = child;
            }
            current = child;
        }
        //如果单词后面还有子节点
        if (current.next.size() > 0) {
            if (current.isWord) {
                current.isWord = false;
                size--;
                return true;
            }
            //不存在该单词，该单词只是前缀
            return false;
        }
        //如果单词的所有字母的都没有多个分支，删除整个单词
        if (multiChildNodeIndex == -1) {
            root.next.remove(word.charAt(0));
            size--;
            return true;
        }
        //如果单词的除了最后一个字母，其他的字母有分支
        if (multiChildNodeIndex != word.length() - 1) {
            multiChildNode.next.remove(word.charAt(multiChildNodeIndex + 1));
            size--;
            return true;
        }
        return false;
    }

    public List<String> getWords(
            Node node, String prefix) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);

            if (node.next.get(c) != null) {
                node = node.next.get(c);
            }
        }

        if (node != null) {
            if (node.isWord) {
                list.add(prefix);
            }
            TreeMap<Character, Node> startNode = node.next;
            Character c = null;
            for (Map.Entry<Character, Node> entry : startNode.entrySet()) {
                StringBuilder stringBuilder = new StringBuilder(prefix);
                Node nextNode = entry.getValue();
                c = entry.getKey();
                stringBuilder.append(c);
                list.addAll(getWords(nextNode, stringBuilder.toString()));
            }
        }
        return list;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("apache");
        trie.add("abound");
        trie.add("app");
        trie.add("apple");
        trie.add("apply");
        //trie.add("appearance")
        //System.out.println(trie.root);
        //System.out.println(trie.getWords(trie.root, "ab"));
        //System.out.println(trie);

        //System.out.println(trie.contains("app"));
        //System.out.println(trie.contains("abound"));
        //System.out.println(trie.remove("app"));
        System.out.println(trie.contains("please"));
    }
}

package com.plf.lru;

/**
 *
 * @author  panlf
 * @date  2020/12/08
 */
public class Node<K,V> {
    public K key;

    public V value;

    public Node preNode;

    public Node nextNode;

    public Node(K key,V value){
        this.key = key;
        this.value = value;
    }

}

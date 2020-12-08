package com.plf.lru;

import java.util.Hashtable;

/**
 * LRU-1算法
 * @author panlf
 * @date 2020/12/8
 */
public class LRUCache<K,V> {

    /**
     * 当前的大小
     */
    private int currentSize;

    /**
     * 限制大小
     */
    private int capacity;

    /**
     * 所有的Node节点
     */
    private Hashtable<K, Node> nodeCaches;

    /**
     * 头节点
     */
    private Node head;

    /**
     * 尾节点
     */
    private Node tail;

    public LRUCache(int capacity){
        this.currentSize = 0;
        this.capacity = capacity;
        // 缓存容器
        nodeCaches = new Hashtable<>(capacity);
    }

    public void put(K key,V value){
        Node node = nodeCaches.get(key);

        //如果是新元素
        if(node == null){
            //如果超过了容器的最大限制
            if(nodeCaches.size()>=capacity){
                //移除最后一个节点
                nodeCaches.remove(tail.key);
                removeTail();
            }
            node = new Node(key,value);
        }else{
            // 如果不是新值，就覆盖旧值
            node.value = value;
        }

        //把元素移动到首部
        moveToHead(node);
        nodeCaches.put(key, node);
    }

    /**
     * 通过key获取元素
     * @param key
     * @return
     */
    public Object get(K key) {
        Node node = nodeCaches.get(key);
        if (node == null) {
            return null;
        }
        //把访问的节点移动到首部
        moveToHead(node);
        return node.value;
    }

    /**
     * 根据key移除节点
     * @param key
     * @return
     */
    public Object remove(K key) {
        Node node = nodeCaches.get(key);
        if (node != null) {
            if (node.preNode != null) {
                node.preNode.nextNode = node.nextNode;
            }
            if (node.nextNode != null) {
                node.nextNode.preNode = node.preNode;
            }
            if (node == head) {
                head = node.nextNode;
            }
            if (node == tail) {
                tail = node.preNode;
            }
        }
        return nodeCaches.remove(key);
    }


    /**
     * 将值移到头部
     * @param node
     */
    private void moveToHead(Node node) {
        if (head == node) {
            return;
        }
        if (node.nextNode != null) {
            node.nextNode.preNode = node.preNode;
        }
        if (node.preNode != null) {
            node.preNode.nextNode = node.nextNode;
        }
        if (node == tail) {
            tail = tail.preNode;
        }
        if (head == null || tail == null) {
            head = tail = node;
            return;
        }
        node.nextNode = head;
        head.preNode = node;
        head = node;
        head.preNode = null;
    }

    /**
     * 移除最后一个节点
     */
    private void removeTail() {
        if (tail != null) {
            tail = tail.preNode;
            if (tail == null) {
                head = null;
            } else {
                tail.nextNode = null;
            }
        }
    }

    /**
     * 清除所有节点
     */
    public void clear() {
        head = null;
        tail = null;
        nodeCaches.clear();
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        while(head!=null){
            stringBuilder.append(head.key+":"+head.value+"\n");
            head = head.nextNode;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put("j","jin");
        lruCache.put("y","yin");
        lruCache.put("b","best");
        lruCache.put("w","warm");
        lruCache.put("b","best");

        lruCache.remove("w");
        lruCache.get("y");
        lruCache.put("j","jin");
        System.out.println(lruCache.toString());
    }
}

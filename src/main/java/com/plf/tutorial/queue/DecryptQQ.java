package com.plf.tutorial.queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author panlf
 * @date 2022/11/21
 */
public class DecryptQQ {
    public static void main(String[] args) {
        Deque<Integer> deque = new LinkedList<Integer>();
        deque.add(6);
        deque.add(3);
        deque.add(1);
        deque.add(7);
        deque.add(5);
        deque.add(8);
        deque.add(9);
        deque.add(2);
        deque.add(4);
        StringBuilder stringBuilder = new StringBuilder();
        while(deque.size() > 0){
            stringBuilder.append(deque.pop());
            if(deque.size() > 0) {
                deque.addLast(deque.pop());
            }
        }
        System.out.println(stringBuilder.toString());
    }
}

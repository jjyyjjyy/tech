package me.jy.list;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author jy
 * @date 2018/02/23
 */
public class Median<E extends Comparable<E>> {

    private E result;

    private Queue<E> minQueue = new PriorityQueue<>();

    private Queue<E> maxQueue = new PriorityQueue<>(Collections.reverseOrder());

    public void add(E e) {
        if (e == null) {
            return;
        }
        if (result == null) {
            result = e;
            return;
        }

        if (e.compareTo(result) < 0) {
            maxQueue.add(e);
        } else minQueue.add(e);


        int diff = minQueue.size() - maxQueue.size();
        boolean needChange = Math.abs(diff) > 1;

        if (needChange) {
            if (diff > 0) {
                E newResult = minQueue.poll();
                maxQueue.offer(result);
                result = newResult;
            } else {
                E newResult = maxQueue.poll();
                minQueue.offer(result);
                result = newResult;
            }
        }

    }

    public E getMedianValue() {
        return this.result;
    }

    public static void main(String[] args) {
        Median<Integer> median = new Median<>();
        Arrays.asList(34, 90, 67, 45, 1, 99, 2).forEach(median::add);
        System.out.println(median.getMedianValue());
    }
}

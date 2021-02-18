package com.donaldy.gateway;


import java.util.concurrent.TimeUnit;

/**
 * @author donald
 * @date 2021/02/18
 */
public class SlidingWindowTest {
}

class SlidingWindow {
    // 单位时间分割多少块
    private int slot;
    // 单位时间的次数
    private long limit;
    // 单位时间
    private TimeUnit timeUnit;
    // 记录窗口滑动到 Node
    private Node lastNode;
    // 每个 slot 时间段
    private long slotTime;

    public SlidingWindow(int slot, long limit) {
        this.slot = slot;
        this.limit = limit;
        init();
    }

    private void init() {
        Node currentNode = null;
        long current = System.currentTimeMillis();
        for (int i = 0; i < slot; ++i) {
            if (lastNode == null) {
                lastNode = new Node(current, 0, i + 1);
                currentNode = lastNode;
            } else {
                lastNode.next = new Node(current, 0 , i + 1);
                lastNode = lastNode.next;
            }
        }
        lastNode.next = currentNode;
        slotTime = 60 / slot;
    }

    /**
     * 这个方法检查是否超限，如果未超限，次数加一
     */
    public synchronized boolean checkAnddAdd() {
        reset();
        long sum = getSum();
        System.out.println(sum);
        if (sum >= limit) {
            return false;
        }
        lastNode.addCounter();
        return true;
    }

    private long getSum() {

        long sum = 0L;
        Node currentNode = lastNode;
        for (int i = 0; i < slot; ++i) {
            sum += currentNode.counter;
            currentNode = currentNode.next;
        }
        return sum;
    }

    private void reset() {
        long currentTimeMills = System.currentTimeMillis();
        long time = lastNode.getTime();
        int count = (int) ((currentTimeMills - time) / slotTime);
        if (count > slot) {
            count = slot;
        }
        reset(count, currentTimeMills);
    }

    private void reset(int num, long currentTimeMills) {

        if (num <= 0) {
            return;
        }
        Node currentNode = lastNode;
        for (int i = 0; i < num; ++i) {
            currentNode = currentNode.next;
        }
        currentNode.setTime(currentTimeMills);
        currentNode.setCounter(0L);
        lastNode = currentNode;
    }

    class Node {
        private long time;
        private long counter;
        private Node next;
        private int id;

        public Node(Node next) {
            this.next = next;
        }

        public Node() {

        }

        public Node(long time, long counter, int id) {
            this.time = time;
            this.id = id;
            this.counter = counter;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public void addCounter() {
            this.counter = this.counter + 1;
        }

        public void setCounter(long counter) {
            this.counter = counter;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
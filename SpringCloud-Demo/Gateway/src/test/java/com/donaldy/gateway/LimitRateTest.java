package com.donaldy.gateway;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author donald
 * @date 2021/02/18
 */
public class LimitRateTest {

    @Test
    public void testCounter() throws InterruptedException {

        Counter counter = new Counter();

        for (int i = 0 ;i < 20; ++i) {

            System.out.println(counter.acquire());

            if (i == 9) {

                System.out.println("等待 ===========");
                Thread.sleep(60 *1000);
            }
        }
    }
}

class Counter {

    public long timeStamp = System.currentTimeMillis();
    public AtomicInteger reqCount = new AtomicInteger(0);
    // 时间窗口内最大请求数
    public final int limit = 5;
    // 时间窗口ms 60s, 1分钟
    public final long interval = 60 * 1000;

    public boolean acquire() {

        long now = System.currentTimeMillis();

        if (now < timeStamp + interval) {

            // 在时间窗口内
            int cnt = reqCount.getAndIncrement();

            // 判断当前时间窗口内是否超过最大请求控制数
            return cnt <= limit;
        } else {
            // 重置
            timeStamp = now;
            // 超时后重置
            reqCount.set(1);
            return true;
        }
    }
}
package com.chen.example.thread.base.cfdemo.flow;


import com.chen.example.thread.tools.SleepTools;

import java.util.concurrent.CompletableFuture;

/**
 * 类说明：通过exceptionally进行补偿。
 */
public class Exceptionally {
    public static void main(String[] args) {
        String result = CompletableFuture.supplyAsync(() -> {
            SleepTools.second(1);
            if (1 == 1) {
                throw new RuntimeException("测试一下异常情况");
            }
            return "s1";
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return "hello world";
        }).join();
        System.out.println(result);
    }
}

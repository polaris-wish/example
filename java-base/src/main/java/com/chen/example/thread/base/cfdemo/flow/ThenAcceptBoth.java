package com.chen.example.thread.base.cfdemo.flow;


import com.chen.example.thread.tools.SleepTools;

import java.util.concurrent.CompletableFuture;

/**
 * 类说明：结合消费类
 */
public class ThenAcceptBoth {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            SleepTools.second(1);
            return "hello";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            SleepTools.second(2);
            return "world";
        }), (s1, s2) -> System.out.println(s1 + " " + s2));
        SleepTools.second(3);
    }
}

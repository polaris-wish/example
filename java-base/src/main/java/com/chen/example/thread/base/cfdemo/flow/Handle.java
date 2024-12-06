package com.chen.example.thread.base.cfdemo.flow;


import com.chen.example.thread.tools.SleepTools;

import java.util.concurrent.CompletableFuture;

/**
 * 类说明：运行后处理结果类
 */
public class Handle {
    public static void main(String[] args) {
        /*出现异常时*/
        String result = CompletableFuture.supplyAsync(() -> {
            SleepTools.second(1);
            //出现异常
            if (1 == 1) {
                throw new RuntimeException("测试一下异常情况");
            }
            return "s1";
        }).handle((s, t) -> {
            if (t != null) {
                return "hello world";
            }
            return s;
        }).join();
        System.out.println(result);

        /*未出现异常时*/
        String result2 = CompletableFuture.supplyAsync(() -> {
            SleepTools.second(1);
            return "s1";
        }).handle((s, t) -> {
            if (t != null) {
                return "hello world";
            }
            return s;
        }).join();
        System.out.println(result2);
    }
}

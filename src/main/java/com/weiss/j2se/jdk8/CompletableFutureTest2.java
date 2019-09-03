package com.weiss.j2se.jdk8;

import org.apache.log4j.Logger;
import org.testng.collections.Lists;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * 多个任务，有一个完成就算完成了。
 *
 * @Author: siwei
 * @Date: 2018/10/19
 */
public class CompletableFutureTest2 {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(CompletableFutureTest2.class);

    private static List<String> originalList = Lists.newArrayList("1", "2", "3", "4", "5");

    private static Boolean finalResult = false;
    private static List<CompletableFuture<String>> futures;

    private static List<String> resultList = Lists.newArrayList();


    public static void map() {
        futures = originalList.stream().map(msg -> CompletableFuture.completedFuture(msg).thenApplyAsync(s -> delayedHandle(s)))
                .collect(Collectors.toList());

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).whenCompleteAsync((v, th) -> {
            logger.info(Thread.currentThread().getName() + ",all finished");
            futures.forEach(cf -> {
                logger.info("is canceled: " + cf.isCancelled() + cf.isDone());
                if (!cf.isCancelled()) {
                    String result = cf.getNow("");
                    resultList.add(result);
                }
            });

//            logger.info(Thread.currentThread().getName() + ",countdown.");
        }).join();

//        logger.info(Thread.currentThread().getName() + ": " + resultList);
    }

    public static void main(String[] args) throws InterruptedException {


        map();


        System.out.println("=============== end: ============ " + resultList);

    }

    private static String delayedHandle(String s) {

        int sleepTime = Integer.parseInt(s) * 3000;
        try {
            logger.info(Thread.currentThread().getName() + "begin, value: " + s + ",sleep:" + sleepTime);
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            logger.error(Thread.currentThread().getName() + "error occured.", e);
        }

        return true + s;
    }

}

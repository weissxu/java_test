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
public class CompletableFutureTest {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(CompletableFutureTest.class);

    private static List<String> originalList = Lists.newArrayList("1", "2", "3", "4", "5");

    private static Boolean finalResult = false;
    private static final CountDownLatch latch = new CountDownLatch(1);
    private static List<CompletableFuture<String>> futures;

    private static List<String> resultList = Lists.newArrayList();


    public static void map(final CountDownLatch latch) {
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

            logger.info(Thread.currentThread().getName() + ",countdown.");
            latch.countDown();
        });

//        logger.info(Thread.currentThread().getName() + ": " + resultList);
    }

    public static void main(String[] args) throws InterruptedException {


        map(latch);
        latch.await();


        System.out.println("=============== end: ============ " + resultList);
//        CompletableFuture cf = null;
//
//        cf = CompletableFuture.completedFuture(originalList.get(0))
//                .thenApplyAsync(s -> delayedHandle(s));
////        cf = cf.handle((s, th) -> {
////            if (th instanceof CancellationException) {
////                logger.info("s value: " + s);
////                return "cacel";
////            }
////            return (th != null) ? "message upon cancel" : "";
////
////        });
////        cf.completeExceptionally(new RuntimeException("completed exceptionally"));
//
//        for (int i = 1; i < originalList.size(); i++) {
//            cf = cf.applyToEither(
//                    CompletableFuture.completedFuture(originalList.get(i)).thenApplyAsync(s ->
//                            delayedHandle(s)),
//                    s -> s + " from applyToEither");
//        }
//
//        Object join = cf.join();
//        logger.info("joined result: " + join);

    }

    private static String delayedHandle(String s) {

        int sleepTime = Integer.parseInt(s) * 3000;
        try {
            logger.info(Thread.currentThread().getName() + "begin, value: " + s + ",sleep:" + sleepTime);
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            logger.error(Thread.currentThread().getName() + "error occured.", e);
        }

//        if (sleepTime % 2 == 0) {
//            throw new CancellationException(s);
//        }

        logger.info(Thread.currentThread().getName() + "end, value: " + s);
        if (sleepTime == 6000) {
            logger.info(Thread.currentThread().getName() + "cacel..");

            futures.forEach(each -> {
                each.cancel(true);
            });
        }

        return true + s;
    }

}

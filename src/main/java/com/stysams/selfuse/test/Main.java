package com.stysams.selfuse.test;

import java.util.Arrays;
import java.util.concurrent.*;

/**
 * @author StysaMS
 */
public class Main {
    public static void main(String[] args) {
        //int[] a = {1, 2, 3, 4, 5};
        //int[] b = new int[5];
        //System.arraycopy(a, 2, b, 0,3);
        //System.out.println(Arrays.toString(b));
        //System.out.println(7 << 1);
        //System.out.println(7 >> 1);
        //System.out.println(15 << 1);
        //System.out.println(15 >> 2);
        //CompletableFuture.supplyAsync();

        new Thread(new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "";
            }
        }));
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 5L, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(10));
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executor.execute(() -> {
                System.out.println("线程任务".concat(String.valueOf(finalI)));
            });
        }
        System.out.println("over");
        executor.shutdown();
    }

    public static class FinallyDemo {
        public static void main(String[] args) {


            FinallyDemo finallyDemo = new FinallyDemo();
            //finallyDemo.finallyTestTryNoResult();
            //System.out.println(finallyDemo.finallyTestTryResult());
            //System.out.println(finallyDemo.finallyTestCatchResult());
            System.out.println(finallyDemo.finallyTestFinallyResult());
        }

        // 1.都没有返回值
        public void finallyTestTryNoResult() {
            try {
                System.out.println("try code block invoked");
                //int i = 1 / 0;
                throw new Exception();
            } catch (Exception e) {
                System.out.println("catch code block invoked");
            } finally {
                System.out.println("finally code block invoked");
            }
        }

        // 2.try有返回值
        public String finallyTestTryResult() {
            try {
                System.out.println("try code block invoked");
                return "no result";
                //throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("catch code block invoked");
            } finally {
                System.out.println("finally code block invoked");
            }
            return "result";

        }

        // 3.catch有返回值
        public String finallyTestCatchResult() {

            try {
                System.out.println("try code block invoked");
                //throw new Exception();
                int i = 1 / 0;
                return "no result";
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("catch code block invoked");
                return "error";
            } finally {
                System.out.println("finally code block invoked");
            }

        }

        // 4.finally有返回值
        public String finallyTestFinallyResult() {

            try {
                System.out.println("try code block invoked");
                //throw new Exception();
                int i = 1 / 0;
                return "no result";
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("catch code block invoked");
                return "error";
            } finally {
                System.out.println("finally code block invoked");
                return "success";
            }

        }


    }
}

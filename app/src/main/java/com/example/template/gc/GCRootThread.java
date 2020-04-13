package com.example.template.gc;

/**
 * 功能描述：验证 活跃线程 作为 GC Root
 * Created by gfq on 2020/4/13.
 */
public class GCRootThread {
    private int _10MB = 10 * 1024 * 1024;
    private byte[] memory = new byte[8 * _10MB];

    public static void main(String[] args) throws InterruptedException {

        System.out.println("初始值：");
        printMemory();

        AsyncTask asyncTask = new AsyncTask(new GCRootThread());
        Thread thread = new Thread(asyncTask);
        thread.start();
        System.out.println("线程开启后：");
        printMemory();

        System.gc();
        System.out.println("线程开启，执行GC后");
        printMemory();

        thread.join();
        asyncTask = null;
        System.gc();
        System.out.println("线程结束，执行GC后");
        printMemory();

    }

    private static class AsyncTask implements Runnable {

        private GCRootThread gcRootThread;

        public AsyncTask(GCRootThread gcRootThread) {
            this.gcRootThread = gcRootThread;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 打印当前JVM剩余空间和总的空间大小
     */
    public static void printMemory() {
        System.out.print("free is " + Runtime.getRuntime().freeMemory() / 1024 / 1024 + " M , ");
        System.out.println("total is " + Runtime.getRuntime().totalMemory() / 1024 / 1024 + " M");
    }
}

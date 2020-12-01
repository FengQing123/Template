package com.example.template.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能描述：
 * Created by gfq on 2019/12/20.
 */
public class Test {

    public static void main(String[] args) {
//        int[] array = {2, 3, 9, 6, 4, 3};
//        int[] array2 = BubbleSort(array);
//        for (int i = 0; i < array2.length; i++) {
//            System.out.println(array2[i]);
//        }
        doThread();
    }

    private static void doThread() {
        AtomicInteger mAi = new AtomicInteger();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (this) {
                        notify();
                        int i = mAi.incrementAndGet();
                        if (i <= 100) {
                            System.out.println(Thread.currentThread().getName() + ":" + i);
                        }
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
    }


    private static int[] BubbleSort(int[] sortArray) {
        for (int i = 1; i < sortArray.length; i++) {

            boolean flag = true;

            for (int j = 0; j < sortArray.length - i; j++) {
                if (sortArray[j + 1] < sortArray[j]) {
                    int temp = sortArray[j];
                    sortArray[j] = sortArray[j + 1];
                    sortArray[j + 1] = temp;
                    flag = false;
                }
            }

            if (flag) {
                break;
            }
        }
        return sortArray;
    }


    private static int[] InsertSort(int[] sortArray) {
        for (int i = 1; i < sortArray.length; i++) {

            int temp = sortArray[i];

            int j = i;
            while (j > 0 && temp < sortArray[j - 1]) {
                sortArray[j] = sortArray[j - 1];
                j--;
            }

            if (j != i) {
                sortArray[j] = temp;
            }
        }
        return sortArray;
    }

}

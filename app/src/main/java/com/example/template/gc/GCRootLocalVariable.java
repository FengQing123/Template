package com.example.template.gc;

/**
 * 功能描述：验证 虚拟机栈 （栈帧中的局部变量）中引用的对象作为 GC Root
 * Created by gfq on 2020/4/13.
 */
public class GCRootLocalVariable {

    private int _10MB = 10 * 1024 * 1024;
    private byte[] memory = new byte[8 * _10MB];

    public static void main(String[] args) {

        System.out.println("初始值：");
        printMemory();

        method();

        System.out.println("method 方法执行完成后 ");
        printMemory();

        System.gc();
        System.out.println("第二次GC完成后");
        printMemory();

    }

    public static void method() {
        GCRootLocalVariable g = new GCRootLocalVariable();
        System.out.println("创建 GCRootLocalVariable 对象后");
        printMemory();
        System.gc();
        System.out.println("第一次GC完成后");
        printMemory();
    }

    /**
     * 打印当前JVM剩余空间和总的空间大小
     */
    public static void printMemory() {
        System.out.print("free is " + Runtime.getRuntime().freeMemory() / 1024 / 1024 + " M , ");
        System.out.println("total is " + Runtime.getRuntime().totalMemory() / 1024 / 1024 + " M");
    }

}

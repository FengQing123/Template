package com.example.template.gc;

/**
 * 功能描述：验证 方法区 中的 静态变量 引用的对象 作为 GC Root
 * Created by gfq on 2020/4/13.
 */
public class GCRootStaticVariable {

    private static int _10MB = 10 * 1024 * 1024;
    private byte[] memory;
    private static GCRootStaticVariable staticVariable;

    public GCRootStaticVariable(int size) {
        memory = new byte[size];
    }

    public static void main(String[] args) {
        System.out.println("初始值：");
        printMemory();

        GCRootStaticVariable g = new GCRootStaticVariable(4 * _10MB);
        System.out.println("new GCRootStaticVariable(4 * _10MB)对象后");
        printMemory();

        g.staticVariable = new GCRootStaticVariable(8 * _10MB);
        System.out.println("g.staticVariable = new new GCRootStaticVariable(8 * _10MB)对象后");
        printMemory();

        g = null;//将 g 设置为 null, 调用GC时可以回收此对象内存，否则 GC 无效
        System.out.println("g = null 后");
        printMemory();

        System.gc();
        System.out.println("GC 完成后");
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

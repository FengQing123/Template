package com.example.template.generic;

/**
 * 功能描述：泛型继承关系
 * Created by gfq on 2020/4/2.
 */
public class Pair<T> {

    static class Employee {
    }

    static class Worker extends Employee {
    }

    public static void main(String[] args) {

        Pair<Employee> employeePair = new Pair<Employee>();
        Pair<Worker> workerPair = new Pair<Worker>();

        //Worker 是 Employee 的子类
        Employee employee = new Worker();

        //编译不通过：Pair<Employee> 和 Pair<Worker> 没有任何继承关系
//        Pair<Employee> employeePair1 = new Pair<Worker>();

        //编译通过：ExtendPair<T> extends Pair<T>
        Pair<Employee> pair = new ExtendPair<Employee>();

        //编译通过：set(Pair<Employee> p)
        set(employeePair);
        //编译不通过：set(Pair<Employee> p)：Pair<Employee> 和 Pair<Worker> 没有任何继承关系
//        set(workerPair);
        //编译不通过：set(Pair<Employee> p)：需要传入Pair对象
//        set(new Employee());
//        set(new Worker());
    }

    private static void set(Pair<Employee> p) {

    }

    //泛型可以继承 或者 扩展 其他泛型类，比如List 和ArrayList
    private static class ExtendPair<T> extends Pair<T> {

    }
}

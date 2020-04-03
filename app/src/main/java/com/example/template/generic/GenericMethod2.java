package com.example.template.generic;

/**
 * 功能描述：泛型方法
 * Created by gfq on 2020/4/2.
 */
public class GenericMethod2 {

    //这是个泛型类
    public static class Generic<T> {

        private T key;

        public Generic(T key) {
            this.key = key;
        }

        /**
         * 这不是泛型方法，只是个普通成员方法
         * 因为它的返回值是在 声明泛型类 已经声明过的泛型，所以才可以继续使用这个泛型
         *
         * @return
         */
        public T getKey() {
            return key;
        }

        /**
         * 这个方法，编译器会提示“cannot resolve symbol”
         * 因为类声明的泛型是 T ，不是 E，所以在使用 E 做形参和返回值类型时，编译器无法识别
         *
         * @param key
         * @return
         */
//        public E setKey(E key) {
//            this.key = key;
//        }

        /**
         * 这也不是泛型方法，只是使用Generic<Number> 这个泛型类做形参而已
         *
         * @param obj
         */
        public void show(Generic<Number> obj) {

        }

        //有问题，类只声明T，未声明E，编译器无法处理E
//        public <T> T show2(Generic<E> abj) {
//
//        }

        //有问题，类只声明T，未声明E，编译器无法处理E
//        public <T, E> T show3(E abj) {
//            return abj;
//        }
    }
}

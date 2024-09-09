package com.dd.thuctd;

@FunctionalInterface
public interface FunctionInterface {
    // have only one method abstract
    int compareTo(int a, int b);

    // override method from java.lang.Object
    @Override
    boolean equals(Object obj);

    // default method khong anh huong den tinh hop le
    static int staticMethod(int a, int b) {
        return a;
    }

    default int method(int a) {
        return a;
    }

    // mot so function interface trong jdk: Runnable, Comparable, ActionListener, Callable, ConSumer, BiFunfion<T, U, R>

    public static void main(String[] args) {

    }
}

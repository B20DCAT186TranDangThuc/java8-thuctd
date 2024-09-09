package com.dd.thuctd.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class LamdaExpression {
    @FunctionalInterface
    interface Operator<T> {
        T process(T a, T b);
    }

    public static void main(String[] args) {
        // can have zero, one or more parameters.
        Operator<Integer> addOperator = (a, b) -> a + b;
        System.out.println(addOperator.process(2, 3));

        Consumer<String> oneParam = s -> System.out.println("One parameter: " + s);
        oneParam.accept("One");

        // nhieu cau lenh trong than
        BiFunction<Integer, Integer, String> moreThan = (a, b) -> {
            if (a > b) {
                return "a > b";
            } else {
                return "a <= b";
            }
        };
        System.out.println(moreThan.apply(1, 2));

        // Ex1: print all element in list
        List<String> list = new ArrayList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");
        list.forEach(e -> System.out.println(e));

        // Addvantages: ngan gon, khong can su dung anonymous class
    }
}

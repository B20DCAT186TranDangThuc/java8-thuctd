package com.dd.thuctd.flatmap;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FlatMApExample2 {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6};

        Stream<int[]> streamArray = Stream.of(array);

        // doi voi primitive, java8 ho tro flatMapTo{primitive type}
        IntStream intStream = streamArray.flatMapToInt(x -> Arrays.stream(x));

        intStream.forEach(System.out::println);
    }
}

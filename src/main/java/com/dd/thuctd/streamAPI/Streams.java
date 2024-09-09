package com.dd.thuctd.streamAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {

    public static void toAList() {
        List<Integer> list = new ArrayList<Integer>();

        for (int i = 1; i < 10; i++) {
            list.add(i);
        }

        Stream<Integer> stream = list.stream();
        List<Integer> evenNumbersList = stream.filter(i -> i % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(evenNumbersList);
    }

    public static void toAnArray() {
        List<Integer> list = new ArrayList<Integer>();

        for (int i = 1; i < 10; i++) {
            list.add(i);
        }

        Stream<Integer> stream = list.stream();
        Integer[] evenNumbersArr = stream.filter(i -> i % 2 == 0).toArray(Integer[]::new);
        System.out.print(evenNumbersArr);
    }

    public static void main(String[] args) {
        // CREATE STREAM
        // C1: Stream.of
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        // C2: Stream.of(array)
        Stream<Integer> stream2 = Stream.of(new Integer[]{1, 2, 3, 4, 5});
        // C3: List.stream
        List<Integer> list = new ArrayList<>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
        }};
        Stream<Integer> stream3 = list.stream();
        // C4: Stream.generate()
        Stream<Integer> stream4 = Stream.generate(() -> (new Random()).nextInt(100));


        // Stream Collectors:
        // to a list
        toAList();
        // to an array
        toAnArray();


        // String Operator
        List<String> memberNames = new ArrayList<>();
        memberNames.add("Amitabh");
        memberNames.add("Shekhar");
        memberNames.add("Aman");
        memberNames.add("Rahul");
        memberNames.add("Shahrukh");
        memberNames.add("Salman");
        memberNames.add("Yana");
        memberNames.add("Lokesh");
        // filter: loc cac pthu theo dieu kien
        memberNames.stream().filter(s -> s.startsWith("A")).forEach(System.out::println);

        // map: chuyen doi tung doi thuong trong stream thanh doi tuong khac
        memberNames.stream().map(String::toUpperCase).forEach(System.out::println);

        // sorted: sap xep cacs phan tu trong stream
        memberNames.stream().sorted().forEach(System.out::println);

        // ....
    }
}

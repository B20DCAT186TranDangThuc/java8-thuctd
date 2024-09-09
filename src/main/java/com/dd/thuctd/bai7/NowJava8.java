package com.dd.thuctd.bai7;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NowJava8 {
    public static void main(String[] args) {
//        List<String> lines = Arrays.asList("spring", "node", ".net");
//
//        List<String> res = lines.stream()
//                .filter(line -> !".net".equals(line))
//                .collect(Collectors.toList());
//
//        res.forEach(System.out::println);

        List<Person> persons = Arrays.asList(
                new Person("mkyong", 30),
                new Person("jack", 20),
                new Person("lawrence", 40)
        );

        String res = persons.stream()
                .filter(p -> p.getName().equals("jack"))
                .map(Person::getName)
                .findAny()
                .orElse(null);
        System.out.println(res);

        // multiple condition
        String res2 = persons.stream()
                .filter(p -> p.getName().equals("jack") && p.getAge() == 20)
                .map(Person::getName)
                .findAny()
                .orElse(null);
        System.out.println(res2);
    }
}

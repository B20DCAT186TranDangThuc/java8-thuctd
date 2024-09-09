package com.dd.thuctd.bai7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BeforeJava8 {

    public static List<String> getFilterOutput(List<String> lines, String filter) {
        List<String> res = new ArrayList<>();

        for (String line : lines) {
            if (!filter.equals(line)) {
                res.add(line);
            }
        }
        return res;
    }

    public static Person getStudentByName(List<Person> persons, String name) {
        Person person = null;
        for (Person p : persons) {
            if (p.getName().equals(name)) {
                person = p;
            }
        }
        return person;
    }

    public static void main(String[] args) {
//        List<String> list = Arrays.asList("spring", "node", "mkyong");
//        List<String> result = getFilterOutput(list, "spring");
//        result.forEach(System.out::println);

        List<Person> persons = Arrays.asList(
                new Person("mkyong", 30),
                new Person("jack", 20),
                new Person("lawrence", 40)
        );

        Person result = getStudentByName(persons, "lawrence");
        System.out.println(result);
    }
}

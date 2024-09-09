package com.dd.thuctd.flatmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMap {

    public static void main(String[] args) {
        // flatMap xu ly truong hop ban muon lam phang mot ctdl
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("1", "2", "3"),
                Arrays.asList("4", "5", "6"),
                Arrays.asList("7", "8", "9")
        );

        List<String> flatList = listOfLists.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println(flatList);
    }

}

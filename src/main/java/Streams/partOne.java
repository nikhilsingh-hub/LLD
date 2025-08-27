package Streams;

import java.util.*;
import java.util.stream.*;

public class partOne {
    public static Map<Integer, List<String>> filterFruitsStartingWithA(List<String> fruits) {
         return fruits.stream()
                .collect(Collectors.groupingBy(String::length));
    }
    public static List<Integer> distinctmy(List<Integer> numbers) {
        return numbers.stream().distinct().toList();
    }
    public static void main(String[] args) {

        List<Integer> numbers =  new ArrayList<>(List.of(1,2,2,3,3,4,5,6));
        List<String> words = Arrays.asList("apple", "banana", "orange", "grape", "kiwi");

        Stream<Integer> stream =  numbers.stream();

//        System.out.println(stream.filter(x -> x%2 != 0).toList());
//        System.out.println(distinctmy(numbers));

        List<String> allFruits = Arrays.asList("Apple", "Banana", "Apricot", "Avocado", "Orange");
        Map<Integer, List<String>> fruitsStartingWithA = filterFruitsStartingWithA(allFruits);
        System.out.println("Fruits starting with 'A': " + fruitsStartingWithA);

    }
}

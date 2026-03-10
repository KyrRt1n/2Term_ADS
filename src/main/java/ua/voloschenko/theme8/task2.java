package ua.voloschenko.theme8;

import java.util.ArrayList;
import java.util.List;

public class task2 {

    public static void main(String[] args) {
        List<Double> nums = new ArrayList<>();
        nums.add(2.0);
        nums.add(1.2);
        nums.add(-3.5);
        List<Double> empty = new ArrayList<>();
        System.out.println(firstOrNull(nums));
        System.out.println(firstOrNull(empty));

        List<Integer> ints = List.of(1,2,22);
        List<Double> doubles = List.of(1.2,2.1,2.25);
        System.out.println(sum(ints));    // 25
        System.out.println(sum(doubles)); // 5.55

        List<Number> numbers = new ArrayList<>();
        addDefaultIds(numbers);
        System.out.println(numbers);

    }

    public static <T> T firstOrNull(List<T> list) {
        return list.isEmpty() ? null : list.get(0);
    }

    public static double sum(List<? extends Number> list) {
        double result = 0;
        for (Number number : list) {
            result += number.doubleValue();
        }
        return result;
    }

    public static void addDefaultIds(List<? super Integer> list){
        list.add(1);
        list.add(2);
        list.add(3);
    }



}

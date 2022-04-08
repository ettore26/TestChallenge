package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        int[] array;
        int targetNumber;

        array = new int[]{7, 6, 4, -1, 1, 2};
        targetNumber = 16;
        fourNumberSum(array, targetNumber)
                .forEach(e -> System.out.println(Arrays.toString(e)));
        // [
        // [7, 6, 4, -1],
        // [7, 6, 1, 2]
        // ]

        array = new int[]{1, 2, 3, 4, 5, 6, 7};
        targetNumber = 10;
        fourNumberSum(array, targetNumber)
                .forEach(e -> System.out.println(Arrays.toString(e)));
        // [
        //  [1, 2, 3, 4]
        //]

        array = new int[]{-2, -1, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        targetNumber = 4;
        fourNumberSum(array, targetNumber)
                .forEach(e -> System.out.println(Arrays.toString(e)));
        // [
        //  [-2, -1, 1, 6],
        //  [-2, 1, 2, 3],
        //  [-2, -1, 2, 5],
        //  [-2, -1, 3, 4]
        //]
    }

    public static List<Integer[]> fourNumberSum(int[] array, int targetSum) {
        List<Integer[]> finalResult = new ArrayList<>();

        ArrayList<ArrayList<Integer>> mixedList = mix(array);
        for (ArrayList<Integer> mixed : mixedList) {

            boolean isTargetSum = (mixed.stream()
                    .mapToInt(Integer::intValue)
                    .sum() == targetSum);

            if (mixed.size() == 4 && isTargetSum)
                finalResult.add(mixed.toArray(new Integer[0]));
        }

        return finalResult;
    }

    private static ArrayList<ArrayList<Integer>> mix(int[] nums) {
        if (nums.length <= 0) {
            ArrayList<ArrayList<Integer>> emptyList = new ArrayList<ArrayList<Integer>>(new ArrayList<>());
            emptyList.add(new ArrayList<>());
            return emptyList;
        }

        Integer first = nums[0];
        int[] copyListNoFirst = Arrays.copyOfRange(nums, 1, nums.length);
        ArrayList<ArrayList<Integer>> mixedNoFirstList = mix(copyListNoFirst);

        ArrayList<ArrayList<Integer>> mixedWithFirstList = new ArrayList<>();
        for (ArrayList<Integer> num : mixedNoFirstList) {
            ArrayList<Integer> newValue = new ArrayList<>(num);
            newValue.add(first);
            mixedWithFirstList.add(newValue);
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>(mixedNoFirstList);
        result.addAll(mixedWithFirstList);
        return result;
    }

}

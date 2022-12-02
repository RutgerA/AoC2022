package assignment1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.FileReader;

public class SolutionTwo {
    private static final String INPUT_FILE_NAME = "AoC2022/src/assignment1/input.txt";
    private static final String DEFAULT_LINE_SEPARATOR = ";";

    private static List<String> parseData(String data) {
        final String[] splittedData = data.split(DEFAULT_LINE_SEPARATOR);
        final List<String> elfs = new ArrayList<String>();
        final StringBuilder calories = new StringBuilder();
        for (final String line : splittedData) {
            if(line == ""){
                elfs.add(calories.toString());
                calories.setLength(0);
            }

            if (calories.toString() != "") calories.append(DEFAULT_LINE_SEPARATOR);
            calories.append(line);
        }

        return elfs;
    }

    private static int[] doCalorieSum(final List<String> parsedData) {
        final int[] result = new int[parsedData.size()];
        int idx = 0;
        for (final String elf : parsedData) {
            final String[] elfCaloriesSplitted = elf.split(DEFAULT_LINE_SEPARATOR);
            int sumOfCalories = 0;
            for (final String calorie : elfCaloriesSplitted) {
                sumOfCalories += Integer.parseInt(calorie);
            }
            
            result[idx] = sumOfCalories;
            ++idx;
        }

        return result;
    }

    private static int getSumOfHighestThreeCalories(final int[] values) {
        final int[] sorted = Arrays.stream(values).sorted().toArray();
        return sorted[sorted.length - 1] + sorted[sorted.length - 2] + sorted[sorted.length - 3];
    }

    public static void main(final String[] args) {
        final String data = FileReader.read(INPUT_FILE_NAME, DEFAULT_LINE_SEPARATOR);
        final List<String> parsedData = parseData(data);
        final int[] sumOfAllCalories = doCalorieSum(parsedData);

        final int result = getSumOfHighestThreeCalories(sumOfAllCalories);

        System.out.println(result);
        System.out.println(result == 200044);
    }
}

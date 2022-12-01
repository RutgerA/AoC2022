package assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SolutionTwo {
    private static final String INPUT_FILE_NAME = "AdventOfCode2022/src/assignment1/input.txt";
    private static final String DEFAULT_LINE_SEPARATOR = ";";
    
    public static String readFile(final String filename) {
        final File inputFile = new File(filename);
        final StringBuilder sb = new StringBuilder();

        try (Scanner fileReader = new Scanner(inputFile)) {
            while (fileReader.hasNextLine()) {
                sb.append(fileReader.nextLine());
                sb.append(DEFAULT_LINE_SEPARATOR);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

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

    public static void main(final String[] args) {
        final String data = readFile(INPUT_FILE_NAME);
        final List<String> parsedData = parseData(data);
        final int[] sumOfAllCalories = doCalorieSum(parsedData);
        
        // for (final int calorieSum : sumOfAllCalories) {
        //     System.out.println(calorieSum);
        // }
        
        // System.out.println(Arrays.stream(sumOfAllCalories).max().getAsInt());
        final int[] sortedArray = Arrays.stream(sumOfAllCalories).sorted().toArray();
        final int sumOfLastThree = sortedArray[sortedArray.length -1] + sortedArray[sortedArray.length -2] + sortedArray[sortedArray.length -3];


        System.out.println(sumOfLastThree);
    }

}

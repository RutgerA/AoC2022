package assignment3;

import java.util.HashMap;

import utils.FileReader;

public class SolutionOne {
    private static final String INPUT_FILE_NAME = "AoC2022/src/assignment3/input.txt";
    private static final String DEFAULT_LINE_SEPARATOR = ";";
    private static final String DICTIONARY = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final HashMap<Character, Integer> priority = new HashMap<Character, Integer>();

    private static String[][] splitRucksacks (final String[] rucksacks) {
        final String[][] sucksacksCompartments = new String[rucksacks.length][];
        for (int idx = 0; idx < rucksacks.length; ++idx) {
            final String s = rucksacks[idx];
            final int mid = s.length() / 2;
            sucksacksCompartments[idx] = new String[]{ s.substring(0, mid), s.substring(mid) };
        }

        return sucksacksCompartments;
    }

    private static void calculatePriorities() {
        for(int idx = 0; idx < DICTIONARY.length(); ++idx ) {
            priority.put(DICTIONARY.charAt(idx), idx + 1);
        }
    }

    public static void main(final String[] args) {
        final String data = FileReader.read(INPUT_FILE_NAME, DEFAULT_LINE_SEPARATOR);
        final String[] rucksacks = data.split(DEFAULT_LINE_SEPARATOR);
        final String[][] rucksacksCompartments = splitRucksacks(rucksacks);
        calculatePriorities();
        
        int sum = 0;
        for (final String[] compartments : rucksacksCompartments) {
            final HashMap<Character, Integer> leftsideMapping = new HashMap<Character, Integer>();
            final String compartmentOne = compartments[0];
            final String compartmentTwo = compartments[1];
            final int length = compartmentOne.length();
            for (int idx = 0; idx < length; ++idx) {
                final char item = compartmentOne.charAt(idx);
                leftsideMapping.put(item, priority.get(item));
            }

            for (int idx = 0; idx < length; ++idx) {
                final Integer foundMatchingItem = leftsideMapping.get(compartmentTwo.charAt(idx));
                if (foundMatchingItem != null) {
                    sum += foundMatchingItem;
                    break;
                }
            }
        }
        
        System.out.println(sum);
        System.out.println(sum == 8088);
    }
}

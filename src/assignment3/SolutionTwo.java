package assignment3;

import java.util.HashMap;

import utils.FileReader;

public class SolutionTwo {
    private static final String INPUT_FILE_NAME = "AoC2022/src/assignment3/input.txt";
    private static final String DEFAULT_LINE_SEPARATOR = ";";
    private static final String DICTIONARY = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final HashMap<Character, Integer> priority = new HashMap<Character, Integer>();
    
    private static String[][] splitRucksacks (final String[] rucksacks) {
        final int groupSize = rucksacks.length / 3;
        final String[][] sucksacksCompartments = new String[groupSize][];
        int kdx = 0;
        for (int idx = 0; idx < groupSize; ++idx) {
            sucksacksCompartments[idx] = new String[] {
                rucksacks[kdx], rucksacks[kdx+1], rucksacks[kdx+2]
            };
            kdx += 3;
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
        final String[] rucksacksCombined = data.split(DEFAULT_LINE_SEPARATOR);
        final String[][] rucksacksGroups = splitRucksacks(rucksacksCombined);
        calculatePriorities();
        
        int sum = 0;
        for (final String[] rucksacks : rucksacksGroups) {
            final HashMap<Character, Integer> firstRucksackMapping = new HashMap<Character, Integer>();
            final HashMap<Character, Integer> secondRucksackMapping = new HashMap<Character, Integer>();
            final String rucksackOne = rucksacks[0];
            final String rucksackTwo = rucksacks[1];
            final String rucksackThree = rucksacks[2];

            for (int idx = 0; idx < rucksackOne.length(); ++idx) {
                final char item = rucksackOne.charAt(idx);
                firstRucksackMapping.put(item, priority.get(item));
            }

            for (int idx = 0; idx < rucksackTwo.length(); ++idx) {
                final char item = rucksackTwo.charAt(idx);
                secondRucksackMapping.put(item, priority.get(item));
            }

            for (int idx = 0; idx < rucksackThree.length(); ++idx) {
                final Integer foundMatchingItemOne = firstRucksackMapping.get(rucksackThree.charAt(idx));
                final Integer foundMatchingItemTwo = secondRucksackMapping.get(rucksackThree.charAt(idx));
                if (foundMatchingItemOne != null && foundMatchingItemTwo != null) {
                    sum += foundMatchingItemOne;
                    break;
                }
            }
        }
        
        System.out.println(sum);
        System.out.println(sum == 2522);
    }
}

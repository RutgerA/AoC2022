package assignment2;

import java.util.HashMap;

import utils.FileReader;

public class SolutionOne {
    private static final String INPUT_FILE_NAME = "AoC2022/src/assignment2/input.txt";
    private static final String DEFAULT_LINE_SEPARATOR = ";";
    private static final HashMap<String, Integer> PLAYER_ONE_VALUES = new HashMap<String, Integer>();
    private static final HashMap<String, Integer> PLAYER_TWO_VALUES = new HashMap<String, Integer>();

    private static void initPlayerValues() {
        final HashMap<String, Integer> abcMapping = new HashMap<String, Integer>();
        abcMapping.put("A", 1);
        abcMapping.put("B", 2);
        abcMapping.put("C", 3);

        final HashMap<String, Integer> xyzMapping = new HashMap<String, Integer>();
        xyzMapping.put("X", 1);
        xyzMapping.put("Y", 2);
        xyzMapping.put("Z", 3);

        final String[] abc = new String[] {
            "A", "B", "C"
        };

        final String[] xyz = new String[] {
            "X", "Y", "Z"
        };

        for (final String p1 : abc) {
            for (final String p2 : xyz) {
                final String match = p1 + " " + p2;
                final int p1value = abcMapping.get(p1);
                final int p2value = xyzMapping.get(p2);

                if (p1value == p2value) {
                    PLAYER_ONE_VALUES.put(match, p1value + 3);
                    PLAYER_TWO_VALUES.put(match, p2value + 3);
                    continue;
                }

                if (p1value > p2value) {
                    if (p1value - p2value == 2) {
                        PLAYER_ONE_VALUES.put(match, p1value);
                        PLAYER_TWO_VALUES.put(match, p2value + 6);
                        continue;
                    }
                    PLAYER_ONE_VALUES.put(match, p1value + 6);
                    PLAYER_TWO_VALUES.put(match, p2value);
                    continue;
                }

                if (p2value - p1value == 2) {
                    PLAYER_ONE_VALUES.put(match, p1value + 6);
                    PLAYER_TWO_VALUES.put(match, p2value);
                    continue;
                }
                PLAYER_ONE_VALUES.put(match, p1value);
                PLAYER_TWO_VALUES.put(match, p2value + 6);
            }
        }
    }

    private static int calcSumOfPlayerTwoStrategy(final String[] matches) {
        int resultPlayerTwo = 0;
        for (final String match : matches) {
            resultPlayerTwo += PLAYER_TWO_VALUES.get(match);
        }

        return resultPlayerTwo;
    }

    public static void main(final String[] args) {
        final String data = FileReader.read(INPUT_FILE_NAME, DEFAULT_LINE_SEPARATOR);
        initPlayerValues();

        final String[] matches = data.split(DEFAULT_LINE_SEPARATOR);
        final int resultPlayerTwo = calcSumOfPlayerTwoStrategy(matches);

        System.out.println(resultPlayerTwo);
        System.out.println(resultPlayerTwo == 11150);
    }

}

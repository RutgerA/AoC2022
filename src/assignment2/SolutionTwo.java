package assignment2;

import java.util.HashMap;

import utils.FileReader;

public class SolutionTwo {
    private static final String INPUT_FILE_NAME = "AoC2022/src/assignment2/input.txt";
    private static final String DEFAULT_LINE_SEPARATOR = ";";
    private static final HashMap<String, Integer> PLAYER_ONE_VALUES = new HashMap<String, Integer>();
    private static final HashMap<String, Integer> PLAYER_TWO_VALUES = new HashMap<String, Integer>();

    private static void initPlayerValues() {
        final HashMap<String, Integer> abcMapping = new HashMap<String, Integer>();
        abcMapping.put("A", 1);
        abcMapping.put("B", 2);
        abcMapping.put("C", 3);

        final String[] abc = new String[] {
            "A", "B", "C"
        };

        for (final String p1 : abc) {
            final int p1value = abcMapping.get(p1);
            final String matchX = p1 + " X";
            PLAYER_ONE_VALUES.put(matchX, p1value + 6);
            PLAYER_TWO_VALUES.put(matchX, p1value - 1 == 0 ? 3 : p1value - 1);
            final String matchY = p1 + " Y";
            PLAYER_ONE_VALUES.put(matchY, p1value + 3);
            PLAYER_TWO_VALUES.put(matchY, p1value + 3);
            final String matchZ = p1 + " Z";
            PLAYER_ONE_VALUES.put(matchY, p1value);
            PLAYER_TWO_VALUES.put(matchZ, ((p1value % 3) + 1) + 6);
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
        System.out.println(resultPlayerTwo == 8295);
    }

}

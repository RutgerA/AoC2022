package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    
    private FileReader() {}

    public static String read(final String filename, final String lineSeparator) {
        final File inputFile = new File(filename);
        final StringBuilder sb = new StringBuilder();

        try (Scanner fileReader = new Scanner(inputFile)) {
            while (fileReader.hasNextLine()) {
                sb.append(fileReader.nextLine());
                sb.append(lineSeparator);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}

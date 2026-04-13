package laba.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * The class that reads the console
 */
public class ConsoleManager {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String readLine() {
        try {
            System.out.print(">>> ");
            String readerString = reader.readLine();
            if (readerString == null) {
                System.out.println("Выход из программы");
                System.exit(0);
            }
            return readerString;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}

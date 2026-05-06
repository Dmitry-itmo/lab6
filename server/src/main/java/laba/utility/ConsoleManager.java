package laba.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.slf4j.*;

import laba.serverUtility.ConnectManager;


/**
 * The class that reads the console
 */
public class ConsoleManager {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final Logger logger = LoggerFactory.getLogger(ConnectManager.class);

    public static String readLine() {
        try {
            String readerString = reader.readLine();
            if (readerString == null) {
                logger.info("Выход из программы");
                System.exit(0);
            }
            return readerString;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static BufferedReader getReader() {
        return reader;
    }
}

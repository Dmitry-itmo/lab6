package laba.commands;

import java.util.ArrayDeque;

import org.slf4j.*;

import laba.serverUtility.ConnectManager;
/**
 * Shows the history of the last 9 teams
 */
public class HistoryCommand implements Command{
    private static final Logger logger = LoggerFactory.getLogger(ConnectManager.class);
    private static ArrayDeque<String> historyCommand = new ArrayDeque<>();

    public static void addCommand(String command) {
        if (historyCommand.size() < 9) {
            historyCommand.addLast(command);
        } else {
            historyCommand.removeFirst();
            historyCommand.addLast(command);
        }
    }

    
    @Override
    public void execute() {
        for (String command : historyCommand) {
            logger.info(command);
        }
        logger.info("Выполнена команда history");
    }

    @Override
    public String toString() {
        return "history - выводит последние 9 команд (без их аргументов)";
    }
}

package laba.commands;

import java.util.Map;

import org.slf4j.*;

import laba.serverUtility.ConnectManager;
import laba.utility.CommandManager;
/**
 * A command that outputs a description of each command
 */
public class HelpCommand implements Command{
    private static final Logger logger = LoggerFactory.getLogger(ConnectManager.class);
    
    public void execute() {
        for (Map.Entry<String, Command> entry : CommandManager.getCommands().entrySet()) {
            logger.info(entry.getValue().toString());
        }
        logger.info("Выполнена команда help");
    }

    @Override
    public String toString() {
        return "help - выводит справку по доступным командам";
    }

}

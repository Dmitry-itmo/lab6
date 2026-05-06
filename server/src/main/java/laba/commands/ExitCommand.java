package laba.commands;

import java.io.Serializable;

import org.slf4j.*;

import laba.serverUtility.ConnectManager;

public class ExitCommand implements Command,Serializable{
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ConnectManager.class);

    @Override
    public void execute() {
        logger.info("Завершение работы сервера");
        System.exit(0);
    }
    
    @Override
    public String toString() {
        return "exit - завершает программу (без сохранения файла)";
    }
}

package laba.commands;

import java.io.Serializable;

import org.slf4j.*;

import laba.serverUtility.ConnectManager;
import laba.utility.CollectionManager;
/**
 * The command that shows information about the collection
 */
public class InfoCommand implements Command, Serializable{
    private static final Logger logger = LoggerFactory.getLogger(ConnectManager.class);
    @Override
    public void execute() {
       logger.info("Информация о коллекции:\n  Длина: {}\n  Тип: {}\n  Дата инициализации: {}", 
            CollectionManager.getCollection().size(), 
            CollectionManager.getCollection().getClass(), 
            CollectionManager.getCreationDate());
        logger.info("Выполнена команда info");
    }
    
    @Override
    public String toString() {
        return "info - выводит информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}


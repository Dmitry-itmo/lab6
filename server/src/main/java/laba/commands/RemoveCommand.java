package laba.commands;

import laba.utility.CollectionManager;

import java.io.IOException;
import java.io.Serializable;

import org.slf4j.*;

import laba.data.*;
import laba.exceptions.*;
import laba.serverUtility.ConnectManager;
/**
 * The command deletes a collection item by its ID
 */
public class RemoveCommand implements Command,Serializable{
    private SpaceMarine spaceMarine;
    private static final long serialVersionUID = 1L;

    public SpaceMarine getSpaceMarine() {
        return spaceMarine;
    }
    private static final Logger logger = LoggerFactory.getLogger(ConnectManager.class);
    @Override
    public void execute() {
        try {
            SpaceMarine spaceMarine1 = CollectionManager.searchSpaceMarine(getSpaceMarine().getId());
            CollectionManager.removeElement(spaceMarine1);
            SpaceMarine.removeID(spaceMarine1.getId());
            logger.info("Элемент с ID " + spaceMarine1.getId() + " удален");
        } catch (Exception e) {
            logger.error("Ошибка в {}", spaceMarine.getId());
        }
    }

    public void execute(String id) throws IncorrectCommandException, IncorrectIDException{
        SpaceMarine spaceMarine = CollectionManager.searchSpaceMarine(Integer.parseInt(id));
        CollectionManager.removeElement(spaceMarine);
        SpaceMarine.removeID(Integer.parseInt(id));
        logger.info("Элемента с ID {} удален", id);
        logger.info("Выполнена команда remove");
    } 

    @Override
    public String toString() {
        return "remove - удаляет элемент из коллекции по его ID";
    }
    
}

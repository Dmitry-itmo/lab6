package laba.commands;

import laba.data.*;
import laba.exceptions.IncorrectCommandException;
import laba.exceptions.IncorrectIDException;
import laba.serverUtility.ConnectManager;
import laba.utility.*;
import static laba.utility.ConsoleManager.*;

import java.io.Serializable;

import org.slf4j.*;

/**
 * The command updates information about a collection item whose id is equal to the specified one.
 */
public class UpdateCommand implements Command,Serializable{
    private static final Logger logger = LoggerFactory.getLogger(ConnectManager.class);
    private static final long serialVersionUID = 1L;

    private SpaceMarine spaceMarine;

    public SpaceMarine getSpaceMarine(){
        return spaceMarine;
    }
    @Override
    public void execute() {
        try {
            SpaceMarine oldSpaceMarine = CollectionManager.searchSpaceMarine(getSpaceMarine().getId());
            CollectionManager.removeElement(oldSpaceMarine);
            CollectionManager.addSpaceMarine(getSpaceMarine());
            logger.info("Выполнена команда update");
            logger.info("Обновлен элемент с ID {}", getSpaceMarine().getId());
        } catch (Exception e) {
            logger.error("Ошибка в update " + e);
        } 

    }

    @Override
    public void execute(String ID) throws IncorrectCommandException,IncorrectIDException {
        
    }

    @Override
    public String toString() {
        return "update - обновить значения элемента коллекции, id которого равен заданному";
    }
}

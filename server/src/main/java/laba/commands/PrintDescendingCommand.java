package laba.commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import org.slf4j.*;

import laba.data.SpaceMarine;
import laba.serverUtility.ConnectManager;
import laba.utility.CollectionManager;
/**
 * A command that outputs the elements of a collection in descending order
 */
public class PrintDescendingCommand implements Command,Serializable{
    private static final Logger logger = LoggerFactory.getLogger(ConnectManager.class);
    @Override
    public void execute() {
        if (CollectionManager.getCollection().size() == 0) {
            logger.info("В коллекции нет элементов");
            return;
        }
        ArrayList<SpaceMarine> list = new ArrayList<>(CollectionManager.getCollection());
        Collections.sort(list);
        Collections.reverse(list);
        for (SpaceMarine spaceMarine : list) {
            logger.info(spaceMarine.toString());
        }
        
        logger.info("Выполнена команда print_descending");
    }

    @Override
    public String toString() {
        return "print_descending - выводит элементы коллекции в порядке убывания";
    }
    
}

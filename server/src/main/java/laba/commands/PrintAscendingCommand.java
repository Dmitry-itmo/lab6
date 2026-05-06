package laba.commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import org.slf4j.*;

import laba.data.SpaceMarine;
import laba.serverUtility.ConnectManager;
import laba.utility.CollectionManager;
/**
 * A command that outputs the elements of a collection in ascending order
 */
public class PrintAscendingCommand implements Command,Serializable{
    private static final Logger logger = LoggerFactory.getLogger(ConnectManager.class);

    private static final long serialVersionUID = 1L;

    private SpaceMarine spaceMarine;

    public SpaceMarine getSpaceMarine() {
        return spaceMarine;
    }


    @Override
    public void execute() {
        if (CollectionManager.getCollection().size() == 0) {
            logger.info("В коллекции нет элементов");
            return;
        }
        ArrayList<SpaceMarine> list = new ArrayList<>(CollectionManager.getCollection());
        Collections.sort(list);


        for (SpaceMarine spaceMarine : list) {
            logger.info(spaceMarine.toString());
        }
        
        
        logger.info("Выполнена команда print_ascending");
    }

    @Override
    public String toString() {
        return "print_ascending - выводит элементы коллекции в порядке возрастания";
    }
}

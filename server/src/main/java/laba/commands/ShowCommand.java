package laba.commands;

import java.io.Serializable;
import java.util.ArrayList;

import org.slf4j.*;

import laba.data.SpaceMarine;
import laba.serverUtility.ConnectManager;
import laba.sorter.SortBySpaceMarineID;
import laba.utility.CollectionManager;
/**
 * The command shows information about each item in the collection
 */
public class ShowCommand implements Command,Serializable{
    private static final Logger logger = LoggerFactory.getLogger(ConnectManager.class);
    @Override
    public void execute() {
        if (CollectionManager.getCollection().size() == 0) {
            logger.info("В коллекции нет элементов");
            return;
        }

        ArrayList<SpaceMarine> list = new ArrayList<>(CollectionManager.getCollection());
        list.sort(new SortBySpaceMarineID());

        StringBuilder sb = new StringBuilder("Результат команды show:\n");

        for (SpaceMarine spaceMarine : list) {
            sb.append(spaceMarine.toString()).append("\n");
        }
        logger.info(sb.toString());
        
        logger.info("Выполнена команды show");
    }
    
    @Override
    public String toString() {
        return "show - выводит все элементы коллекции в строковом представлении";
    }

}

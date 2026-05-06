package laba.commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import org.slf4j.*;

import laba.data.SpaceMarine;
import laba.serverUtility.ConnectManager;
import laba.utility.CollectionManager;
/**
 * The command deletes all items from the collection that are less than the specified value.
 */
public class RemoveLowerCommand implements Command,Serializable{
    private static final Logger logger = LoggerFactory.getLogger(ConnectManager.class);
    private SpaceMarine spaceMarine;
    private static final long serialVersionUID = 1L;

    public SpaceMarine getSpaceMarine() {
        return spaceMarine;
    }
    @Override
    public void execute() {
        ArrayList<SpaceMarine> list = new ArrayList<>(CollectionManager.getCollection());
    
        list.add(getSpaceMarine());
        Collections.sort(list);
        Collections.reverse(list);
        int index = list.indexOf(getSpaceMarine());
        for (int i = index+1; i < list.size(); i++) {
            CollectionManager.removeElement(list.get(i));
            SpaceMarine.removeID(list.get(i).getId());
            logger.info("Элемент с ID " + list.get(i).getId() + " удален");
        }
        logger.info("Выполнена команда remove_lower");
        
        
    }

    @Override
    public String toString() {
        return "remove_lower - удаляет из коллекции все элементы, меньшие, чем заданный";
    }
}

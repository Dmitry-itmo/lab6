package laba.commands;

import java.io.Serializable;

import org.slf4j.*;

import laba.data.*;
import laba.serverUtility.ConnectManager;
import laba.utility.CollectionManager;
/**
 * Adds a new item to the collection
 */
public class AddCommand implements Command,Serializable{

    private static final Logger logger = LoggerFactory.getLogger(ConnectManager.class);

    private static final long serialVersionUID = 1L;

    private SpaceMarine spaceMarine;

    public SpaceMarine getSpaceMarine() {
        return spaceMarine;
    }

    @Override
    public void execute(){
        CollectionManager.addSpaceMarine(getSpaceMarine());
        logger.info("Добавлен элемент в коллекцию");
    }

    @Override
    public String toString() {
        return "add - добавляет новый элемент в коллекцию";
    }
}

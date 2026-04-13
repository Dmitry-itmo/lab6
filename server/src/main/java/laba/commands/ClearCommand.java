package laba.commands;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import laba.utility.CollectionManager;
/**
 * Clears the collection
 */
public class ClearCommand implements Command, Serializable{
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = LoggerFactory.getLogger(ClearCommand.class);

    @Override
    public void execute() {
        logger.info("Очистка коллекции");
        CollectionManager.removeCollection();
    }
    
    @Override
    public String toString() {
        return "clear - очищает коллекцию";
    }
}

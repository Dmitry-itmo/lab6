package laba.commands;

import laba.data.*;
import laba.utility.CollectionManager;
/**
 * Adds a new item to the collection
 */
public class AddCommand implements Command{


    @Override
    public void execute(){
        SpaceMarine spaceMarine = CollectionManager.createElementSpaceMarine();
        CollectionManager.addSpaceMarine(spaceMarine);
        System.out.println("Элемент добавлен в коллекцию");
    }

    @Override
    public String toString() {
        return "add - добавляет новый элемент в коллекцию";
    }
}

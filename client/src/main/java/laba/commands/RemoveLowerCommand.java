package laba.commands;

import java.util.ArrayList;
import java.util.Collections;

import laba.data.SpaceMarine;
import laba.utility.CollectionManager;
/**
 * The command deletes all items from the collection that are less than the specified value.
 */
public class RemoveLowerCommand implements Command{
    @Override
    public void execute() {
        ArrayList<SpaceMarine> list = new ArrayList<>(CollectionManager.getCollection());
    
        SpaceMarine spaceMarine = CollectionManager.createElementSpaceMarine();
        list.add(spaceMarine);
        Collections.sort(list);
        Collections.reverse(list);
        int index = list.indexOf(spaceMarine);
        for (int i = index+1; i < list.size(); i++) {
            CollectionManager.removeElement(list.get(i));
            SpaceMarine.removeID(list.get(i).getId());
            System.out.println("Элемент с ID " + list.get(i).getId() + " удален");
        }
        
        
    }

    @Override
    public String toString() {
        return "remove_lower - удаляет из коллекции все элементы, меньшие, чем заданный";
    }
}

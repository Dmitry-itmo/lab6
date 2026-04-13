package laba.commands;

import java.util.ArrayList;
import java.util.Collections;

import laba.data.SpaceMarine;
import laba.utility.CollectionManager;
/**
 * The command deletes all items from the collection that exceed the specified value.
 */
public class RemoveGreaterCommand implements Command{
    

    @Override
    public void execute() {
        ArrayList<SpaceMarine> list = new ArrayList<>(CollectionManager.getCollection());
    
        SpaceMarine spaceMarine = CollectionManager.createElementSpaceMarine();
        list.add(spaceMarine);

        Collections.sort(list);
        int index = list.indexOf(spaceMarine);
        for (int i = index+1; i < list.size(); i++) {
            CollectionManager.removeElement(list.get(i));
            SpaceMarine.removeID(list.get(i).getId());
            System.out.println("Элемент с ID " + list.get(i).getId() + " удален");
        }
        
        
    }

    @Override
    public String toString() {
        return "remove_greater - удаляет из коллекции все элементы, превышающие заданный";
    }
}

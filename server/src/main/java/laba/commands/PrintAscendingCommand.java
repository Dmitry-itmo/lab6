package laba.commands;

import java.util.ArrayList;
import java.util.Collections;

import laba.data.SpaceMarine;
import laba.utility.CollectionManager;
/**
 * A command that outputs the elements of a collection in ascending order
 */
public class PrintAscendingCommand implements Command{
    @Override
    public void execute() {
        if (CollectionManager.getCollection().size() == 0) {
            System.out.println("В коллекции нет элементов");
            return;
        }
        ArrayList<SpaceMarine> list = new ArrayList<>(CollectionManager.getCollection());
        Collections.sort(list);


        for (SpaceMarine spaceMarine : list) {
            System.out.println();
            System.out.println(spaceMarine);
        }
        
        System.out.println();
    }

    @Override
    public String toString() {
        return "print_ascending - выводит элементы коллекции в порядке возрастания";
    }
}

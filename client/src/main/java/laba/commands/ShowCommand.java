package laba.commands;

import java.util.ArrayList;

import laba.data.SpaceMarine;
import laba.sorter.SortBySpaceMarineID;
import laba.utility.CollectionManager;
/**
 * The command shows information about each item in the collection
 */
public class ShowCommand implements Command{
    @Override
    public void execute() {
        if (CollectionManager.getCollection().size() == 0) {
            System.out.println("В коллекции нет элементов");
            return;
        }

        ArrayList<SpaceMarine> list = new ArrayList<>(CollectionManager.getCollection());
        list.sort(new SortBySpaceMarineID());

        for (SpaceMarine spaceMarine : list) {
            System.out.println();
            System.out.println(spaceMarine);
        }
        System.out.println();
    }
    
    @Override
    public String toString() {
        return "show - выводит все элементы коллекции в строковом представлении";
    }

}

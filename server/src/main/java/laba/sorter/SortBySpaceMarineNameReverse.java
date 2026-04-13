package laba.sorter;

import java.util.Comparator;

import laba.data.SpaceMarine;
/**
 * Sorting objects of the SpaceMarine class by their names, but in reverse alphabetical order
 */
public class SortBySpaceMarineNameReverse implements Comparator<SpaceMarine>{

    @Override
    public int compare(SpaceMarine spaceMarine1, SpaceMarine spaceMarine2) {
        return -1 * spaceMarine1.getName().compareTo(spaceMarine2.getName());
    }
    
}

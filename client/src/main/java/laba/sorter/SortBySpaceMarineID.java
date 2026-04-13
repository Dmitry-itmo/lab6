package laba.sorter;

import java.util.Comparator;

import laba.data.SpaceMarine;
/** 
 * Sorting the SpaceMarine class by their ID
 */
public class SortBySpaceMarineID implements Comparator<SpaceMarine>{
    @Override
    public int compare(SpaceMarine spaceMarine1, SpaceMarine spaceMarine2) {
        return spaceMarine1.getId() - spaceMarine2.getId();
    }
}

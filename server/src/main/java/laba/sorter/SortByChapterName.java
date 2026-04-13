package laba.sorter;

import java.util.Comparator;
import laba.data.Chapter;
/**
 * Sorting objects of the Chapter class by their name field
 */
public class SortByChapterName implements Comparator<Chapter>{
    public int compare(Chapter chapter1, Chapter chapter2) {
        return chapter1.getName().compareTo(chapter2.getName()); 
    }
    
}

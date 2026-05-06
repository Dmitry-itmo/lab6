package laba.commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

import org.slf4j.*;

import java.util.Collections;

import laba.data.Chapter;
import laba.data.SpaceMarine;
import laba.serverUtility.ConnectManager;
import laba.utility.CollectionManager;
/**
 * A command that outputs the values of the Chapter class field of all collection elements in ascending order
 */
public class PrintFieldAscendingChapterCommand implements Command,Serializable{
    private static final Logger logger = LoggerFactory.getLogger(ConnectManager.class);
    @Override
    public void execute() {
        if (CollectionManager.getCollection().size() == 0) {
            logger.info("В коллекции нет элементов");
            return;
        }
        HashSet<SpaceMarine> hashSet = CollectionManager.getCollection();
        ArrayList<Chapter> list = new ArrayList<>();
        for (SpaceMarine spaceMarine : hashSet) {
            list.add(spaceMarine.getChapter());
        }
        Collections.sort(list);

        for (Chapter chapter : list) {
            logger.info(chapter.toString());
        }
        
        logger.info("Выполнена команда print_field_ascending_chapter");
    }

    @Override
    public String toString() {
        return "print_field_ascending_chapter - выводит значения поля chapter всех элементов в порядке возрастания";
    }
}

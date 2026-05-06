package laba.commands;

import org.slf4j.*;

import laba.serverUtility.ConnectManager;
import laba.utility.FileManager;

/**
 * The command saves the collection to an xml file.
 */
public class SaveCommand implements Command{
    @Override
    public void execute() {
        FileManager.save();
    }

    @Override
    public String toString() {
        return "save - сохраняет коллекцию в файл";
    }
}

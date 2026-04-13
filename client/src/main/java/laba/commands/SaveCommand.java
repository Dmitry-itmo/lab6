package laba.commands;

import laba.utility.FileManager;

/**
 * The command saves the collection to an xml file.
 */
public class SaveCommand implements Command{
    @Override
    public void execute() {
        FileManager.save();
        System.out.println("Коллекция сохранена");
    }

    @Override
    public String toString() {
        return "save - сохраняет коллекцию в файл";
    }
}

package laba.commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import laba.data.SpaceMarine;
import laba.utility.CollectionManager;
import laba.utility.ServerManager;
/**
 * The command deletes all items from the collection that are less than the specified value.
 */
public class RemoveLowerCommand implements Command,Serializable{
    private static final long serialVersionUID = 1L;


    private SpaceMarine spaceMarine;

    public SpaceMarine getSpaceMarine() {
        return spaceMarine;
    }
    @Override
    public void execute() {
        if (!ServerManager.checkServer()) {
            System.out.println("Вы не можете удалять элементы в коллекции без подключения к серверу");
            return;
        }

        spaceMarine = CollectionManager.createElementSpaceMarine();
        ServerManager.sendObject(this);
    }

    @Override
    public String toString() {
        return "remove_lower - удаляет из коллекции все элементы, меньшие, чем заданный";
    }
}

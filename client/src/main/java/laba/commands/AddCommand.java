package laba.commands;

import java.io.Serializable;

import laba.data.*;
import laba.utility.CollectionManager;
import laba.utility.ServerManager;
/**
 * Adds a new item to the collection
 */
public class AddCommand implements Command, Serializable{
    private static final long serialVersionUID = 1L;

    private SpaceMarine spaceMarine;

    public SpaceMarine getSpaceMarine() {
        return spaceMarine;
    }


    @Override
    public void execute(){
        if (!ServerManager.checkServer()) {
            System.out.println("Вы не можете добавлять элемент в коллекцию без подключения к серверу");
            return;
        }

        spaceMarine = CollectionManager.createElementSpaceMarine();
        

        ServerManager.sendObject(this);
        
        System.out.println("Элемент добавлен в коллекцию");

    }

    @Override
    public String toString() {
        return "add - добавляет новый элемент в коллекцию";
    }
}

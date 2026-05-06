package laba.commands;

import laba.utility.CollectionManager;
import laba.utility.ServerManager;

import java.io.Serializable;

import laba.data.*;
import laba.exceptions.*;
/**
 * The command deletes a collection item by its ID
 */
public class RemoveCommand implements Command, Serializable{
    private static final long serialVersionUID = 1L;

    private SpaceMarine spaceMarine;

    public SpaceMarine getSpaceMarine() {
        return spaceMarine;
    }

    @Override
    public void execute() {
        System.out.println("Нужен ID");
    }

    public void execute(String id) throws IncorrectCommandException, IncorrectIDException{
        if (!ServerManager.checkServer()) {
            System.out.println("Вы не можете удалить элемент в коллекции без подключения к серверу");
            return;
        }

        spaceMarine = CollectionManager.searchSpaceMarine(Integer.parseInt(id));
        
        ServerManager.sendObject(this);
        System.out.println("Элемент " + spaceMarine.getId() + " удален");
    } 

    @Override
    public String toString() {
        return "remove - удаляет элемент из коллекции по его ID";
    }
    
}

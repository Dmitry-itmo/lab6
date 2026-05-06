package laba.commands;

import laba.data.*;
import laba.exceptions.IncorrectCommandException;
import laba.exceptions.IncorrectIDException;
import laba.utility.*;
import static laba.utility.ConsoleManager.*;

import java.io.Serializable;

/**
 * The command updates information about a collection item whose id is equal to the specified one.
 */
public class UpdateCommand implements Command,Serializable{
    private static final long serialVersionUID = 1L;
    
    private SpaceMarine spaceMarine;

    public SpaceMarine getSpaceMarine() {
        return spaceMarine;
    }

    @Override
    public void execute() {
        System.out.println("Нужен ID");
    }

    @Override
    public void execute(String ID) throws IncorrectCommandException,IncorrectIDException {
        if (!ServerManager.checkServer()) {
            System.out.println("Вы не можете обновить элемент в коллекции без подключения к серверу");
            return;
        }

        try {
            CollectionManager.searchSpaceMarine(Integer.parseInt(ID));
        } catch (Exception e) {
            System.out.println("Не существует такого ID");
            return;
        }
        
        spaceMarine = CollectionManager.createElementSpaceMarine();
        CollectionManager.removeElement(spaceMarine);
        spaceMarine.setId(Integer.parseInt(ID));

        ServerManager.sendObject(this);
        System.out.println("Элемент изменён");
    }

    @Override
    public String toString() {
        return "update - обновить значения элемента коллекции, id которого равен заданному";
    }
}

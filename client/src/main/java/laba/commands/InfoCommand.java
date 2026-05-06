package laba.commands;

import java.io.Serializable;

import laba.utility.CollectionManager;
/**
 * The command that shows information about the collection
 */
public class InfoCommand implements Command,Serializable{
    private static final long serialVersionUID = 1L;
    @Override
    public void execute() {
        System.out.println("Длина коллеции: " + CollectionManager.getCollection().size());
        System.out.println("Тип данных: " + CollectionManager.getCollection().getClass());
        System.out.println("Дата инициализации: " + CollectionManager.getCreationDate());
    }
    
    @Override
    public String toString() {
        return "info - выводит информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}


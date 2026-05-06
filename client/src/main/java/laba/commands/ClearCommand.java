package laba.commands;

import java.io.Serializable;
import java.nio.*;
import java.nio.charset.StandardCharsets;
import java.net.*;

import laba.utility.ServerManager;
import laba.utility.ToByteObject;
import laba.Client;
/**
 * Clears the collection
 */
public class ClearCommand implements Command, Serializable{
    private static final long serialVersionUID = 1L;
    
    @Override
    public void execute() {

        if (!ServerManager.checkServer()) {
            System.out.println("Вы не можете очистить коллекцию без подключения к серверу");
            return;
        }
        
        ServerManager.sendObject(new ClearCommand());

        System.out.println("Коллекция очищена");
        
    }
    
    @Override
    public String toString() {
        return "clear - очищает коллекцию";
    }
}

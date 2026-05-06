package laba.commands;

import java.io.Serializable;

import laba.utility.ConsoleManager;
import laba.utility.ServerManager;

public class ExitCommand implements Command,Serializable{

    private static final long serialVersionUID = 1L;

    @Override
    public void execute() {
        if (!ServerManager.checkServer()) {
            System.out.println("При выходите из программы все изменения коллекции не сохранятся");
            System.out.println("(Введите Y чтобы выйти)");
            String y = ConsoleManager.readLine().toUpperCase();
            if (y.equals("Y") || y.equals("YES")) {
                System.exit(0);
            }
            return;
            
        }

        ServerManager.sendObject(new ExitCommand());
        System.exit(0);




        
    }
    
    @Override
    public String toString() {
        return "exit - завершает программу (без сохранения файла)";
    }
}

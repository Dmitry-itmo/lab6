package laba.commands;

import java.util.Map;

import laba.utility.CommandManager;
/**
 * A command that outputs a description of each command
 */
public class HelpCommand implements Command{
    
    public void execute() {
        for (Map.Entry<String, Command> entry : CommandManager.getCommands().entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    @Override
    public String toString() {
        return "help - выводит справку по доступным командам";
    }

}

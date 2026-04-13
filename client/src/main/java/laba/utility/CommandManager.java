package laba.utility;

import java.util.HashMap;

import laba.commands.*;
import laba.exceptions.*;
/**
 * The class defines commands and monitors their correctness
 */
public class CommandManager {

    private static HashMap<String,Command> commands = new HashMap<>();

    

    static {
        commands.put("help", new HelpCommand());
        commands.put("exit", new ExitCommand());
        commands.put("history", new HistoryCommand());
        commands.put("add", new AddCommand());
        commands.put("info", new InfoCommand());
        commands.put("show", new ShowCommand());
        commands.put("clear", new ClearCommand());
        commands.put("remove", new RemoveCommand());
        commands.put("update", new UpdateCommand());
        commands.put("print_ascending", new PrintAscendingCommand());
        commands.put("print_descending", new PrintDescendingCommand());
        commands.put("print_field_ascending_chapter", new PrintFieldAscendingChapterCommand());
        commands.put("remove_greater", new RemoveGreaterCommand());
        commands.put("remove_lower", new RemoveLowerCommand());
        commands.put("save", new SaveCommand());
        commands.put("execute_script", new ExecuteScriptCommand());
    }

   

    public static HashMap<String, Command> getCommands() {
        return commands;
    }

    /**
     * Метод использования комманды
     * @param userLine
     * @throws IncorrectCommandException
     */
    public static void useCommand(String userLine) throws IncorrectCommandException{
        String[] wordsInLine = userLine.split(" ");
        String firstWord = wordsInLine[0];
        try {
            if (wordsInLine.length == 1) {
                commands.get(firstWord).execute();
            } else {
                commands.get(firstWord).execute(wordsInLine[1]);
            } 
            HistoryCommand.addCommand(firstWord);
        }catch (IncorrectIDException e) {
            System.out.println("Неправильный ID");
        } catch (Exception e) {
            throw new IncorrectCommandException();

        }
    }

    public static Command getCommand(String userLine) throws IncorrectCommandException{
        String[] wordsInLine = userLine.split(" ");
        String firstWord = wordsInLine[0];
        try {
            return commands.get(firstWord);   
        }  catch (Exception e) {
            throw new IncorrectCommandException();
        }

    }


}

package laba.commands;

public class ExitCommand implements Command{

    @Override
    public void execute() {
        System.exit(0);
    }
    
    @Override
    public String toString() {
        return "exit - завершает программу (без сохранения файла)";
    }
}

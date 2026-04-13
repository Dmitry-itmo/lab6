package laba.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import laba.data.Chapter;
import laba.data.Coordinates;
import laba.data.SpaceMarine;
import laba.exceptions.IncorrectCommandException;
import laba.exceptions.IncorrectIDException;
import laba.utility.CollectionManager;
import laba.utility.CommandManager;
/**
 * Executes commands from the file
 */
public class ExecuteScriptCommand implements Command{
    
    private ArrayList<String> elementSpaceMarine = new ArrayList<>();
    private boolean commandADD = false;
    static int count;

    @Override
    public void execute() {
        System.out.println("Нужен имя файла");
    }

    @Override
    public void execute(String path) throws IncorrectCommandException, IncorrectIDException {
        BufferedReader reader = null;
        String script = "";
        try {
            reader = new BufferedReader(
                        new InputStreamReader(
                            new FileInputStream("script"+ File.separator+path), "UTF-8"));
            String line;
            StringBuilder result = new StringBuilder();
            
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }

            script = result.toString();
            
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            elementSpaceMarine = new ArrayList<String>();
            commandADD = false;
            try {
                reader.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
        String[] commands = script.split("\n");
        for (String command : commands){
            if (CommandManager.getCommand(command) instanceof AddCommand) {
                commandADD = true;
            }
            
            if (commandADD) {
                elementSpaceMarine.add(command);
                if (elementSpaceMarine.size() == 10) {
                    commandADD = false;
                    setParametr(elementSpaceMarine);   
                }
            } else { 
                if (CommandManager.getCommand(command) instanceof ExecuteScriptCommand) {
                    if (count <= 10){
                        count++;
                        CommandManager.useCommand(command);
                        count--;
                    } else {
                        System.out.println("Глубина не может быть больше 10");
                        count = 0;
                        throw new IncorrectCommandException();
                    }
                } 
                if (!(CommandManager.getCommand(command) instanceof ExecuteScriptCommand))
                CommandManager.useCommand(command);
                
            }
        }
    }

    void setParametr(ArrayList<String> arrayList) throws IncorrectCommandException{
        SpaceMarine spaceMarine = new SpaceMarine();
        // 1
        spaceMarine.setName(arrayList.get(1));
        Chapter chapter = new Chapter();
        Coordinates coordinates = new Coordinates(0, 0);
        // 2
        try {
            coordinates.setX(Long.parseLong(arrayList.get(2)));
        } catch (NumberFormatException e) {
            System.err.println("Ошибка в файлу, X должен быть целое число!");
            throw new IncorrectCommandException();
        }
        // 3
        try {
            coordinates.setY(Float.parseFloat(arrayList.get(2)));
        } catch (NumberFormatException | IncorrectCommandException e) {
            System.err.println("Неправильно введенная координата Y!");
            throw new IncorrectCommandException();
        }
        // 4 
        try {
            spaceMarine.setHealth(Long.parseLong(arrayList.get(4)));
        } catch (NumberFormatException e) {
            System.err.println("Введите число!");
        } catch (IncorrectCommandException e) {
            System.err.println("Число должно быть больше 0");
        }

        
        
        // 5
        try {
            spaceMarine.setCategory(arrayList.get(5));
            
            // 6
            spaceMarine.setMeleeWeapon(arrayList.get(6));
            
            // 7
            spaceMarine.setWeaponType(arrayList.get(7));
        } catch (IncorrectCommandException e) {
            System.out.println("Неправильно введенные типы");
            throw new IncorrectCommandException();
        }
        
        spaceMarine.setChapter(chapter);
        
        
        
        spaceMarine.setCoordinates(coordinates);
        
        //8
        chapter.setName(arrayList.get(8));
        
        // 9 
        chapter.setWorld(arrayList.get(9)); 
        
        spaceMarine.setChapter(chapter);
          
        
        CollectionManager.addSpaceMarine(spaceMarine);

        System.out.println("Элемент добавлен в коллекцию");

        elementSpaceMarine = new ArrayList<String>();

    }

    @Override
    public String toString() {
        return "execute_script - считает и исполняет скрипт из указанного файла";
    }
}

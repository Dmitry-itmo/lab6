package laba.commands;

import laba.data.*;
import laba.exceptions.IncorrectCommandException;
import laba.exceptions.IncorrectIDException;
import laba.utility.*;
import static laba.utility.ConsoleManager.*;

/**
 * The command updates information about a collection item whose id is equal to the specified one.
 */
public class UpdateCommand implements Command{
    @Override
    public void execute() {
        System.out.println("Нужен ID");
    }

    @Override
    public void execute(String ID) throws IncorrectCommandException,IncorrectIDException {
        SpaceMarine spaceMarine = CollectionManager.searchSpaceMarine(Integer.parseInt(ID));
        System.out.println("Введите имя коробля: ");
        spaceMarine.setName(readLine());
        Coordinates coordinates = new Coordinates(0,0);
        Chapter chapter = new Chapter();
        
        while (true) {
            System.out.println("Введите координату X: ");
            try {
                coordinates.setX(Long.parseLong(readLine()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введите целое число!");
            }
        }
        while (true) {
            System.out.println("Введите координату Y (Y > -192): ");
            try {
                coordinates.setY(Float.parseFloat(readLine()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введите число!");
            } catch (IncorrectCommandException e) {
                System.out.println("Число должно быть больше -192");
            }
        }
        while(true) {
            System.out.println("Введите здоровье (число больше 0)");
            try {
                spaceMarine.setHealth(Long.parseLong(readLine()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введите число!");
            } catch (IncorrectCommandException e) {
                System.out.println("Число должно быть больше 0");
            }
        }

        while(true) {
            try {
                System.out.println("Введите одну из категорий Inceptor, Supressor, Terminator, Chaplain, Apothecary");
                spaceMarine.setCategory(readLine());
                break;
            } catch(IncorrectCommandException e) {
                System.out.println("Неправильно введенный тип");
            }
        }

        while(true) {
            try {
                System.out.println("Введите одну из категорий Chain_sword, Chain_axe, Manreaper, Power_Fist");
                spaceMarine.setMeleeWeapon(readLine());
                break;
            } catch(IncorrectCommandException e) {
                System.out.println("Неправильно введенный тип");
            }
        }

        while(true) {
            try {
                System.out.println("Введите одну из категорий Combi_flamer, Combi_plasma_gun, Flamer");
                spaceMarine.setWeaponType(readLine());
                break;
            } catch(IncorrectCommandException e) {
                System.out.println("Неправильно введенный тип");
            }
        }

        spaceMarine.setCoordinates(coordinates);
        spaceMarine.setChapter(chapter);
        System.out.println("Введите name Chapter: ");
        chapter.setName(readLine());

        System.out.println("Введите world Chapter: ");
        chapter.setWorld(readLine());

        System.out.println("Элемент изменён");
    }

    @Override
    public String toString() {
        return "update - обновить значения элемента коллекции, id которого равен заданному";
    }
}

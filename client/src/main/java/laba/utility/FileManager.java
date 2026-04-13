package laba.utility;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;

import laba.data.SpaceMarine;
import laba.exceptions.IncorrectIDException;
/**
 * This class is responsible for saving and uploading data from xml files to a collection
 */
public class FileManager {

    private static XStream xStream;
    private static String path = System.getenv("CONFIG_PATH");;

    static {
        xStream = new XStream();
        xStream.allowTypesByWildcard(new String[] {"laba.data.**"});
        
        xStream.alias("spaceMarine", SpaceMarine.class);
        xStream.alias("spaceMarines", HashSet.class);
    }
    
    public static void save() {
            String xml = xStream.toXML(CollectionManager.getCollection());
            
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(path);
                fileWriter.write(xml);
            } catch(IOException e) {
                System.err.println(e);
            } finally {
                try {
                    fileWriter.close();
                } catch(IOException e) {
                    System.err.println(e);
                }
            }
        
        
    }

    public static void load() {
        BufferedReader reader = null;
        String xmlString = null;
        try {
            reader = new BufferedReader(
                        new InputStreamReader(
                            new FileInputStream(path), "UTF-8"));
            String line;
            StringBuilder result = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }

            xmlString = result.toString();
            
            
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }

        if (!xmlString.equals("")) {
            
            try {
                @SuppressWarnings("unchecked")
                HashSet<SpaceMarine> xmlSet = (HashSet<SpaceMarine>) xStream.fromXML(xmlString);
                
                for (SpaceMarine spaceMarine : xmlSet) {
                    if (!spaceMarine.validate()) {
                        System.err.println("Неправильно введенные данные в файлe");
                        System.err.println(spaceMarine);
                        return;
                    }
                    try {
                        SpaceMarine.addID(spaceMarine.getId());
                    } catch (IncorrectIDException e) {
                        System.err.println("Неправильный ID");
                        System.err.println(spaceMarine);
                        return;
                    } 
                    CollectionManager.addSpaceMarine(spaceMarine);
                }
            } catch(XStreamException e) {
                System.err.println(e);
                System.err.println("Проблема с файлом");
            } catch (NullPointerException e) {
                System.err.println(e);
            } catch (Exception e) {
                System.err.println(e);
                System.err.println("Ошибка");
            }   
        }
    }
}

package laba.utility;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;

import laba.data.SpaceMarine;
import laba.exceptions.IncorrectIDException;
import laba.serverUtility.ConnectManager;
/**
 * This class is responsible for saving and uploading data from xml files to a collection
 */
public class FileManager {
    private static final Logger logger = LoggerFactory.getLogger(FileManager.class);
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
                logger.info("Сохранение коллекции");
            } catch(IOException e) {
                logger.error("Ошибка в загрузке файла" + e.getMessage());
            } catch(Exception e) {
                logger.error("Ошибка в загрузке файла" + e.getMessage());
            } 
            finally {
                try {
                    fileWriter.close();
                } catch(IOException e) {
                    logger.error("Ошибка в загрузке файла" + e.getMessage());
                }
            }
        
        
    }

    public static void load() throws Exception{
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

            
            
        }
        catch (Exception e) {
            logger.error("Ошибка в загрузки файла");
        } 
        
        finally {
            try {
                reader.close();
            } catch (Exception e) {
                logger.error("Ошибка закрытия файла");
            }
        }

        if (!xmlString.equals("")) {
            
            try {
                @SuppressWarnings("unchecked")
                HashSet<SpaceMarine> xmlSet = (HashSet<SpaceMarine>) xStream.fromXML(xmlString);
                
                for (SpaceMarine spaceMarine : xmlSet) {
                    if (!spaceMarine.validate()) {
                        
                        logger.error("Неправильно введеный файл");
                        return;
                    }
                    try {
                        SpaceMarine.addID(spaceMarine.getId());
                    } catch (IncorrectIDException e) {
                        logger.error("Неправильный ID");
                        return;
                    } 
                    CollectionManager.addSpaceMarine(spaceMarine);
                }
            } catch(XStreamException e) {
                logger.error("Проблема с файлом");
            } catch (NullPointerException e) {
                logger.error("" + e);
            } catch (Exception e) {
                logger.error("Ошибка");
            }   
        }
    }
}

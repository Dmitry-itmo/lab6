package laba.serverUtility;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

import org.slf4j.*;


import laba.exceptions.IncorrectCommandException;
import laba.utility.*;
import laba.commands.*;
import laba.data.SpaceMarine;

import java.util.*;
import java.util.stream.Collectors;


public class ConnectManager {
    private static final Logger logger = LoggerFactory.getLogger(ConnectManager.class);

    public static void connecting() {
        int port = 6767;
        
        try (DatagramSocket serverSocket = new DatagramSocket(port)) {
            logger.info("Сервер запущен на порту {}", port);

            serverSocket.setSoTimeout(50);
            
            byte[] receiveData = new byte[1024];

            while (true) {

                if (ConsoleManager.getReader().ready()) {
                    String userLine = ConsoleManager.readLine();

                    try {  
                        CommandManager.useCommand(userLine); 
                    } catch (IncorrectCommandException e) {
                        System.err.println(e.getMessage());
                    }
                    
                }

                try {
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    serverSocket.receive(receivePacket);

                    receiveData = receivePacket.getData();

                    String receiveString = new String(
                        receivePacket.getData(), 
                        receivePacket.getOffset(), 
                        receivePacket.getLength(), 
                        StandardCharsets.UTF_8
                    ).trim(); 

                    if (receiveString.equals("PING")) {
                        byte[] sendData = "PONG".getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                        serverSocket.send(sendPacket);
                    } else {
                        Object object = ReadManager.bytesToObject(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength());
                        if (object instanceof LoadCommand) {
                            Set<SpaceMarine> sendSet = CollectionManager.getCollection().stream().sorted((a,b) -> a.getName().compareTo(b.getName())).collect(Collectors.toSet());
                            SendManager.sendCollection(sendSet, receivePacket, serverSocket);

                        } 
                        if (object instanceof Command) {
                            ReadManager.readCommand((Command) object);
                        }
                        if (object instanceof Set){
                            logger.info("Заргузка коллекции");
                            HashSet<SpaceMarine> hashSet = (HashSet<SpaceMarine>) object;
                            CollectionManager.setCollection(hashSet);
                        }

                    }
                } catch (SocketTimeoutException e) {
                    
                } catch (IOException e) {
                    logger.error("Ошибка ввода-вывода" + e);
                } catch (Exception e) {
                    logger.error("Неизвестная ошибка " + e);
                }

            }

        } catch (Exception e) {
            logger.error("Остановка сервера" + e);
        }
        
    }    
}

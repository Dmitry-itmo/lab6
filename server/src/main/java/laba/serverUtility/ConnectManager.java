package laba.serverUtility;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import laba.exceptions.IncorrectCommandException;
import laba.utility.CommandManager;
import laba.utility.ConsoleManager;
import laba.commands.*;


public class ConnectManager {
    private static final Logger logger = LoggerFactory.getLogger(ConnectManager.class);

    public static void connecting() {
        int port = 67;
        
        try (DatagramSocket serverSocket = new DatagramSocket(port)) {
            System.out.println("Сервер запущен на порту " + port);
            logger.info("Сервер запущен на порту {}", port);

            serverSocket.setSoTimeout(50);
            
            byte[] receiveData = new byte[1024];

            System.out.print(">>> ");

            while (true) {

                if (ConsoleManager.getReader().ready()) {
                    String userLine = ConsoleManager.readLine();

                    try {  
                        CommandManager.useCommand(userLine); 
                    } catch (IncorrectCommandException e) {
                        System.err.println(e.getMessage());
                    }
                    
                    System.out.print(">>> ");
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
                        Command command = (Command) ReadManager.bytesToObject(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength());
                        command.execute();
                    }
                } catch (SocketTimeoutException e) {
                    
                } catch (IOException e) {
                    logger.error("Ошибка ввода-вывода");
                }
            }

        } catch (Exception e) {
            logger.error("Остановка сервера");
        }
        
    }    
}

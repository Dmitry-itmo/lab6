package laba;

import java.net.*;
import java.nio.channels.DatagramChannel;

import laba.exceptions.*;
import laba.utility.*;
/**
 * @author Romanov Dmitry
 */
public class Client {

    public static DatagramChannel channel;
    public static String host = "localhost";
    public static int port = 67;

    private static InetSocketAddress inetSocketAddress = new InetSocketAddress(host,port);


    public static InetSocketAddress gInetSocketAddress() {
        return inetSocketAddress;
    }

    public static void main(String[] args){
        FileManager.load();
        
        try (DatagramChannel clientSocket = DatagramChannel.open()) {
            channel = clientSocket;
            Client.channel.configureBlocking(false);
            System.out.println("Введите сообщение");
            while (true) {

                String userLine = ConsoleManager.readLine();

                try {  
                    CommandManager.useCommand(userLine); 
                } catch (IncorrectCommandException e) {
                    System.err.println(e.getMessage());
                } 

            }

        } catch (Exception e) {
            System.out.println(e);
        }


        
        // FileManager.load();
        // while (true) {
        //     String userLine = ConsoleManager.readLine();
        //     try {  
        //         CommandManager.useCommand(userLine); 
        //     } catch (IncorrectCommandException e) {
        //         System.err.println(e.getMessage());
        //     }  
        // }
    }
} 
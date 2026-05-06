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
    public static int port = 6767;

    private static InetSocketAddress inetSocketAddress = new InetSocketAddress(host,port);


    public static InetSocketAddress gInetSocketAddress() {
        return inetSocketAddress;
    }

    public static void main(String[] args){
        System.out.println("\r\n" + //
                        "██╗░░░░░░█████╗░██████╗░░░░░░░░█████╗░\r\n" + //
                        "██║░░░░░██╔══██╗██╔══██╗░░░░░░██╔═══╝░\r\n" + //
                        "██║░░░░░███████║██████╦╝█████╗██████╗░\r\n" + //
                        "██║░░░░░██╔══██║██╔══██╗╚════╝██╔══██╗\r\n" + //
                        "███████╗██║░░██║██████╦╝░░░░░░╚█████╔╝\r\n" + //
                        "╚══════╝╚═╝░░╚═╝╚═════╝░░░░░░░░╚════╝░");
        
        try (DatagramChannel clientSocket = DatagramChannel.open()) {
            channel = clientSocket;
            Client.channel.configureBlocking(false);
            while (true) {
                if (ServerManager.checkServer()) {
                    break;
                }
                System.out.println("Нажмите на Enter, чтобы повторить попытку");
                ConsoleManager.readLine();
            }
            
            
            System.out.println("===== Успешное подключение =====");
            System.out.println("Введите сообщение");
               
            while (true) {

                
                String userLine = ConsoleManager.readLine();
                

                try {
                    if (ServerManager.checkServer()) {
                        ServerManager.loadCollection();
                    } else {
                        System.out.println("Вы работаете с локальной коллекцией");
                    }  
                    CommandManager.useCommand(userLine); 
                } catch (IncorrectCommandException e) {
                    System.err.println(e.getMessage());
                } 
                

            }

        } catch (Exception e) {
            System.out.println("Ошибка");
        }


        
     
    }
} 
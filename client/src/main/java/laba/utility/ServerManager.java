package laba.utility;

import java.io.IOException;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;



import laba.Client;
import laba.commands.ClearCommand;
import laba.commands.LoadCommand;
import laba.data.SpaceMarine;

public class ServerManager {

    public static boolean addSet = false;
    public static Set<SpaceMarine> bufferSet = new HashSet<>();

    public static boolean checkServer() {
        byte[] sendData = "PING".getBytes();
        ByteBuffer sendByte = ByteBuffer.wrap(sendData);
        ByteBuffer buffer = ByteBuffer.allocate(65535);
        
        try {

            Client.channel.send(sendByte, Client.gInetSocketAddress());
            SocketAddress address = null;

            long timeout = 1000; 
            long startTime = System.currentTimeMillis();

            while (System.currentTimeMillis() - startTime < timeout) {
                
                address = Client.channel.receive(buffer);
                if (address != null) {
                    break; 
                }
                Thread.sleep(10); 
            }


            if (address != null) {
                buffer.flip();
                
                
                byte[] data = new byte[buffer.remaining()];
                buffer.get(data);
                
                String pong = new String(data, StandardCharsets.UTF_8);

                if (!pong.equals("PONG")) {
                    System.out.println("Неверный ответ от сервера");
                    throw new Exception();
                }
            } else {
                throw new SocketTimeoutException();
            }
            
            return true;
            
        } catch (SocketTimeoutException e) {
            System.out.println("Сервер не отвечает");
            return false;
        } catch(Exception e) {
            System.err.println(e);
            return false;
        }
    }


    public static void sendCollection() {
        Set<SpaceMarine> sendSet = CollectionManager.getCollection().stream().sorted((a,b) -> a.getName().compareTo(b.getName())).collect(Collectors.toSet());

        byte[] sendData = ToByteObject.objectToBytes(sendSet);
        ByteBuffer sendByte = ByteBuffer.wrap(sendData);
        try {
            Client.channel.send(sendByte, Client.gInetSocketAddress()); 
        } catch (Exception e) {
            System.err.println("Server" + e);
        }
    }

    public static void sendObject(Object obj) {
        byte[] sendData = ToByteObject.objectToBytes(obj);
        ByteBuffer sendByte = ByteBuffer.wrap(sendData);
        try {
            Client.channel.send(sendByte, Client.gInetSocketAddress()); 
              
                 
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void loadCollection() {
        HashSet<SpaceMarine> tempBuffer = new HashSet<>();
        boolean isLoading = true;
        try {
            ServerManager.sendObject(new LoadCommand());
            
            while (isLoading) {
                ByteBuffer buffer = ByteBuffer.allocate(65535);
                SocketAddress address = Client.channel.receive(buffer);

                if (address == null) continue; 

                buffer.flip();
                byte[] data = new byte[buffer.remaining()];
                buffer.get(data);
                
                Object object = ReadManager.bytesToObject(data, 0, data.length);

                if (object instanceof String) {
                    String str = (String) object;
                    
                    if (str.equals("PONG")) {
                        continue; 
                    }
                    
                    if (str.equals("BEGIN")) {
                        tempBuffer.clear();
                        continue;
                    }
                    
                    if (str.equals("END")) {
                        CollectionManager.setCollection(tempBuffer);
                        isLoading = false; 
                    }
                } 
                else if (object instanceof Set) {
                    tempBuffer.addAll((Set<SpaceMarine>) object);
                }

            }
        } catch (Exception e) {
            System.err.println("Ошибка при загрузке");
        }
            
    }

}

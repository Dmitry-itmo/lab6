package laba.commands;

import java.io.Serializable;
import java.nio.*;
import java.nio.charset.StandardCharsets;
import java.net.*;

import laba.utility.ToByteObject;
import laba.Client;
/**
 * Clears the collection
 */
public class ClearCommand implements Command, Serializable{
    private static final long serialVersionUID = 1L;
    
    @Override
    public void execute() {
        byte[] sendData = "PING".getBytes();
        ByteBuffer sendByte = ByteBuffer.wrap(sendData);
        ByteBuffer buffer = ByteBuffer.allocate(65535);
        
        try {

            Client.channel.send(sendByte, Client.gInetSocketAddress());
            SocketAddress address = Client.channel.receive(buffer);

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
            
            
        } catch (SocketTimeoutException e) {
            System.out.println("Сервер не отвечает, попробуйте позже ещё раз");
            return;
        } catch(Exception e) {
            System.err.println(e);
            return;
        }

        sendData = ToByteObject.objectToBytes(new ClearCommand());
        sendByte = ByteBuffer.wrap(sendData);
        try {
            Client.channel.send(sendByte, Client.gInetSocketAddress()); 
              
            
            System.out.println("Коллекция очищена");     
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    @Override
    public String toString() {
        return "clear - очищает коллекцию";
    }
}

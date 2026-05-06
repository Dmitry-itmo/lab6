package laba.serverUtility;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.slf4j.*;

import laba.data.SpaceMarine;
import laba.utility.ToByteObject;

public class SendManager {
    private static final Logger logger = LoggerFactory.getLogger(ConnectManager.class);

    public static void sendCollection(Set<SpaceMarine> hashSet, DatagramPacket receivePacket, DatagramSocket serverSocket) throws Exception {
        List<SpaceMarine> list = new ArrayList<>(hashSet);
        int size = list.size();
        int chunkSize = 20; 

        byte[] sendBIG = ToByteObject.objectToBytes("BEGIN"); 
        DatagramPacket sendPacketBIG = new DatagramPacket(sendBIG, sendBIG.length, receivePacket.getAddress(), receivePacket.getPort());
        serverSocket.send(sendPacketBIG);
        Thread.sleep(5); 
        

        List<SpaceMarine> buffer = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            buffer.add(list.get(i));

            if ((i + 1) % chunkSize == 0 || (i + 1) == size) {
                byte[] sendData = ToByteObject.objectToBytes(new HashSet<>(buffer));
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                serverSocket.send(sendPacket);
                logger.info("Отправка коллекции {} из {}", i+1,size);
                buffer.clear();
                Thread.sleep(2); 
            }
        }

        byte[] sendEND = ToByteObject.objectToBytes("END");
        serverSocket.send(new DatagramPacket(sendEND, sendEND.length, receivePacket.getAddress(), receivePacket.getPort()));


        
    }
}

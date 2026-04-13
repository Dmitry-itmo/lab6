package laba.utility;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class ToByteObject {
    public static byte[] objectToBytes(Object obj) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(obj);
            oos.flush();
            return baos.toByteArray();
        } catch(Exception e) {
            return e.toString().getBytes();
        }
    }
}

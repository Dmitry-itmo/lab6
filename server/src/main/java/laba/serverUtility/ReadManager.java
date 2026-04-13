package laba.serverUtility;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadManager {
    public static Object bytesToObject(byte[] data, int offset, int length) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(data, offset, length);
        try (ObjectInputStream ois = new ObjectInputStream(bais)) {
            return ois.readObject();
        }
    }
}

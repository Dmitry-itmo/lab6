package laba.serverUtility;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import laba.commands.AddCommand;
import laba.commands.Command;
import laba.commands.ExitCommand;
import laba.utility.CollectionManager;
import laba.utility.FileManager;

public class ReadManager {
    private static final Logger logger = LoggerFactory.getLogger(ConnectManager.class);


    public static Object bytesToObject(byte[] data, int offset, int length) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(data, offset, length);
        try (ObjectInputStream ois = new ObjectInputStream(bais)) {
            return ois.readObject();
        }
    }

    public static void readCommand(Command command) {
        if (command instanceof ExitCommand) {
            FileManager.save();
            return;
        }
        
        command.execute();
        
                        
    }
}

package laba;

import laba.serverUtility.ConnectManager;
import laba.utility.*;
/**
 * @author Romanov Dmitry
 */
public class Main {
    public static void main(String[] args){
        FileManager.load();
        ConnectManager.connecting();
    }
} 
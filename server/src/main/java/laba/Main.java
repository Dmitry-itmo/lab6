package laba;

import java.io.File;

import laba.serverUtility.ConnectManager;
import laba.utility.*;
/**
 * @author Romanov Dmitry
 */
public class Main {
    public static void main(String[] args){
        try {
            FileManager.load();
        } catch(Exception e) {
            FileManager.save();
        }
        ConnectManager.connecting();
    }
} 
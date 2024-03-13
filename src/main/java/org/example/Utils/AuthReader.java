package org.example.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AuthReader {

    public static String readKey(String key) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("src/test/resources/loginDetails.properties"));
        Properties p = new Properties();
        p.load(fileInputStream);
        return key;
    }
}

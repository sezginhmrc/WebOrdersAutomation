package com.weborders.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties configFile;

    static {
        // this static block  will be executed whenever class is loaded
        try {
            // location of properties file
            String path = System.getProperty("user.dir") + "/configuration.properties";
            // get that file as a stream so we can take it from there
            FileInputStream input = new FileInputStream(path);
            //FileInputStream is useful to read data from a file
            // create object of Properties class
            configFile = new Properties();
            // load properties file into properties object
            // Which we can load our custom properties
            // it allows us to load our custom properties and use them in project
            configFile.load(input);
            input.close();
            //close the input stream at the end
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file!");
        }
    }
    // above static method doesnt return anything
    // try catch method since fileinputstream is checked expection
    // we just handled it.
    /*
     * @param keyName property name (key)
     * @return  property value
     * */
    // this method returns property value from configuration.properties file
    public static String getProperty(String keyName) {
        return configFile.getProperty(keyName);
        // we can use it anywhere in project
        // we provide the key
        // it returns the value in configuration properties

    }
}

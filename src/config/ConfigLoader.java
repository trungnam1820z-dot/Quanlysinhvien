package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    Properties prop = new Properties();
    public ConfigLoader(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        prop.load(fis);
    }
    public String get(String key){
        return prop.getProperty(key);
    }
}

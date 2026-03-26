package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    Properties prop;
    public ConfigLoader(String fileName) throws IOException {
        this.prop = new Properties();
        FileInputStream fis = new FileInputStream(fileName);
        prop.load(fis);
    }
    public String get(String key){
        return prop.getProperty(key);
    }
}

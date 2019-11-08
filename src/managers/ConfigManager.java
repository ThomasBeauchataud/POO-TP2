package managers;

import com.esotericsoftware.yamlbeans.YamlReader;

import java.io.FileReader;
import java.util.Map;

public class ConfigManager {

    private static Map config;

    public static int getInt(String key) {
        if(config == null) {
            loadConfig();
        }
        return Integer.parseInt((String)config.get(key));
    }

    private static void loadConfig() {
        try {
            YamlReader reader = new YamlReader(new FileReader(System.getProperty("user.dir") + "/config/config.yml"));
            Object object = reader.read();
            config = (Map) object;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while trying to load the configuration file");
            System.exit(1);
        }
    }

}

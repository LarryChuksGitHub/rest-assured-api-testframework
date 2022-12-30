package utils;

import constants.FrameworkConstants;
import enums.PropertyType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class PropertyUtils {

    private PropertyUtils(){}

    private static Properties properties = new Properties();
    private static Map<String, String > map = new HashMap<>();

//    static {
//
//    }
    private static Map<String, String> readPropertyAndStoreInMap(){
        try(FileInputStream fileInputStream = new FileInputStream(FrameworkConstants.getInstance().getGetPropertyFile())){
            properties.load(fileInputStream);
           // properties.entrySet().forEach(e -> map.put(String.valueOf(e.getKey()),String.valueOf( e.getValue())) );
            for (Map.Entry<Object, Object> entry:  properties.entrySet()) {
                String  key = String.valueOf(entry.getKey());
                String  value = String.valueOf(entry.getValue());
                map.put(key,value);
                System.out.println("the key: "+ key +", the value " +" : " + value);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Property file not found exiting");
            System.exit(0);
        }
        return map;
    }

    public static String getValue(PropertyType key){
        map = readPropertyAndStoreInMap();
        return map.get(key.name().toLowerCase().trim());
    }
}

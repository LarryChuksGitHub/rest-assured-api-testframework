package utils;

import static utils.FakerUtils.*;

public final class RandomUtils {
    private RandomUtils(){}

    public static int getID(){
        return generateRandomValue(100,2000000);
    }
    public static String getFirstname(){
        return generateFirstname();
    }
    public static String getLastname(){
        return generateLastname();
    }
    public static String getEmail(){
        return generateEmail();
    }
    public static String getName(){

        return generateName();
    }
    public static String getDescription(){
        return generateDescritption();
    }
}

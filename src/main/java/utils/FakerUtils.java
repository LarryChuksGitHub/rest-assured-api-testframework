package utils;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;

public final class FakerUtils {
    private static Faker faker = new Faker();

    private FakerUtils(){}

    static int generateRandomValue(int min,int max){
        return faker.number().numberBetween(min,max);
    }

    static String generateFirstname(){
        return faker.name().firstName();
    }
    static String generateLastname(){
        return faker.name().lastName();
    }
    static String generateFullname(){
        return faker.name().fullName();
    }
    static String generateEmail(){
        return generateFullname()+"gmail.com";
    }
    static Address generateAddress(){
        return faker.address();
    }
    static String generateName(){
        return faker.name().name();
    }
    static String generateDescritption(){
        return faker.expression(faker.name()+" Dummy Repo");
    }
}

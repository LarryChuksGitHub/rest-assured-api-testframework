package utils;

import io.restassured.response.Response;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;

public final class APiUtils {

    private APiUtils(){}

    @SneakyThrows
    public static String getJsonFileAndReadIntoString(String filePath){
        byte[] content =  Files.readAllBytes(Paths.get(filePath));
        return new String(content);
    }

    @SneakyThrows
    public static void StoreJsonToFile(String filePath, Response response){
       Files.write(Paths.get(filePath), response.asByteArray());
    }

}

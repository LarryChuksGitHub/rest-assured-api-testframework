package requestbuilder;

import enums.PropertyType;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.PropertyUtils;

import static io.restassured.RestAssured.given;

public final class RequestBuilder {
    private RequestBuilder(){}
    private static String baseUrl = PropertyUtils.getValue(PropertyType.URL);
    private static String baseUrlJira = PropertyUtils.getValue(PropertyType.URLJIRA);


    public static RequestSpecification buildRequestForGetCalls(){
       return  given()
                .header("Content-Type", ContentType.JSON)
                .baseUri(baseUrl)
                 .log()
                .all();
    }
    public static RequestSpecification buildRequestForPostCallsInJira(){
        return  given()
                .config(RestAssuredConfig.config().logConfig(LogConfig.logConfig().blacklistHeader("Authorization","")))
                .header("Content-Type", ContentType.JSON)
                .header("Authorization","Basic Y2h1a3d1amkuaGlsYXJ5QGdtYWlsLmNvbTpKSmVyZW1pMDY/")
                //.auth()
                //.basic("chukwuji.hilary@gmail.com","JJeremi06?")
                .baseUri(baseUrlJira)
                .log()
                .all();
    }

    public static RequestSpecification buildRequestForGetCallsInJira(){
        return  given()
                .config(RestAssuredConfig.config().logConfig(LogConfig.logConfig().blacklistHeader("Authorization")))
                .header("Content-Type", ContentType.JSON)
                .header("Authorization","Basic Y2h1a3d1amkuaGlsYXJ5QGdtYWlsLmNvbTpKSmVyZW1pMDY/")
                .baseUri(baseUrlJira)
                .log()
                .all();
    }

    public static RequestSpecification buildRequestForPostCalls(){
        return  given()
                //.header("Content-Type", ContentType.JSON)
                .contentType(ContentType.JSON)
                .baseUri(baseUrl)
                .log()
                .all();
    }


    public static RequestSpecification buildRequestForPutCalls(){
        return  given()
                //.header("Content-Type", ContentType.JSON)
                .contentType(ContentType.JSON)
                .baseUri(baseUrl)
                .log()
                .all();
    }
    public static RequestSpecification buildRequestForDeleteCalls(){
        return  given()
                //.header("Content-Type", ContentType.JSON)
                .contentType(ContentType.JSON)
                .baseUri(baseUrl)
                .log()
                .all();
    }
}

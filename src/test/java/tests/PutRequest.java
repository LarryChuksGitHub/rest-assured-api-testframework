package tests;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import report.ExtentLogger;
import report.ExtentReport;
import requestbuilder.RequestBuilder;

import java.lang.reflect.Method;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.put;

public class PutRequest  extends BaseTest{


    String uRL = "/Employees";
    Response response = null;
    @Test
    public void testUpdateEmployee(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstname", "Ebu");
        jsonObject.put("lastname","Ede");
        jsonObject.put("email","g@gmail.com");

       RequestSpecification request = RequestBuilder.buildRequestForPutCalls()
                .pathParam("id",1)
                .body(jsonObject.toMap());

        ExtentLogger.requestInfo("This is the Request details");
        String reuestBody = ExtentLogger.logReuestBody(request);
      ExtentLogger.logRequest(reuestBody);
      response = request.put(uRL+"/{id}");
        response.prettyPrint();
        response.statusCode();
        var responseTime = response.getTime();

       //ExtentLogger.pass(MarkupHelper.createCodeBlock(response.prettyPrint(), CodeLanguage.JSON));
        ExtentLogger.info("This is the response details");
       ExtentLogger.logPassResponse(response.asPrettyString());
        System.out.println("This is the response time: " + responseTime);
        System.out.println("This is the status code: " + response.statusCode());
        Assert.assertEquals(response.statusCode(),2001,"did not update user");

    }

}

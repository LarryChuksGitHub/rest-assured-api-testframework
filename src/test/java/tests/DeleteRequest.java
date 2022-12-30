package tests;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import report.ExtentReport;
import requestbuilder.RequestBuilder;

import java.lang.reflect.Method;

import static io.restassured.RestAssured.given;

public class DeleteRequest extends BaseTest {
    String uRL = "/Employees";
    Response response = null;

   // @Test
    public void deleteEmployee(Method method){
       response= RequestBuilder.buildRequestForDeleteCalls()
                .pathParam("id",10)
                .delete(uRL+"/{id}");

        response.prettyPrint();
       // ExtentLogger.pass(method.getName()+MarkupHelper.createCodeBlock(response.prettyPrint(), CodeLanguage.JSON));;
        var responseTime = response.getTime();
        System.out.println("This is the response time: " + responseTime);
        System.out.println("This is the status code: " + response.statusCode());
        Assert.assertEquals(response.statusCode(),200,"did not add user");




    }
}

package tests;

import annotations.FrameworkAnnotation;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import report.ExtentLogger;
import requestbuilder.RequestBuilder;

import java.lang.reflect.Method;


public class DeleteRequest extends BaseTest {
    String uRL = "/employees";
    Response response = null;

    //@FrameworkAnnotation
    @Test(description = "Test to delete Employee")
    public void deleteEmployee(Method method){
       response= RequestBuilder.buildRequestForDeleteCalls()
                .pathParam("id",1)
                .delete(uRL+"/{id}");

        response.prettyPrint();
        //ExtentLogger.pass(method.getName() +" Deletion was successful");
        var responseTime = response.getTime();
        System.out.println("This is the response time: " + responseTime);
        System.out.println("This is the status code: " + response.statusCode());
        Assert.assertEquals(response.statusCode(),200,"did not delete employee");
    }
}

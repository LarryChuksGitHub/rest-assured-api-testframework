package tests;

import annotations.FrameworkAnnotation;
import employee.EmployeeDetails;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import report.ExtentLogger;
import requestbuilder.RequestBodyBuilder;
import requestbuilder.RequestBuilder;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.put;

public class PutRequest extends BaseTest {


    String uRL = "/employees";
    Response response = null;

    @Test
    @FrameworkAnnotation
    public void testUpdateEmployee() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstname", "Ebu");
        jsonObject.put("lastname", "Ede");
        jsonObject.put("email", "g@gmail.com");

        RequestSpecification request = RequestBuilder.buildRequestForPutCalls()

                .pathParam("id", RequestBodyBuilder.bodyBuilderForPutRequest().getId())
                //.body(jsonObject.toMap());
                .body(RequestBodyBuilder.bodyBuilderForPutRequest());

     ExtentLogger.requestInfo("This is the Request details");
        String reuestBody = ExtentLogger.logRequestBody(request);
        ExtentLogger.logRequest(reuestBody);

        response = request.put(uRL + "/{id}");
        response.prettyPrint();
        response.statusCode();
        var responseTime = response.getTime();

        ExtentLogger.info("This is the response details");
        ExtentLogger.logPassResponse(response.asPrettyString());
        System.out.println(response.as(EmployeeDetails.class));
        System.out.println("This is the response time: " + responseTime);
        System.out.println("This is the status code: " + response.statusCode());
        Assert.assertEquals(response.statusCode(), 200, "did not update user");

    }

}

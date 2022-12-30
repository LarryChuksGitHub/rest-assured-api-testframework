package tests;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.github.javafaker.Faker;
import constants.FrameworkConstants;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matcher;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import report.ExtentLogger;
import report.ExtentReport;
import requestbuilder.RequestBuilder;
import utils.APiUtils;
import utils.RandomUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static io.restassured.RestAssured.given;


public class PostRequest  extends BaseTest{
    String uRL = "/employees";
    Response response = null;
    int statuscode = 0;

    //@Test
    public void addEmployee(Method method){
        int randomID = (int) (Math.random()*1000);
        System.out.println("The random in is: " + randomID);
//
//        String jsonBody =
//                "{\n" +
//                "      \"id\":" +
//                 randomID +
//                "      \"email\": \"lindsay.ferguson@reqres.in\",\n" +
//                "      \"first_name\": \"Lindsay\",\n" +
//                "      \"last_name\": \"Ferguson\",\n" +
//                "      \"avatar\": \"https://reqres.in/img/faces/8-image.jpg\"\n" +
//                "  }";

        String jsonBody = "{\n" +
                "      \"id\": 101,\n" +
                "      \"email\": \"lindsay.ferguson@reqres.in\",\n" +
                "      \"first_name\": \"Lindsay\",\n" +
                "      \"last_name\": \"Ferguson\",\n" +
                "      \"avatar\": \"https://reqres.in/img/faces/8-image.jpg\"\n" +
                "    }";


       response = RequestBuilder.buildRequestForPostCalls()
               .body(jsonBody)
               .post(uRL);

       response.prettyPrint();
       statuscode = response.statusCode();
       var responseTime = response.getTime();

        //ExtentLogger.pass(method.getName()+MarkupHelper.createCodeBlock(response.prettyPrint(), CodeLanguage.JSON));;
        //ExtentReport.getTestReport(method.getName()).pass(MarkupHelper.createCodeBlock(response.prettyPrint(), CodeLanguage.JSON));;
        System.out.println("This is the response time: " + responseTime);
        System.out.println("This is the status code: " + statuscode);
        Assert.assertEquals(statuscode,201,"did not add user");

    }

    @Test
    public void addEmployeeAndValidateSchema(Method method) throws IOException {
        response = RequestBuilder.buildRequestForPostCalls()
                .body(new File(FrameworkConstants.getInstance().getGetJsonInputFile()))
                .post(uRL);


        System.out.println(response.getTime());
        System.out.println(response.statusCode());
        response.prettyPrint();

        //ExtentLogger.pass(method.getName()+MarkupHelper.createCodeBlock(response.prettyPrint(), CodeLanguage.JSON));;
        //ExtentReport.getTestReport(method.getName()).pass(MarkupHelper.createCodeBlock(response.prettyPrint(), CodeLanguage.JSON));;
//        ExtentReport.getTestReport(method.getName()).info("Info to the "+ method.getName() + response.prettyPrint());
//        ExtentReport.getTestReport(method.getName()).fail("The test: "+ method.getName() +"failed" +response.prettyPrint());
//        ExtentReport.getTestReport(method.getName()).skip("The test: "+ method.getName() +"failed" +response.prettyPrint());



        Assert.assertEquals(response.statusCode(), 201, "did not add user");
        Assert.assertEquals(response.jsonPath().get("[0].foods.breakfast"),"Bread" ,"incorrect value of bread");
        ValidatableResponse schema = response.then().body(JsonSchemaValidator
                .matchesJsonSchema(new File(FrameworkConstants.getInstance().getGetSchemaPath())));
        System.out.println("This is schema: " +schema.log());
        Files.write(Paths.get(FrameworkConstants.getInstance().getGetJsonResponseOutputFile()),response.asByteArray());
        ExtentLogger.logPassResponse(method.getName());

    }


    @Test
    public void addEmployee3(Method method) throws IOException {
       var content = APiUtils.getJsonFileAndReadIntoString(FrameworkConstants.getInstance().getGetJsonInputFile());
       String contentString = new String(content);
       var body = contentString.replace("8432",String.valueOf(new Faker().number().numberBetween(1,10000)));
                  var replaceID =     body.replace("employeeid", String.valueOf(RandomUtils.getID()));
              var replacedFname = replaceID .replace("fname", new Faker().name().firstName());
              var repladedLname = replacedFname .replace("lname", new Faker().name().lastName());
               var replacedEmail = repladedLname.replace("lindsay.ferguson@reqres.in",new Faker().name().firstName()+"."+new Faker().name().lastName()+"@gmail.com");
              var finalBody = replacedEmail;
        response = RequestBuilder.buildRequestForPostCalls()
                .body(finalBody)
                .post(uRL);
       // ExtentLogger.pass(method.getName()+MarkupHelper.createCodeBlock(response.prettyPrint(), CodeLanguage.JSON));;
        //ExtentReport.getTestReport(method.getName()).pass(MarkupHelper.createCodeBlock(response.prettyPrint(), CodeLanguage.JSON));;
        System.out.println(response.getTime());
        System.out.println(response.statusCode());
        response.prettyPrint();
        statuscode = response.statusCode();
        Assert.assertEquals(statuscode,201,"did not add user");

    }
    @Test
    public void addEmployee4(Method method) throws IOException {

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id",new Faker().number().numberBetween(100,100000));
        map.put("first_name", new Faker().name().firstName());
        map.put("last_name", new Faker().name().lastName());
        map.put("email", new Faker().name().firstName()+"."+new Faker().name().lastName()+"@gmail.com");
        map.put("avatar", "https://reqres.in/img/faces/8-image.jpg");
        map.put("jobs", Arrays.asList("tester","teacher"));

        Map<String, Object> foods = new LinkedHashMap<>();
        foods.put("breakfast", "Bread");
        foods.put("Lunch", "Spagetti");
        List<String> dinner = new ArrayList<>();
        dinner.add("Milk");
        dinner.add("Beans");
        foods.put("Dinner",dinner);
        map.put("foods",foods);

        response = RequestBuilder.buildRequestForPostCalls()
                .body(map)
                .post(uRL);

       // ExtentLogger.pass(method.getName()+MarkupHelper.createCodeBlock(response.prettyPrint(), CodeLanguage.JSON));
       // ExtentReport.getTestReport(method.getName()).pass(MarkupHelper.createCodeBlock(response.prettyPrint(), CodeLanguage.JSON));;
        System.out.println(response.getTime());
        System.out.println(response.statusCode());
        statuscode = response.statusCode();
        response.prettyPrint();
        Assert.assertEquals(statuscode,201,"did not add user");
    }

    @Test
    public void addEmployeeJson(Method method){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",new Faker().number().numberBetween(100,100000));
        jsonObject.put("first_name", new Faker().name().firstName());
        jsonObject.put("first_name", new Faker().name().firstName());
        jsonObject.put("last_name", new Faker().name().lastName());
        jsonObject.accumulate("email", new Faker().name().firstName()+"."+new Faker().name().lastName()+"@gmail.com");
        jsonObject.accumulate("email", new Faker().name().firstName()+"."+new Faker().name().lastName()+"@gmail.com");
        jsonObject.put("avatar", "https://reqres.in/img/faces/8-image.jpg");
        jsonObject.putOpt("car", "");

        JSONArray jobs = new JSONArray();
        jobs.put(Arrays.asList("tester", "teacher"));
        jsonObject.put("jobs",jobs);
        JSONObject foods = new JSONObject();
        foods.put("breakfast", "Bread");
        foods.put("Lunch", "Spagetti");

        JSONArray dinner = new JSONArray();
            dinner.put(Arrays.asList("Milk","Beans"));
            foods.put("Dinner", dinner);
            jsonObject.put("foods",foods);
            Map<String, Object> body = new TreeMap();
           body = jsonObject.toMap();

        response = RequestBuilder.buildRequestForPostCalls()
                .body(body)
                .post(uRL);

       // ExtentLogger.pass(method.getName()+MarkupHelper.createCodeBlock(response.prettyPrint(), CodeLanguage.JSON));;
       // ExtentReport.getTestReport(method.getName()).pass(MarkupHelper.createCodeBlock(response.prettyPrint(), CodeLanguage.JSON));;
        System.out.println(response.getTime());
        System.out.println(response.statusCode());
        statuscode = response.statusCode();
        response.prettyPrint();
        Assert.assertEquals(statuscode,201,"did not add user");
//
//        {
//            "id": 8432,
//                "email": "lindsay.ferguson@reqres.in",
//                "first_name": "Lindsay",
//                "last_name": "Ferguson",
//                "avatar": "https://reqres.in/img/faces/8-image.jpg",
//                "jobs":["tester", "teacher"],
//            "foods" : {
//            "breakfast": "Bread",
//                    "Lunch": "Spagetti",
//                    "Dinner": ["Milk","Beans"]
//        }
//        }

    }
}

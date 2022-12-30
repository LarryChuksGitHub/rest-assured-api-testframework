package tests;

import com.github.javafaker.Faker;
import constants.FrameworkConstants;
import employee.EmployeeOld;
import employee.EmployeeLombok;
import employee.Foods;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Employee;

import requestbuilder.RequestBuilder;
import utils.APiUtils;
import utils.RandomUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PostRequestPojo extends BaseTest{
    // POJO Plain old Java Object

    String uRL = "/Employees";
    Response response = null;
    private int statuscode = 0;
    private int responseTime = 0;


    @Test
    public void postRequest(Method method){
        List<Object> outsideList = new ArrayList<>();
        Foods foods = new Foods("Bread","Rice",Arrays.asList("Milk","Beans","Avocado"));
        EmployeeOld employeeOldJson = new EmployeeOld(
                new Faker().number().numberBetween(10,97535353),"larry@gmail.com","larry","chuks","www.image",
                Arrays.asList("Tester","Teacher","Driver"),foods);
        outsideList.add(employeeOldJson);

       response = RequestBuilder.buildRequestForPostCalls()
                .body(outsideList)
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
    public void postRequestLombok(Method method){
        Foods foods = new Foods("Bread","Rice",Arrays.asList("Milk","Beans","Avocado"));
        var jobs = Arrays.asList("Tester","Teacher","Driver");

        EmployeeLombok employeeJson = new EmployeeLombok(
                new Faker().number().numberBetween(10,97535353),"larry@gmail.com","larry","Chuks","www.image",
                jobs,foods);


        response = RequestBuilder.buildRequestForPostCalls()
                .body(employeeJson)
                .post(uRL);


       // ExtentLogger.pass(method.getName()+MarkupHelper.createCodeBlock(response.prettyPrint(), CodeLanguage.JSON));;
       // ExtentReport.getTestReport(method.getName()).pass(MarkupHelper.createCodeBlock(response.prettyPrint(), CodeLanguage.JSON));;
        response.prettyPrint();
        statuscode = response.statusCode();
        var responseTime = response.getTime();
        System.out.println("This is the response time: " + responseTime);
        System.out.println("This is the status code: " + statuscode);
        Assert.assertEquals(statuscode,201,"did not add user");

        var des =response.as(EmployeeLombok.class);
        String breakfast = des.getFoods().getBreakfast().toString();
        String name = des.getFirstname();
        String email = des.getEmail();

        System.out.println("This should be bread: "+breakfast.toLowerCase());
        System.out.println("This is name: " +name.toLowerCase());
        System.out.println("this is des mail " +email.toLowerCase());

        Assert.assertEquals(name,"larry");
        Assert.assertEquals(breakfast,"Bread");
        Assert.assertEquals(email,"larry@gmail.com");


        response.prettyPrint();
        statuscode = response.statusCode();
        responseTime = response.getTime();
        System.out.println("This is the response time: " + responseTime);
        System.out.println("This is the status code: " + statuscode);
        Assert.assertEquals(statuscode,201,"did not add user");


    }

    @Test
    public void postRequestEmployee(Method method){
        var firstname = RandomUtils.getFirstname();
        var lastname =RandomUtils.getLastname();

        Employee employee = Employee.builder()
                .setFirstname(firstname)
                .setId(RandomUtils.getID())
                .setLastname(lastname)
                .build();

        response = RequestBuilder.buildRequestForPostCalls()
                .body(employee)
                .post(uRL);
        var des =response.as(Employee.class);
        String desfirstname = des.getFirstname();
        String deslastname = des.getLastname();


        Assert.assertEquals(firstname,desfirstname);
        Assert.assertEquals(lastname,deslastname);
        response.prettyPrint();
        statuscode = response.statusCode();
        responseTime = (int) response.getTime();

        //ExtentLogger.pass(method.getName()+MarkupHelper.createCodeBlock(response.prettyPrint(), CodeLanguage.JSON));;

        //ExtentReport.getTestReport(method.getName()).pass(MarkupHelper.createCodeBlock(response.prettyPrint(), CodeLanguage.JSON));;
        System.out.println("This is the response time: " + responseTime);
        System.out.println("This is the status code: " + statuscode);
        Assert.assertEquals(statuscode,201,"did not add user");
    }

    @Test
    public void postRequestEmployeeWithJson(Method method) {

        var filePath =APiUtils.getJsonFileAndReadIntoString(FrameworkConstants.getInstance().getGetJsonInputFile());
        var replacedID = filePath.replace("employeeid",String.valueOf(RandomUtils.getID()));
        var replacedFirstname =replacedID.replace("fname",(RandomUtils.getFirstname()));
        var replacedLastname = replacedFirstname.replace("lname",(RandomUtils.getLastname()));
        var finalJsonBody = replacedLastname;


               // .replace("lastnamex",(RandomUtils.getLastname())
        //.replace("firstnamex",(RandomUtils.getFirstname())))
 //.replace("idx",String.valueOf(RandomUtils.getID()));

        System.out.println("The name: "+filePath.startsWith("[0].firstname"));
      //var  replacedName = filePath.replace("[0].lastname",RandomUtils.getLastname());
        //System.out.println("The replaced name: "+replacedName);

        response = RequestBuilder.buildRequestForPostCalls()
                .body(finalJsonBody)
                .post(uRL);
        statuscode = response.statusCode();
        response.prettyPrint();
       var name = response.jsonPath().getString("[0].firstname");
        responseTime = (int) response.getTime();


      //ExtentLogger.pass(MarkupHelper.createCodeBlock(response.prettyPrint(), CodeLanguage.JSON));;
        System.out.println("This is the response time: " + responseTime);
        System.out.println("This is the status code: " + statuscode);
        System.out.println("The F name: " + name);
        Assert.assertEquals(statuscode,201,"did not add user");
        var time = System.currentTimeMillis();
        APiUtils.StoreJsonToFile(FrameworkConstants.getInstance().getGetJsonResponseOutputFile(), response);
    }

}



/*

[
        {
        "id": 8432,
        "email": "lindsay.ferguson@reqres.in",
        "first_name": "Lindsay",
        "last_name": "Ferguson",
        "avatar": "https://reqres.in/img/faces/8-image.jpg",
        "jobs":["tester", "teacher"],
        "foods" : {
        "breakfast": "Bread",
        "Lunch": "Spagetti",
        "Dinner": ["Milk","Beans"]
        }
        }
        ]

 */

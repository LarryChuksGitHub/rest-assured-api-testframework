package tests;



import annotations.FrameworkAnnotation;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import report.ExtentLogger;
import report.ExtentReport;
import requestbuilder.RequestBuilder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class GetRequest  extends BaseTest{
    private Response response = null;
    private String url ="/employees" ;

    @FrameworkAnnotation(assignAuthor = {"larry,Ebu"},assignCategory = {"calls", "smoke"})
    @Test
    public void testGetEmployees() {
        response = RequestBuilder.buildRequestForGetCalls()
                .get(url );


        Headers headers = response.headers();
        for (Header header : headers) {
            var key = header.getName();
            var value = header.getValue();
            System.out.println(" key " + key + " : " + " value " + value);
        }
        System.out.println("This is the content type: " + response.contentType());
        System.out.println("This is the status code: " + response.statusCode());
        System.out.println("This is the body peek: " + response.body().peek());
        var s =response.getHeaders();
        var size = response.jsonPath().getList("$").size();
        System.out.println("The size of the json list: " + size);

        Assert.assertEquals(response.statusCode(), 200, "incorrect status code");
        System.out.println("response time is: " + response.getTime());
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(size)
                .isPositive();
        ExtentLogger.info("This is all Employees");
      ExtentLogger.pass(MarkupHelper.createCodeBlock(response.asPrettyString(), CodeLanguage.JSON));

    }
    @FrameworkAnnotation
    @Test(dataProvider = "data", description = "List details of one employee")
    public void testGetOneEmployee(Map<String,Object> data, Method method) {

        String firstname = (String)(data.get("first_name"));

        System.out.println("the id is: " + data.get("id"));
        System.out.println("the firstname is: " + firstname);
        Response response = RequestBuilder.buildRequestForGetCalls()
                .queryParam("id", data.get("id"))
                .get(url);

        System.out.println("This is the content type: " + response.contentType());
        System.out.println("response time is: " + response.getTime());
        System.out.println("This is the status code: " + response.statusCode());


        var name = response.jsonPath().getString("[0].first_name");
        assertThat(name).isEqualToIgnoringCase(firstname)
                .isNotEmpty()
                .as("the first name")
                .contains(firstname);
        Assert.assertEquals(response.statusCode(), 200, "incorrect status code");
        ExtentLogger.pass(MarkupHelper.createCodeBlock(response.asPrettyString(), CodeLanguage.JSON));

    }

    @DataProvider(name = "data")
    public Object [] provider(){
        Object [] data = null; //= new Object[4][1];

        Map<Object,Object> map = new HashMap<>();
        map.put("id",8); //
        map.put("first_name","Lindsay"); // Ebu

        Map<Object,Object> map2 = new HashMap<>();
        map2.put("id",9); //
        map2.put("first_name","Tobias");

        Map<Object,Object> map3 = new HashMap<>();
        map3.put("id","11");
        map3.put("first_name","George");

        List<Map<Object,Object>> mapList = new ArrayList<>();
        mapList.add(map);
        mapList.add(map2);
        mapList.add(map3);
//
//        data [0][0] = map;
//        data [1][0] = map2;
//        data [2][0] = map3;
//        data [3][0] = map4;

        // data = (Object[][]) Arrays.asList(map,map2,map3,map4).toArray();
        data = mapList.toArray();

        return data;
    }

}
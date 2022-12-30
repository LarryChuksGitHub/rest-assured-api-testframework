package report;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.http.Headers;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

import java.lang.reflect.Method;

import static com.aventstack.extentreports.markuputils.ExtentColor.*;

public final class ExtentLogger {
    private ExtentLogger(){}

    public static void pass(String message){
        ExtentThreadLocalManager.getextentTestThreadLocal().pass(message);
    }
    public static void pass(Markup message){
        ExtentThreadLocalManager.getextentTestThreadLocal().pass(message);
    }
    public static void fail(String message){
        ExtentThreadLocalManager.getextentTestThreadLocal().fail(MarkupHelper.createLabel(message,RED));
    }
    public static void skip(String message){
        ExtentThreadLocalManager.getextentTestThreadLocal().skip(message);
    }
    public static void info(Markup message){
        ExtentThreadLocalManager.getextentTestThreadLocal().info(message);
    }
    public static void requestInfo(String message){
        ExtentThreadLocalManager.getextentTestThreadLocal().info(message);
    }

    public static void info(String message){
        ExtentThreadLocalManager.getextentTestThreadLocal().info(message);
    }

    public static void logPassResponse(String message){
        ExtentThreadLocalManager.getextentTestThreadLocal().pass(MarkupHelper.createCodeBlock(message, CodeLanguage.JSON));
       // (MarkupHelper.createCodeBlock(response.prettyPrint(), CodeLanguage.JSON))
    }
    public static void logRequest(String message){
        ExtentThreadLocalManager.getextentTestThreadLocal().pass(MarkupHelper.createCodeBlock(message, CodeLanguage.JSON));
    }

    public static void logFailResponse(String message){
        ExtentThreadLocalManager.getextentTestThreadLocal().fail(MarkupHelper.createLabel(message, (ExtentColor.RED)));
    }

    public static void logInfoResponse(String message){
        ExtentThreadLocalManager.getextentTestThreadLocal().info(MarkupHelper.createCodeBlock(message, CodeLanguage.JSON));
    }
    public static void logSkipResponse(String message){
        ExtentThreadLocalManager.getextentTestThreadLocal().skip(MarkupHelper.createCodeBlock(message, CodeLanguage.JSON));
    }

    public static void logReuest(Headers message){
        ExtentThreadLocalManager.getextentTestThreadLocal().info(MarkupHelper.createCodeBlock(String.valueOf(message), CodeLanguage.JSON));
    }

    public static void assignAuthor(String [] authors){
        for (String author: authors) {
            ExtentThreadLocalManager.getextentTestThreadLocal().assignAuthor(author);
        }
    }
    public  static void assignCategory(String [] categoires){
        for (String category: categoires) {
            ExtentThreadLocalManager.getextentTestThreadLocal().assignAuthor(category);
        }

    }

    public static String logReuestBody(RequestSpecification request) {
        QueryableRequestSpecification requestQuery = SpecificationQuerier.query(request);
        String requestBody = requestQuery.getBody().toString();
        return requestBody;
    }

}

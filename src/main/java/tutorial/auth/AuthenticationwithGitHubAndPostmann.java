package tutorial.auth;

import constants.FrameworkConstants;
import lombok.SneakyThrows;
import org.testng.annotations.Test;
import utils.APiUtils;
import utils.RandomUtils;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;



public class AuthenticationwithGitHubAndPostmann {


    @Test
    public void testBasicAuth(){
        var response = given()
                .auth()
                .basic("postman","password")
                .log()
                .all()
                .get("https://postman-echo.com/basic-auth");

        response.prettyPrint();

    }
    @Test
    public void testBasicAuthHeader(){
        var response = given()
                .header("Authorization","Basic cG9zdG1hbjpwYXNzd29yZA==")
                .log()
                .all()
                .get("https://postman-echo.com/basic-auth");

        response.prettyPrint();

    }


    @Test
    public void testAPIKeyAuth(){
        var reposnse = given()
                .header("X-API-Key","PMAK-63aac7360b07051b6a3ffbf1-e48d0244f45ab46a31580e37d7664df096")
                .log()
                .all()
                .get("https://api.getpostman.com/workspaces");

        reposnse.prettyPrint();
    }


    @Test
    public void testGithubGetRepos(){
       var response = given()
                .header("Authorization","Bearer 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")
                .queryParam("per_page",1)
                //.queryParam("visibility","public")
                .log()
                .all()
                .get(" https://api.github.com/user/repos");

       response.prettyPrint();
        var name = response.jsonPath().getString("[0].name");
        System.out.println("The name: " +name);
        assertThat(name).isEqualTo("ghfd-zalando-class");

    }


    @SneakyThrows
    @Test
    public void testGitHubCreateRepo(){
        var content = APiUtils.getJsonFileAndReadIntoString(FrameworkConstants.getInstance().getGetJsonInputFileForGitHub());
        String contentString = new String(content);
       var replacedName = contentString.replace("rname", RandomUtils.getName());
       var description = RandomUtils.getDescription();
       var replacedDescrition = replacedName.replace("rdescription",description);
        System.out.println(description);
       var finalJson = replacedDescrition;

       var response = given()
                .header("Authorization","Bearer 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")
                .log()
                .all()
                .body(finalJson)
                .post("https://api.github.com/user/repos");

       response.prettyPrint();

       description = response.jsonPath().getString("[0].description");
       assertThat(description).isEqualToIgnoringCase(description);

    }
}

package utils;

import constants.FrameworkConstants;
import enums.PropertyType;
import requestbuilder.RequestBuilder;

public class JiraUtils {


    public static String createIssueInJira( String summary, String description){
       String key = null;
        if(PropertyUtils.getValue(PropertyType.CREATEISSUEINJIRA).equalsIgnoreCase("yes")){
            String content = APiUtils.getJsonFileAndReadIntoString(FrameworkConstants.getInstance().getGetJsonInputFileForJiraIssue());
            var contentDespription = content.replace("DESCRIPTION", description);
            var contectSummary = contentDespription.replace("SUMMARY",summary);
            var jsonBody = contectSummary;
            System.out.println("The json body: "+jsonBody);
            var response= RequestBuilder.buildRequestForPostCallsInJira()
                    .body(jsonBody)
                    .post("/rest/api/2/issue/");


            response.asString();
            var task = response.jsonPath().get("name");
             key = response.jsonPath().getString("key");
            System.out.println(response.asString());
            System.out.println("the created issue: "+"http://localhost:8080/browse/"+key);

        }
        return key;
    }

    public static void getIssuesInJira(){
      var  request = RequestBuilder.buildRequestForGetCallsInJira();
       // urljira=http://localhost:8080/rest/api/2
       // http://localhost:8080/browse/DEM-49?jql=project%20%3D%20DEM%20AND%20resolution%20%3D%20Unresolved%20ORDER%20BY%20priority%20DESC%2C%20updated%20DESC
      var response = request.get("/browse/DEM-49");
      response.prettyPrint();
    }

    public static void main(String[] args) {
        //createIssueInJira("Summary", "description");
        getIssuesInJira();

    }
}

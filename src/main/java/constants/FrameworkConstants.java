package constants;

import lombok.Getter;

@Getter
public final class FrameworkConstants {
    private FrameworkConstants(){}

    private static FrameworkConstants INSTANCE = null;

    public static synchronized FrameworkConstants getInstance(){
        if (INSTANCE == null){
            INSTANCE = new FrameworkConstants();
        }
        return INSTANCE;
    }


    private final int time = (int) System.currentTimeMillis();
    private final String systemProperty = System.getProperty("user.dir")+"/src/main/resources/";
    private final String getJsonResponseOutputFile  = systemProperty+"/response/"+time+"response.json";
    private final String getJsonInputFile  = systemProperty+"testdatas/testdata.json";
    private final String getJsonInputFileForGitHub  = systemProperty+"testdatas/github-body.json";
    private final String getJsonInputFileForJiraIssue  = systemProperty+"testdatas/jira-issue.json";
    private final String getPropertyFile = systemProperty+"properties/configproperties";
    private final String getSchemaPath = systemProperty+"schemas/schema.json";
    private final String getReportOutputPath = systemProperty+"reportoutputs/"+time+"testreport.html";

}

package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.spec.SpecificationsServer;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiMethodsAgentActivity extends UrlBase {

    private static final String AGENT_ACTIVITY_AGENT_ACTIVITY   = "/AgentActivity/AgentActivity";
    private static final String ODATA_AGENT_ACTIVITY            = "/odata/AgentActivity";

    public static Response getAgentActivityList(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(AGENT_ACTIVITY_AGENT_ACTIVITY)
                .then()
                .extract().response();

    }

    public static Response getAgentActivityListOdata(){
        return getAgentActivityListOdata(new HashMap<>());
    }

    public static Response getAgentActivityListOdata(Map<String, String> params){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .params(params)
                .when()
                .get(ODATA_AGENT_ACTIVITY)
                .then()
                .extract().response();
    }
}

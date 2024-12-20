package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.spec.SpecificationsServer;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiMethodsAgents extends UrlBase {

    private static final String AGENTS_AGENTS   = "/Agents/Agents";
    private static final String ODATA_AGENTS    = "/odata/Agents";

    public static Response getAgentsList(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(AGENTS_AGENTS)
                .then()
                .extract().response();
    }
    public static Response getAgentsListOdata(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(ODATA_AGENTS)
                .then()
                .extract().response();
    }
}

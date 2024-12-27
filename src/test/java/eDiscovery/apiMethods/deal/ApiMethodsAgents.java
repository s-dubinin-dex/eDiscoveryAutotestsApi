package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.spec.SpecificationsServer;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiMethodsAgents extends UrlBase {

    private static final String AGENTS_UNIQUE_VERSIONS  = "/Agents/UniqueVersions";
    private static final String AGENTS_AGENTS           = "/Agents/Agents";
    private static final String ODATA_AGENTS            = "/odata/Agents";

    public static Response getUniqueAgentVersions(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(AGENTS_UNIQUE_VERSIONS)
                .then()
                .extract().response();
    }

    public static Response getAgentsList(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(AGENTS_AGENTS)
                .then()
                .extract().response();
    }

    public static Response getAgentsListOdata(){
        return getAgentsListOdata(new HashMap<>());
    }

    public static Response getAgentsListOdata(Map<String, String> params){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .params(params)
                .when()
                .get(ODATA_AGENTS)
                .then()
                .extract().response();
    }

    public static Response getAgentById(String id){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(ODATA_AGENTS + String.format("(%s)", id))
                .then()
                .log().all()
                .extract().response();
    }

    public static Response getAgentByIdPath(String id){

        return getAgentByIdPath(id, new HashMap<>());

    }

    public static Response getAgentByIdPath(String id, Map<String, String> params){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .pathParam("id", id)
                .params(params)
                .when()
                .get(ODATA_AGENTS + "/{id}")
                .then()
                .log().all()
                .extract().response();
    }
}

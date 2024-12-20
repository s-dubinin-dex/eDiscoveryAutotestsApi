package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.models.deal.dealAgent.activeTasks.ActiveTasksRequestsModel;
import eDiscovery.models.deal.dealAgent.registerAgent.RegisterAgentRequestModel;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiMethodsDealAgent extends UrlBase {

    private static final String DEAL_AGENT_REGISTER_AGENT   = "/DealAgent/RegisterAgent";
    private static final String DEAL_AGENT_GET_ACTIVE_TASKS = "/DealAgent/GetActiveTasks";

    @Step("Получение задач агентом")
    public static Response getActiveTasks(ActiveTasksRequestsModel requestsBody){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(requestsBody)
                .when()
                .get(DEAL_AGENT_GET_ACTIVE_TASKS)
                .then()
                .extract().response();

    }

    @Step("Регистрация агента")
    public static Response registerAgent(RegisterAgentRequestModel requestBody){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(requestBody)
                .when()
                .post(DEAL_AGENT_REGISTER_AGENT)
                .then()
                .extract().response();

    }
}

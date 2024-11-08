package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiMethodsDealTask extends UrlBase {
    private static final String DEAL_TASK_DEAL_TASK = "/DealTask/DealTask";
    private static final String ODATA_DEAL_TASK = "/odata/DealTask";

    @Step("Получение списка задач")
    public static Response getDealTaskList(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(DEAL_TASK_DEAL_TASK)
                .then()
                .extract().response();
    }

    @Step("Получение списка задач")
    public static Response getDealTaskListOData(){

        return getDealTaskListOData(new HashMap<>());

    }

    @Step("Получение списка задач")
    public static Response getDealTaskListOData(Map<String, String> parameters){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .params(parameters)
                .when()
                .get(ODATA_DEAL_TASK)
                .then()
                .log().all()
                .extract().response();
    }
}

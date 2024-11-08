package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiMethodsDealProgress extends UrlBase {

    private static final String DEAL_PROGRESS_DEAL_PROGRESS = "/DealProgress/DealProgress";
    private static final String ODATA_DEAL_PROGRESS         = "/odata/DealProgress";

    @Step("Получение списка прогрессов")
    public static Response getDealProgressList(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(DEAL_PROGRESS_DEAL_PROGRESS)
                .then()
                .extract().response();
    }

    @Step("Получение списка прогрессов odata")
    public static Response getDealProgressListOData(){

        return getDealProgressListOData(new HashMap<>());

    }

    @Step("Получение списка прогрессов odata")
    public static Response getDealProgressListOData(Map<String, String> parameters){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .params(parameters)
                .when()
                .get(ODATA_DEAL_PROGRESS)
                .then()
                .extract().response();
    }

    @Step("Получение прогресса по id")
    public static Response getDealProgressByID(String id){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(ODATA_DEAL_PROGRESS + String.format("(%s)", id))
                .then()
                .extract().response();
    }

    @Step("Получение прогресса по id")
    public static Response getDealProgressByIDPath(String id){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .pathParam("id", id)
                .when()
                .get(ODATA_DEAL_PROGRESS + "/{id}")
                .then()
                .extract().response();
    }
}

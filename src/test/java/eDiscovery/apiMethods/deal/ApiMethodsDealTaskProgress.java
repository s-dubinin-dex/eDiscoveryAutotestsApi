package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiMethodsDealTaskProgress extends UrlBase {

    private static final String DEAL_TASK_PROGRESS_DEAL_TASK_PROGRESS   = "/DealTaskProgress/DealTaskProgress";
    private static final String ODATA_DEAL_TASK_PROGRESS                = "/odata/DealTaskProgress";

    @Step("Получение списка прогрессов по задачам")
    public static Response getDealTaskProgressList(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(DEAL_TASK_PROGRESS_DEAL_TASK_PROGRESS)
                .then()
                .extract().response();
    }

    @Step("Получение списка прогрессов по задачам")
    public static Response getDealTaskProgressListOData(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(ODATA_DEAL_TASK_PROGRESS)
                .then()
                .extract().response();
    }
}

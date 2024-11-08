package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiMethodsDealStatusHistory extends UrlBase {
    public static final String DEAL_STATUS_HISTORY_DEAL_STATUS_HISTORY  = "/DealStatusHistory/DealStatusHistory";
    public static final String ODATA_DEAL_STATUS_HISTORY                = "/odata/DealStatusHistory";

    @Step("Получение списка изменений статусов")
    public static Response getDealStatusHistoryList(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(DEAL_STATUS_HISTORY_DEAL_STATUS_HISTORY)
                .then()
                .extract().response();
    }

    @Step("Получение списка изменений статусов")
    public static Response getDealStatusHistoryListOData(){

        return getDealStatusHistoryListOData(new HashMap<>());

    }

    @Step("Получение списка изменений статусов")
    public static Response getDealStatusHistoryListOData(Map<String, String> parameters){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .params(parameters)
                .when()
                .get(ODATA_DEAL_STATUS_HISTORY)
                .then()
                .extract().response();
    }

}

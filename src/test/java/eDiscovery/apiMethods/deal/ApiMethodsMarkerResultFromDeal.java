package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiMethodsMarkerResultFromDeal extends UrlBase {

    private static final String ODATA_MARKER_RESULT = "/odata/MarkerResult";

    @Step("Получение результатов маркирования из сервиса Deal")
    public static Response getMarkerResultListFromDeal(){

        return getMarkerResultListFromDeal(new HashMap<>());

    }

    @Step("Получение результатов маркирования из сервиса Deal")
    public static Response getMarkerResultListFromDeal(Map<String, String> parameters){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .params(parameters)
                .when()
                .get(ODATA_MARKER_RESULT)
                .then()
                .extract().response();
    }
}

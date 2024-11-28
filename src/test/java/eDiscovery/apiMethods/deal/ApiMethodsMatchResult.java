package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiMethodsMatchResult extends UrlBase {

    private static final String MATCH_RESULT_MATCH_RESULT   = "/MatchResult/MatchResult";
    private static final String ODATA_MATCH_RESULT          = "/odata/MatchResult";

    @Step("Получение количества совпадений поисковых запросов")
    public static Response getMatchResultList(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(MATCH_RESULT_MATCH_RESULT)
                .then()
                .extract().response();
    }

    @Step("Получение количества совпадений поисковых запросов")
    public static Response getMatchResultListOData(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(ODATA_MATCH_RESULT)
                .then()
                .extract().response();
    }

}

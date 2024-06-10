package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.models.deal.searchPlace.AddSearchPlaceRequestModel;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class ApiMethodsSearchPlace extends UrlBase {

    @Step("Создание места поиска")
    public static Response addSearchPlace(AddSearchPlaceRequestModel addSearchPlaceRequestModel){
        SpecificationsServer.setBaseUrl(URL_DEAL);

        return given()
                .body(addSearchPlaceRequestModel)
                .when()
                .post("/SearchPlace")
                .andReturn();

    }
}

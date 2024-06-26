package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.models.deal.searchPlace.AddSearchPlaceRequestModel;
import eDiscovery.models.deal.searchPlace.UpdateSearchPlaceRequestModel;
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

    @Step("Изменение места поиска")
    public static Response updateSearchPlace(UpdateSearchPlaceRequestModel updateSearchPlaceRequestModel){
        SpecificationsServer.setBaseUrl(URL_DEAL);

        return given()
                .body(updateSearchPlaceRequestModel)
                .when()
                .put("/SearchPlace")
                .andReturn();

    }

    @Step("Удаление места поиска")
    public static Response deleteSearchPlace(String id){
        SpecificationsServer.setBaseUrl(URL_DEAL);

        return given()
                .param("id", id)
                .when()
                .delete("/SearchPlace")
                .andReturn();

    }

    @Step("Получение списка мест поиска")
    public static Response getSearchPlaceList(){
        SpecificationsServer.setBaseUrl(URL_DEAL);

        return given()
                .when()
                .get("/SearchPlace/SearchPlace")
                .andReturn();

    }

    @Step("Получение списка мест поиска")
    public static Response getSearchPlaceListWithIncludeDeletedParameter(Boolean includeDeleted){
        SpecificationsServer.setBaseUrl(URL_DEAL);

        return given()
                .param("includeDeleted", includeDeleted)
                .when()
                .get("/SearchPlace/SearchPlace")
                .andReturn();

    }

    @Step("Получение списка мест поиска по протоколу oData")
    public static Response getSearchPlaceListOData(){
        SpecificationsServer.setBaseUrl(URL_DEAL);

        return given()
                .when()
                .get("/odata/SearchPlace")
                .andReturn();

    }

    @Step("Получение списка мест поиска по протоколу oData")
    public static Response getSearchPlaceListODataWithIncludeDeletedParameter(Boolean includeDeleted){
        SpecificationsServer.setBaseUrl(URL_DEAL);

        return given()
                .param("includeDeleted", includeDeleted)
                .when()
                .get("/odata/SearchPlace")
                .andReturn();

    }
}

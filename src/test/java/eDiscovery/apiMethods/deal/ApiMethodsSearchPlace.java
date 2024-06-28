package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.models.deal.searchPlace.AddSearchPlaceRequestModel;
import eDiscovery.models.deal.searchPlace.AddSearchPlaceRequestModelNotNull;
import eDiscovery.models.deal.searchPlace.UpdateSearchPlaceRequestModel;
import eDiscovery.models.deal.searchPlace.UpdateSearchPlaceRequestModelNotNull;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

import static io.restassured.RestAssured.given;


public class ApiMethodsSearchPlace extends UrlBase {
    private static final String SEARCH_PLACE                = "/SearchPlace";
    private static final String SEARCH_PLACE_SEARCH_PLACE   = "/SearchPlace/SearchPlace";
    private static final String ODATA_SEARCH_PLACE          = "/oData/SearchPlace";


    @Step("Создание места поиска")
    public static Response addSearchPlace(AddSearchPlaceRequestModel addSearchPlaceRequestModel){
        SpecificationsServer.setBaseUrl(DEAL_URL);
        return given()
                .body(addSearchPlaceRequestModel)
                .when()
                .post(SEARCH_PLACE)
                .andReturn();

    }

    @Step("Создание места поиска")
    public static Response addSearchPlace(AddSearchPlaceRequestModelNotNull addSearchPlaceRequestModel){
        SpecificationsServer.setBaseUrl(DEAL_URL);
        return given()
                .body(addSearchPlaceRequestModel)
                .when()
                .post(SEARCH_PLACE)
                .andReturn();

    }

    @Step("Создание места поиска")
    public static Response addSearchPlace(){
        SpecificationsServer.setBaseUrl(DEAL_URL);
        return given()
                .when()
                .post(SEARCH_PLACE)
                .andReturn();

    }

    @Step("Изменение места поиска")
    public static Response updateSearchPlace(UpdateSearchPlaceRequestModel updateSearchPlaceRequestModel){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(updateSearchPlaceRequestModel)
                .when()
                .put(SEARCH_PLACE)
                .andReturn();

    }

    @Step("Изменение места поиска")
    public static Response updateSearchPlace(UpdateSearchPlaceRequestModelNotNull updateSearchPlaceRequestModel){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(updateSearchPlaceRequestModel)
                .when()
                .put(SEARCH_PLACE)
                .andReturn();

    }

    @Step("Изменение места поиска")
    public static Response updateSearchPlace(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .put(SEARCH_PLACE)
                .andReturn();

    }

    @Step("Удаление места поиска")
    public static Response deleteSearchPlace(String id){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .param("id", id)
                .when()
                .delete(SEARCH_PLACE)
                .andReturn();

    }

    @Step("Получение списка мест поиска")
    public static Response getSearchPlaceList(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(SEARCH_PLACE_SEARCH_PLACE)
                .andReturn();

    }

    @Step("Получение списка мест поиска")
    public static Response getSearchPlaceListWithIncludeDeletedParameter(Boolean includeDeleted){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .param("includeDeleted", includeDeleted)
                .when()
                .get("/SearchPlace/SearchPlace")
                .andReturn();

    }

    @Step("Получение списка мест поиска по протоколу oData")
    public static Response getSearchPlaceListOData(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(ODATA_SEARCH_PLACE)
                .andReturn();

    }

    @Step("Получение списка мест поиска по протоколу oData")
    public static Response getSearchPlaceListODataWithParametersMap(HashMap<String, String> parameters){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        RequestSpecification request = given();

        for (String parameterName: parameters.keySet()){
            request.param(parameterName, parameters.get(parameterName));
        }

        return request
                .when()
                .get(ODATA_SEARCH_PLACE)
                .andReturn();

    }

    @Step("Получение списка мест поиска по протоколу oData")
    public static Response getSearchPlaceListODataWithIncludeDeletedParameter(Boolean includeDeleted){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .param("includeDeleted", includeDeleted)
                .when()
                .get(ODATA_SEARCH_PLACE)
                .andReturn();

    }
}

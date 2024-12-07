package eDiscovery.apiMethods.deal;

import eDiscovery.UrlBase;
import eDiscovery.models.deal.searchPlaceGroup.AddSearchPlaceGroupRequestModel;
import eDiscovery.models.deal.searchPlaceGroup.UpdateSearchPlaceGroupRequestModel;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiMethodsSearchPlaceGroup extends UrlBase {

    private static final String SEARCH_PLACE_GROUP                      = "/SearchPlaceGroup";
    private static final String SEARCH_PLACE_GROUP_SEARCH_PLACE_GROUP   = "/SearchPlaceGroup/SearchPlaceGroup";
    private static final String ODATA_SEARCH_PLACE_GROUP                = "/odata/SearchPlaceGroup";

    @Step("Создание группы мест поиска")
    public static Response addSearchPlaceGroup(AddSearchPlaceGroupRequestModel requestBody){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(requestBody)
                .when()
                .post(SEARCH_PLACE_GROUP)
                .then()
                .extract().response();

    }

    @Step("Изменение группы мест поиска")
    public static Response updateSearchPlaceGroup(UpdateSearchPlaceGroupRequestModel requestBody){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(requestBody)
                .when()
                .put(SEARCH_PLACE_GROUP)
                .then()
                .extract().response();

    }

    @Step("Удаление группы мест поиска")
    public static Response deleteSearchPlaceGroup(String id){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .param("id", id)
                .when()
                .delete(SEARCH_PLACE_GROUP)
                .then()
                .extract().response();

    }

    @Step("Получение списка групп мест поиска")
    public static Response getSearchPlaceGroupList(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(SEARCH_PLACE_GROUP_SEARCH_PLACE_GROUP)
                .then()
                .extract().response();

    }

    @Step("Получение списка групп мест поиска по протоколу odata")
    public static Response getSearchPlaceGroupListOData(Map<String, String> params){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .params(params)
                .when()
                .get(ODATA_SEARCH_PLACE_GROUP)
                .then()
                .extract().response();

    }

    @Step("Получение списка групп мест поиска по протоколу odata")
    public static Response getSearchPlaceGroupListOData(){
        return getSearchPlaceGroupListOData(new HashMap<>());
    }

    @Step("Получение группы мест поиска по id")
    public static Response getSearchPlaceGroupById(String id){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(String.format(ODATA_SEARCH_PLACE_GROUP + "(%s)", id))
                .then()
                .extract().response();

    }

    @Step("Получение группы мест поиска по id в path param")
    public static Response getSearchPlaceGroupByIdPath(String id){
        return getSearchPlaceGroupByIdPath(id, new HashMap<>());
    }

    @Step("Получение группы мест поиска по id в path param")
    public static Response getSearchPlaceGroupByIdPath(String id, Map<String, String> params){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .pathParam("id", id)
                .params(params)
                .when()
                .get(ODATA_SEARCH_PLACE_GROUP + "/{id}")
                .then()
                .extract().response();

    }

}

package eDiscovery.apiMethods.deal;

import com.google.gson.JsonObject;
import eDiscovery.UrlBase;
import eDiscovery.models.deal.searchQuery.AddSearchQueryRequestModel;
import eDiscovery.models.deal.searchQuery.AddSearchQueryRequestModelNotNull;
import eDiscovery.models.deal.searchQuery.UpdateSearchQueryRequestModel;
import eDiscovery.models.deal.searchQuery.UpdateSearchQueryRequestModelNotNull;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiMethodsSearchQuery extends UrlBase {
    private static final String SEARCH_QUERY                = "/SearchQuery";
    private static final String SEARCH_QUERY_SEARCH_QUERY   = "/SearchQuery/SearchQuery";
    private static final String ODATA_SEARCH_QUERY          = "/odata/SearchQuery";

    @Step("Создание поискового запроса")
    public static Response addSearchQuery(AddSearchQueryRequestModel addSearchQueryRequestModel){

        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(addSearchQueryRequestModel)
                .when()
                .post(SEARCH_QUERY)
                .then()
                .extract().response();
    }

    @Step("Создание поискового запроса")
    public static Response addSearchQuery(AddSearchQueryRequestModelNotNull addSearchQueryRequestModel){

        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(addSearchQueryRequestModel)
                .when()
                .post(SEARCH_QUERY)
                .then()
                .extract().response();
    }

    @Step("Создание поискового запроса")
    public static Response addSearchQuery(JsonObject jsonObject){

        SpecificationsServer.setBaseUrl(DEAL_URL);
        // Сделал скорее всего для того, чтобы передавать в поля данные других типов
        return given()
                .body(jsonObject)
                .when()
                .post(SEARCH_QUERY)
                .then()
                .extract().response();
    }

    @Step("Создание поискового запроса")
    public static Response addSearchQuery(){

        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .post(SEARCH_QUERY)
                .then()
                .extract().response();
    }

    @Step("Изменение поискового запроса")
    public static Response updateSearchQuery(UpdateSearchQueryRequestModel updateSearchQueryRequestModel){

        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(updateSearchQueryRequestModel)
                .when()
                .put(SEARCH_QUERY)
                .then()
                .extract().response();
    }

    @Step("Изменение поискового запроса")
    public static Response updateSearchQuery(UpdateSearchQueryRequestModelNotNull updateSearchQueryRequestModel){

        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .body(updateSearchQueryRequestModel)
                .when()
                .put(SEARCH_QUERY)
                .then()
                .extract().response();
    }

    @Step("Изменение поискового запроса")
    public static Response updateSearchQuery(){

        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .put(SEARCH_QUERY)
                .then()
                .extract().response();
    }

    @Step("Удаление поискового запроса")
    public static Response deleteSearchQuery(String id){

        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .param("id", id)
                .when()
                .delete(SEARCH_QUERY)
                .then()
                .extract().response();
    }

    @Step("Удаление поискового запроса")
    public static Response deleteSearchQuery(){

        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .delete(SEARCH_QUERY)
                .then()
                .extract().response();
    }

    @Step("Получение списка поисковых запросов")
    public static Response getSearchQueryList(){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(SEARCH_QUERY_SEARCH_QUERY)
                .then()
                .extract().response();

    }

    @Step("Получение списка поисковых запросов")
    public static Response getSearchQueryListWithIncludeDeletedParameter(boolean includeDeleted){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .param("includeDeleted", includeDeleted)
                .when()
                .get(SEARCH_QUERY_SEARCH_QUERY)
                .then()
                .extract().response();

    }

    @Step("Получение списка поисковых запросов по протоколу oData")
    public static Response getSearchQueryListOData(Map<String, String> parameters){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        RequestSpecification request = given();

        for (String parameterName: parameters.keySet()){
            request.param(parameterName, parameters.get(parameterName));
        }

        return request
                .when()
                .get(ODATA_SEARCH_QUERY)
                .then()
                .extract().response();

    }

    @Step("Получение списка поисковых запросов по протоколу oData")
    public static Response getSearchQueryListOData(boolean includeDeleted){

        return getSearchQueryListOData(Map.of("includeDeleted", String.valueOf(includeDeleted)));
    }

    @Step("Получение поискового запроса по id")
    public static Response getSearchQueryODataById(String id){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .when()
                .get(ODATA_SEARCH_QUERY + String.format("(%s)", id))
                .then()
                .extract().response();
    }

    @Step("Получение поискового запроса по id")
    public static Response getSearchQueryODataByIdPath (String id){
        SpecificationsServer.setBaseUrl(DEAL_URL);

        return given()
                .pathParam("id", id)
                .when()
                .get(ODATA_SEARCH_QUERY + "/{id}")
                .then()
                .extract().response();
    }

}

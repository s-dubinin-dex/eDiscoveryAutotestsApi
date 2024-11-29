package eDiscovery.apiMethods.classifier;

import eDiscovery.UrlBase;
import eDiscovery.models.classifier.searchQuery.AddSearchQueryClassifierRequestModel;
import eDiscovery.models.classifier.searchQuery.AddSearchQueryClassifierRequestModelNotNull;
import eDiscovery.models.classifier.searchQuery.UpdateSearchQueryClassifierRequestModel;
import eDiscovery.models.classifier.searchQuery.UpdateSearchQueryClassifierRequestModelNotNull;
import eDiscovery.spec.SpecificationsServer;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiMethodsSearchQueryClassifier extends UrlBase {

    private static final String SEARCH_QUERY                = "/SearchQuery";
    private static final String SEARCH_QUERY_SEARCH_QUERY   = "/SearchQuery/SearchQuery";
    private static final String ODATA_SEARCH_QUERY          = "/odata/SearchQuery";

    @Step("Создание поискового запроса")
    public static Response addSearchQuery(AddSearchQueryClassifierRequestModel addSearchQueryClassifierRequestModel){

        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .body(addSearchQueryClassifierRequestModel)
                .when()
                .post(SEARCH_QUERY)
                .then()
                .extract().response();
    }

    @Step("Создание поискового запроса")
    public static Response addSearchQuery(AddSearchQueryClassifierRequestModelNotNull addSearchQueryClassifierRequestModel){

        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .body(addSearchQueryClassifierRequestModel)
                .when()
                .post(SEARCH_QUERY)
                .then()
                .extract().response();
    }

    @Step("Создание поискового запроса")
    public static Response addSearchQuery(){

        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .when()
                .post(SEARCH_QUERY)
                .then()
                .extract().response();
    }

    @Step("Изменение поискового запроса")
    public static Response updateSearchQuery(UpdateSearchQueryClassifierRequestModel updateSearchQueryClassifierRequestModel){

        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .body(updateSearchQueryClassifierRequestModel)
                .when()
                .put(SEARCH_QUERY)
                .then()
                .extract().response();
    }

    @Step("Изменение поискового запроса")
    public static Response updateSearchQuery(UpdateSearchQueryClassifierRequestModelNotNull updateSearchQueryClassifierRequestModel){

        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .body(updateSearchQueryClassifierRequestModel)
                .when()
                .put(SEARCH_QUERY)
                .then()
                .extract().response();
    }

    @Step("Изменение поискового запроса")
    public static Response updateSearchQuery(){

        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .when()
                .put(SEARCH_QUERY)
                .then()
                .extract().response();
    }

    @Step("Удаление поискового запроса")
    public static Response deleteSearchQuery(String id){

        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .param("id", id)
                .when()
                .delete(SEARCH_QUERY)
                .then()
                .extract().response();
    }

    @Step("Удаление поискового запроса")
    public static Response deleteSearchQuery(){

        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .when()
                .delete(SEARCH_QUERY)
                .then()
                .extract().response();
    }

    @Step("Получение списка поисковых запросов")
    public static Response getSearchQueryList(){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .when()
                .get(SEARCH_QUERY_SEARCH_QUERY)
                .then()
                .extract().response();

    }

    @Step("Получение списка поисковых запросов")
    public static Response getSearchQueryListWithIncludeDeletedParameter(boolean includeDeleted){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .param("includeDeleted", includeDeleted)
                .when()
                .get(SEARCH_QUERY_SEARCH_QUERY)
                .then()
                .extract().response();

    }

    @Step("Получение списка поисковых запросов по протоколу oData")
    public static Response getSearchQueryListOData(Map<String, String> parameters){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

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
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .when()
                .get(ODATA_SEARCH_QUERY + String.format("(%s)", id))
                .then()
                .extract().response();
    }

    @Step("Получение поискового запроса по id")
    public static Response getSearchQueryODataByIdPath (String id){
        SpecificationsServer.setBaseUrl(CLASSIFIER_URL);

        return given()
                .pathParam("id", id)
                .when()
                .get(ODATA_SEARCH_QUERY + "/{id}")
                .then()
                .extract().response();
    }
}
